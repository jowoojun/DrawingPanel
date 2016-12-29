package frames;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Shapes.GEPolygon;
import Shapes.GEShape;
import constants.GEConstants;
import constants.GEConstants.EState;
import transformer.GEDrawer;
import transformer.GETransformer;

public class GEDrawingPanel extends JPanel {
	private GEShape currentShape, selectedShape;
	private EState currentState;
	private ArrayList<GEShape> shapeList;
	private GETransformer transformer;
	private MouseHandler mouseHandler;
	
	public GEDrawingPanel(){
		super();
		
		shapeList = new ArrayList<GEShape>();
		currentState = EState.Idle;
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void setCurrentShape(GEShape currentShape){
		this.currentShape = currentShape;
	}
	
	public void paintComponent(Graphics g){   
		super.paintComponent(g);   
		Graphics2D g2D = (Graphics2D) g;   
		for(GEShape shape : shapeList){
			shape.draw(g2D);   
		}   
	} 

	public void initDraw(Point startP){
		currentShape = currentShape.clone();
		//currentShape.initDraw(startP);
		transformer = new GEDrawer(currentShape);
		transformer.init(startP);
	}
//	
//	public void animateDraw(Point currentP){
//		Graphics2D g2D = (Graphics2D)getGraphics();
//		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
//		currentShape.draw(g2D);
//		currentShape.setCoordinate(currentP);
//		currentShape.draw(g2D);
//	}
//	
	public void continuDraw(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	private void finishDraw(){
		shapeList.add(currentShape);
		
	}
	
	private GEShape onShape(Point p){
		for(GEShape shape : shapeList){
			if(shape.onShape(p)){
				return shape; 
			}
		}
		return null;
	}
	
	private void clearSelectedShape(){
		for(GEShape shape : shapeList){
			shape.setSelected(false);
		}
	}
	private class MouseHandler extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e){
			if(currentState == EState.Idle){
				if(currentShape != null){
					initDraw(e.getPoint());
					if(currentShape instanceof GEPolygon)
						currentState = EState.NPointsDrawing;
					else
						currentState = EState.TwoPointsDrawing;
				}else{
					selectedShape = onShape(e.getPoint());
					clearSelectedShape();
					if(selectedShape != null){
						selectedShape.setSelected(true);
					}
				}
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if(currentState == EState.NPointsDrawing)
				// animateDraw(e.getPoint());
				transformer.tranformer((Graphics2D)getGraphics(), e.getPoint());
		}
		
		@Override
		public void mouseDragged(MouseEvent e){
			if(currentState == EState.TwoPointsDrawing)
				//animateDraw(e.getPoint());
				transformer.tranformer((Graphics2D)getGraphics(), e.getPoint());
		}		
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(currentState == EState.TwoPointsDrawing){
				finishDraw();
				currentState = EState.Idle;
			}else if(currentState == EState.NPointsDrawing){
				return;
			}
			repaint();
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1){
				if(currentState == EState.NPointsDrawing){
					if(e.getClickCount() == 1){
						continuDraw(e.getPoint());
					}
					else if(e.getClickCount() == 2){
						finishDraw();
						currentState = EState.Idle;
						repaint();
					}
				}
			}
		}

		
	}
}

