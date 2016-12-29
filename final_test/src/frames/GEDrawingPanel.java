package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import constants.GEConstants.EState;
import shapes.GEPolygon;
import shapes.GEShape;
import transformer.GEDrawer;
import transformer.GEHeightup;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GETransformer;
import transformer.GEWidthup;
import utils.GECursorManager;

public class GEDrawingPanel extends JPanel{

	private GEShape currentShape, selectedShape;
	private ArrayList<GEShape> shapeList;
	private EState currentState;
	private GETransformer transformer;
	private MouseDrawingHandler drawingHandler;
	private Color fillColor, lineColor;
	private GECursorManager cursorManager;

	public GEDrawingPanel(){
		super();
		shapeList = new ArrayList<GEShape>();
		currentState = EState.Idle;
		drawingHandler = new MouseDrawingHandler();
		lineColor = GEConstants.COLOR_LINE_DEFAULT;
		fillColor = GEConstants.COLOR_FILL_DEFAULT;
		cursorManager = new GECursorManager();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
	}
	
	public void setFillColor(Color fillColor) {
		if(selectedShape != null){
			selectedShape.setFillColor(fillColor);
			repaint();
		}else {
			this.fillColor = fillColor;
		}

	}

	public void setLineColor(Color lineColor) {
		if(selectedShape != null){
			selectedShape.setLineColor(lineColor);
			repaint();
		}else{
			this.lineColor = lineColor;
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		for(GEShape shape : shapeList){
			shape.draw(g2D);
		}
	}

	public void setCurrentShape(GEShape currentShape) {
		this.currentShape = currentShape;
	}

	private void initDraw(Point startP){
		currentShape = currentShape.clone();
		currentShape.setLineColor(lineColor);
		currentShape.setFillColor(fillColor);
	}

	private void continueDrawing(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}

	private void finishDraw(){
		shapeList.add(currentShape);
	}

	private GEShape onShape(Point p){
		for(int i = shapeList.size() - 1; i >= 0; i--){
			GEShape shape = shapeList.get(i);
			if(shape.onShape(p)){
				return shape;
			}
		}
		return null;
	}

	private void clearSelectedShapes(){
		for(GEShape shape : shapeList){
			shape.setSelected(false);
		}
	}
	
	public void widthUp() {
		if(selectedShape != null){
			GEWidthup widthup = new GEWidthup(selectedShape);
			widthup.init();
			widthup.transformer((Graphics2D)getGraphics());
			widthup.finalize();
			repaint();
		}
	}

	public void HeightUp() {
		if(selectedShape != null){
			GEHeightup heightup = new GEHeightup(selectedShape);
			heightup.init();
			heightup.transformer((Graphics2D)getGraphics());
			heightup.finalize();
			repaint();
		}
	}

	private class MouseDrawingHandler extends MouseAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			if(currentState != EState.Idle){
				transformer.transformer(
						(Graphics2D)getGraphics(), e.getPoint());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(currentState == EState.Idle){
				if(currentShape != null){
					clearSelectedShapes();
					selectedShape = null;
					initDraw(e.getPoint());
					transformer = new GEDrawer(currentShape);
					transformer.init(e.getPoint());
					if(currentShape instanceof GEPolygon){
						currentState = EState.NPointsDrawing;
					}else {
						currentState = EState.TwoPointsDrawing;
					}
				}else{
					selectedShape = onShape(e.getPoint());
					clearSelectedShapes();
					if(selectedShape != null){
						selectedShape.setSelected(true);
						if(selectedShape.onAnchor(e.getPoint()) == EAnchorTypes.NONE){
							transformer = new GEMover(selectedShape);
							transformer.init(e.getPoint());
							currentState = EState.Moving;
						}else{
							transformer = new GEResizer(selectedShape);
							transformer.init(e.getPoint());
							currentState = EState.Resizing;
						}
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(currentState == EState.TwoPointsDrawing){	
				finishDraw();
			}else if(currentState == EState.NPointsDrawing){
				return;
			}else if(currentState == EState.Resizing){
				((GEResizer)transformer).finalize();
			}
			currentState = EState.Idle;
			repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(currentState == EState.NPointsDrawing){
					if(e.getClickCount() == 1){
						continueDrawing(e.getPoint());
					}else if(e.getClickCount() == 2){
						finishDraw();
						currentState = EState.Idle;
						repaint();
					}
				}else if(currentState == EState.Idle){
					if(e.getClickCount() == 2){
						Point tempP = new Point();
						tempP.x = e.getPoint().x + GEConstants.Double_increse;
						tempP.y = e.getPoint().y + GEConstants.Double_increse;
						currentShape.initDraw(e.getPoint());
						transformer.transformer((Graphics2D)getGraphics(), tempP);
						finishDraw();
						currentState = EState.Idle;
						repaint();
					}
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(currentState == EState.NPointsDrawing){
				transformer.transformer(
						(Graphics2D)getGraphics(), e.getPoint());
			}else if(currentState == EState.Idle){
				GEShape shape = onShape(e.getPoint());
				if(shape == null){
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else if(shape.isSelected() == true){
					EAnchorTypes anchorType = shape.onAnchor(e.getPoint());
					setCursor(cursorManager.get(anchorType.ordinal()));
				}
			}
		}		
	}
}
