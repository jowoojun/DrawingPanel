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
	
	// drawingPanel 생성자
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
	
	// 그룹 생성
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
	
	// 그룹해제
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
	
	// 도형 생성 및 선과 내부 색을 기본색으로 지정
	private void createShape(Point startP) {
		currentShape = currentShape.clone();
		currentShape.setLineColor(lineColor); 
		currentShape.setFillColor(fillColor); 
	} 
	
	// 모든 도형이 선택되지 않도록함
	public void clearSelectedShapes() {   
		for (GEShape shape : shapeList) {  
			shape.setSelected(false);
		} 
	}
	
	// 도형의 내부색을 지정
	public void setFillColor(Color fillColor){
		if(selectedShape != null){
			selectedShape.setFillColor(fillColor);
			repaint();
		}else{
			this.fillColor = fillColor;
		}	
	}
	
	// 도형의 선색을 지정
	public void setLineColor(Color lineColor){
		if(selectedShape != null){
			selectedShape.setLineColor(lineColor);
			repaint();
		}else{
			this.lineColor = lineColor;
		}
	}
	
	// GEShape에서의 currentShape를 drwingPanel에서 currentShape로 지정
	public void setCurrentShape(GEShape currentShape) {  
		this.currentShape = currentShape; 
	} 
	
	// 해당 도형을 그림
	public void paintComponent(Graphics g){   
		super.paintComponent(g);   
		Graphics2D g2D = (Graphics2D) g;   
		for(GEShape shape : shapeList){
			shape.draw(g2D);   
		}   
	} 
	
	// 다각형을 그릴때  transformer의  continueDrawing메소드에 currentP을 넘겨줌
	public void continuDraw(Point currentP){
		((GEDrawer)transformer).continueDrawing(currentP);
	}
	
	// 해당 점을 가지고 있는 도형을 반환함
	private GEShape onShape(Point p){
		for(int i = shapeList.size() - 1; i >= 0; i--){
			GEShape shape = shapeList.get(i);
			if(shape.onShape(p)){
				return shape; 
			}
		}
		return null;
	}
	
	// 마우스에 대한 행동을 조작
	private class MouseHandler extends MouseAdapter{
		
		// 마우스가 눌렸을때 shift가 눌려있으면 여러객체를 선택하게함
		// 마우스가 눌렸을때 어떤 도형이 선택됬고 anchor위에 마우스가 없을때 GEMover가 생성된다.
		// 마우스가 눌렸을때 어떤 도형이 선택됬고 anchor위에 마우스가 있으면 GEResizer가 생성된다.
		// 마우스가 눌렸을때 어떤 도형도 선택되지 않았으면 GEGrouper가 생성된다.
		// 마우스가 눌렸을때 currentState가 idle이 아니면 해당 도형을 그린다.
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
								transformer = new GERotater(selectedShape); //도형회전
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
			
		// 마우스가 움직일때 
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
		
		// 마우스가 드래그 될때
		@Override
		public void mouseDragged(MouseEvent e) {    
			if (currentState != EState.Idle ) {    
				transformer.transformer((Graphics2D)getGraphics(), e.getPoint());   
			}  
		}   
		
		// 마우스가 풀렸을때
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
		
		// 마우스가 클릭됬을때
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
			//객체 선택 후 마우스 오른쪽으로 각도 입력 창 뷰
			if(e.getButton() == MouseEvent.BUTTON3){
				String result = JOptionPane.showInputDialog("각도를 입력해주세요.");
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
		
		
