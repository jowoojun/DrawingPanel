package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import transformer.GEDrawer;
import transformer.GEGrouper;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotater;
import transformer.GETransformer;
import utils.GECursorManager;
import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import constants.GEConstants.EState;
import shapes.GEGroup;
import shapes.GEPolygon;
import shapes.GESelect;
import shapes.GEShape;

public class GEDrawingPanel extends JPanel {
	private GEShape currentShape, selectedShape;
	private EState currentState;
	private ArrayList<GEShape> shapeList;
	private GETransformer transformer;
	private Color fillColor, lineColor;
	private MouseHandler mouseHandler;
	private GECursorManager cursorManager;
	
	// drawingPanel ������
	public GEDrawingPanel(){
		super();
			
		shapeList = new ArrayList<GEShape>();
		cursorManager = new GECursorManager();
		currentState = EState.Idle;
		this.setBackground(GEConstants.BACKGROUND_COLOR);
		this.setForeground(GEConstants.FOREGROUND_COLOR);
		
		this.fillColor = GEConstants.COLOR_FILL_DEFAULT;
		this.lineColor = GEConstants.COLOR_LINE_DEFAULT;
		
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void setRotate(String result) {
		if(selectedShape!=null){
			transformer = new GERotater(selectedShape,(Graphics2D)getGraphics(), getLocation(),result);
			repaint();
			currentState = EState.Rotater;
		}
		
	}	
	
	// �׷� ����
	public void group(GEGroup group) {   
		boolean check = false;   
		for (int i = shapeList.size(); i > 0; i--) { 
			GEShape shape = shapeList.get(i - 1);  
			if(shape.isSelected()){ 
				shape.setSelected(false);   
				group.addShape(shape); 
				shapeList.remove(shape);
				check = true;
			}  
		}  
		if(check){  
			shapeList.add(group); 
			group.setSelected(true);  
		}
		repaint();  
	} 
	
	// �׷�����
	public void ungroup() { 
		ArrayList<GEShape> tempList = new ArrayList<GEShape>();
		for (int i = shapeList.size(); i > 0; i--) { 
			GEShape shape = shapeList.get(i - 1);  
			if(shape instanceof GEGroup && shape.isSelected()){
				for(GEShape childShape: ((GEGroup)shape).getChildList()){   
					childShape.setSelected(true);   
					tempList.add(childShape);   
				} 
				shapeList.remove(shape); 
			}
		}
		shapeList.addAll(tempList);  
		repaint();  
	} 
	
	// ���� ���� �� ���� ���� ���� �⺻������ ����
	private void createShape(Point startP) {
		currentShape = currentShape.clone();
		currentShape.setLineColor(lineColor); 
		currentShape.setFillColor(fillColor); 
	} 
	
	// ��� ������ ���õ��� �ʵ�����
	public void clearSelectedShapes() {   
		for (GEShape shape : shapeList) {  
			shape.setSelected(false);
		} 
	}
	
	// ������ ���λ��� ����
	public void setFillColor(Color fillColor){
		if(selectedShape != null){
			selectedShape.setFillColor(fillColor);
			repaint();
		}else{
			this.fillColor = fillColor;
		}	
	}
	
	// ������ ������ ����
	public void setLineColor(Color lineColor){
		if(selectedShape != null){
			selectedShape.setLineColor(lineColor);
			repaint();
		}else{
			this.lineColor = lineColor;
		}
	}
	
	// GEShape������ currentShape�� drwingPanel���� currentShape�� ����
	public void setCurrentShape(GEShape currentShape) {  
		this.currentShape = currentShape; 
	} 
	
	// �ش� ������ �׸�
	public void paintComponent(Graphics g){   
		super.paintComponent(g);   
		Graphics2D g2D = (Graphics2D) g;   
		for(GEShape shape : shapeList){
			shape.draw(g2D);   
		}   
	} 
	
	// �ٰ����� �׸���  transformer��  continueDrawing�޼ҵ忡 currentP�� �Ѱ���
	public void continuDraw(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	// �ش� ���� ������ �ִ� ������ ��ȯ��
	private GEShape onShape(Point p){
		for(int i = shapeList.size() - 1; i >= 0; i--){
			GEShape shape = shapeList.get(i);
			if(shape.onShape(p)){
				return shape; 
			}
		}
		return null;
	}
	
	// ���콺�� ���� �ൿ�� ����
	private class MouseHandler extends MouseAdapter{
		
		// ���콺�� �������� shift�� ���������� ������ü�� �����ϰ���
		// ���콺�� �������� � ������ ���É�� anchor���� ���콺�� ������ GEMover�� �����ȴ�.
		// ���콺�� �������� � ������ ���É�� anchor���� ���콺�� ������ GEResizer�� �����ȴ�.
		// ���콺�� �������� � ������ ���õ��� �ʾ����� GEGrouper�� �����ȴ�.
		// ���콺�� �������� currentState�� idle�� �ƴϸ� �ش� ������ �׸���.
		@Override
		public void mousePressed(MouseEvent e) {
			if (currentState == EState.Idle ) {  
				if (currentShape instanceof GESelect && (e.isShiftDown())){
					selectedShape = onShape(e.getPoint());
					 if(selectedShape != null) {
						selectedShape.setSelected(true);  
						selectedShape.onAnchor(e.getPoint());
						currentState = EState.Shift; 
					}else{
						clearSelectedShapes();
					}
				}else if (currentShape instanceof GESelect){  
					selectedShape = onShape(e.getPoint());
					if (selectedShape != null) {       
						 clearSelectedShapes();  
						 selectedShape.setSelected(true);  
						 selectedShape.onAnchor(e.getPoint());     
						 if (selectedShape.getSelectedAnchor() == EAnchorTypes.NONE ){   
							 transformer = new GEMover(selectedShape);    
							 ((GEMover)transformer).init(e.getPoint());   
							 currentState = EState.Moving;     
						 }else if( selectedShape.getSelectedAnchor() == EAnchorTypes.RR ){ 
								transformer = new GERotater(selectedShape); //����ȸ��
								((GERotater)transformer).init(e.getPoint());
								currentState = EState.Rotater;
						}else {       
							 transformer = new GEResizer(selectedShape);   
							 ((GEResizer) transformer).init(e.getPoint());    
							 currentState = EState.Resize;      
						 }  
					}else {   
						 currentState = EState.Selecting;  
						 clearSelectedShapes();
						 createShape(e.getPoint());   
						 transformer = new GEGrouper(currentShape);   
						 ((GEGrouper)transformer).init(e.getPoint());
						 
					 }  				
				}else {  
					clearSelectedShapes();    
					createShape(e.getPoint());    
					transformer = new GEDrawer(currentShape);     
					((GEDrawer) transformer).init(e.getPoint());  
					if (currentShape instanceof GEPolygon) {     
						currentState = EState.NPointsDrawing;  
					} else {   
						currentState = EState.TwoPointsDrawing; 
					}    
				}   
			}   
		}
			
		// ���콺�� �����϶� 
		@Override
		public void mouseMoved(MouseEvent e) {   
			if (currentState == EState.NPointsDrawing) {   
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());    
			} 
			else if (currentState == EState.Idle ) {  
				GEShape shape = onShape(e.getPoint());   
				if (shape == null) {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR ));  
				} 
				else {     
					if (shape.isSelected()) {
						EAnchorTypes anchorType = shape.onAnchor(e.getPoint()); 
						setCursor(cursorManager.get(anchorType.ordinal())); 
					}    
				} 
			}
		}
		
