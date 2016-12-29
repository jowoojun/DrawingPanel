package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constants.GEConstants.EAnchorTypes;
import utils.GEAnchorList;

public abstract class GEShape {
	protected Shape myShape;
	protected Point startP;
	protected boolean selected;
	protected GEAnchorList anchorList;
	protected EAnchorTypes selectedAnchor;
	protected Color lineColor, fillColor;
	protected AffineTransform affineTransform; 
	
	// GEShape의 생성자
	public GEShape(Shape myShape){
		this.myShape = myShape;
		this.selected = false;
		this.anchorList = null;
		affineTransform = new AffineTransform(); 
	}

		
	// lineColor을 지정
	public void setLineColor(Color lineColor){   
		this.lineColor = lineColor;  
	}  

	// fillColor을 지정
	public void setFillColor(Color fillColor){
		this.fillColor = fillColor;
	}
	
	// AnchorList를 반환
	public GEAnchorList getAnchorList(){   
		return anchorList;  
	}  
	
	// 선택된 앵커를 반환
	public EAnchorTypes getSelectedAnchor(){     
		return selectedAnchor;         
	}  
	
	// 선택됬을때 선택됨을 반환
	public boolean isSelected(){   
		return selected;  
	} 
	
	// 앵커를 올려놓을 사각형틀을 반환
	public Rectangle getBounds(){   
		return myShape.getBounds();  
	}  
	
	// 해당 도형을 그림
	public void draw(Graphics2D g2D){ 
		if(fillColor != null){
			g2D.setColor(fillColor);
			g2D.fill(myShape);
		}
		if(lineColor != null){
			g2D.setColor(lineColor);
			g2D.draw(myShape);
		}
		
		if(selected == true){
			anchorList.setPosition(myShape.getBounds());
			anchorList.draw(g2D);
		}
	}

	// 선택되어져있을때 선택됬다면 GEAnchorList를 생성해서 앵커를 그려준다.
	// 선택되지 않았다면 앵커를 그리지 않는다.
	public void setSelected (boolean selected){
		this.selected = selected;
		if(selected == true){
			anchorList = new GEAnchorList();
			anchorList.setPosition(myShape.getBounds());
		}else{
			anchorList = null;
		}
	}

	// 해당도형위에서 마우스가 앵커위에 있는지 아닌지를 판단
	public boolean onShape(Point p){ 
		if (anchorList!=null){
			selectedAnchor = anchorList.onAnchors(p);    
			if (selectedAnchor != EAnchorTypes.NONE )   
				return true;  
		} 
		return myShape.intersects(new Rectangle(p.x, p.y, 2, 2)); 
	}  
	
	// 해당 도형위에 마우스가 어떤 앵커를 선택했는지를 반환
	public EAnchorTypes onAnchor(Point p){  
		this.selectedAnchor = anchorList.onAnchors(p);  
		return selectedAnchor; 
	}  
	
	// 행렬의 기본행연산을 통해 해당 도형의 크기를 조절함
	public void resizeCoordinate(Point2D resizeFactor){   
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());  
		myShape = affineTransform.createTransformedShape(myShape); 
	} 
	
	// 행렬의 기본행연산을 통해 해당 도형을 이동시킴
	public void moveCoordinate(Point moveP){      
		affineTransform.setToTranslation(moveP.x, moveP.y);
		myShape = affineTransform.createTransformedShape(myShape);   
	} 
	
	// 행렬의 기본행연산을 통해 해당 도형의 크기를 조절함
	public void moveReverse(Point resizeAnchor){  
		affineTransform.setToTranslation(-resizeAnchor.x, -resizeAnchor.y); 
		myShape = affineTransform.createTransformedShape(myShape);
	} 
	
	// 행렬의 기본행연산을 통해 해당 도형의 크기를 조절함
	public void move(Point resizeAnchor){   
		affineTransform.setToTranslation(resizeAnchor.x, resizeAnchor.y); 
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	//행렬의 기본 연상을 통해 해당 도형의 회전각도를 바꿈
	public void rotaterCoordinate(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY() );
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	//유저의 입력값에 따라 도형의 회전시킴
	public void rotaterCoordinate_for_userInput(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(Math.toRadians(theta), rotaterAnchor.getX(), rotaterAnchor.getY() );
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	
	abstract public void initDraw(Point startP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();

}
