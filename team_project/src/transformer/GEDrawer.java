package transformer;  
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import constants.GEConstants;
import shapes.GEPolygon;
import shapes.GEShape; 

public class GEDrawer extends GETransformer{ 
	
	// GEDrawer의 생성자
	public GEDrawer(GEShape shape){  
		super(shape);
	} 
	
	// 그림의 초기 지점을 지정
	public void init(Point p) { 
		shape.initDraw(p);  
	} 
	
	// 해당 도형을 그림
	public void transformer(Graphics2D g2D, Point p) { 
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR ); 
		g2D.setStroke(dashedLineStroke);      
		shape.draw(g2D); 
		shape.setCoordinate(p); 
		shape.draw(g2D);  
	} 

	// 다각형의 경우 클릭시마다 점을 추가함
	public void continueDrawing(Point p){  
		((GEPolygon)shape).continueDrawing(p);
	}
	
	// shapeList에 모양을 추가
	public void finalize(ArrayList<GEShape> shapeList) { 
		shapeList.add(shape);
	}
} 