		// ���콺�� �巡�� �ɶ�
		@Override
		public void mouseDragged(MouseEvent e) {    
			if (currentState != EState.Idle ) {    
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());   
			}  
		}   
		
		// ���콺�� Ǯ������
		@Override
		public void mouseReleased(MouseEvent e) { 
			if (currentState == EState.TwoPointsDrawing ) {  
				((GEDrawer)transformer).finalize(shapeList);  
			}else if (currentState == EState.NPointsDrawing ) { 
				return; 
			}else if (currentState == EState.Resize ) {  
				((GEResizer) transformer).finalize(e.getPoint());   
			} else if (currentState == EState.Selecting ){   
				((GEGrouper) transformer).finalize(shapeList);   
			}
			currentState = EState.Idle;
			repaint();
		}  
		
		// ���콺�� Ŭ��������
		@Override
		public void mouseClicked(MouseEvent e) { 
			if (e.getButton() == MouseEvent. BUTTON1 ) { 
				if (currentState == EState.NPointsDrawing ) {   
					if (e.getClickCount() == 1) { 
						continuDraw(e.getPoint());    
					} else if (e.getClickCount() == 2) {  
						((GEDrawer) transformer).finalize(shapeList);    
						currentState = EState.Idle;      
						repaint();   
					}  
				}
			}
			//��ü ���� �� ���콺 ���������� ���� �Է� â ��
			if(e.getButton() == MouseEvent.BUTTON3){
				String result = JOptionPane.showInputDialog("������ �Է����ּ���.");
				if(result != null && selectedShape != null){
					transformer = new GERotater(selectedShape,
							(Graphics2D)getGraphics(), e.getPoint(),result);
					repaint();

					currentState = EState.Rotater;
				}
			}
		}
	}
} 
		
		
