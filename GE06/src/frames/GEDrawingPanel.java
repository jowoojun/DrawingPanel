package frames;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Shapes.GEEllipse;
import Shapes.GELine;
import Shapes.GERectangle;
import Shapes.GEShape;
import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;

public class GEDrawingPanel extends JPanel {
	private GEShape currentShape;
	private MouseHandler mouseHandler;
	
	public GEDrawingPanel(){
		super();
		
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void initDraw(Point startP){
		currentShape.initDraw(startP);
	}
	
	public void animateDraw(Point currentP){
		Graphics2D g2D = (Graphics2D)getGraphics();
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR);
		currentShape.draw(g2D);
		currentShape.setCoordinate(currentP);
		currentShape.draw(g2D);
	}
	
	public void setCurrentShape(GEShape currentShape){
		this.currentShape = currentShape;
	}
	
	private class MouseHandler extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e){
			initDraw(e.getPoint());
		}
		@Override
		public void mouseDragged(MouseEvent e){
			animateDraw(e.getPoint());
		}
	}
}
