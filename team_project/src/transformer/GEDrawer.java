package transformer;  
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import constants.GEConstants;
import shapes.GEPolygon;
import shapes.GEShape; 

public class GEDrawer extends GETransformer{ 
	
	// GEDrawer�� ������
	public GEDrawer(GEShape shape){  
		super(shape);
	} 
	
	// �׸��� �ʱ� ������ ����
	public void init(Point p) { 
		shape.initDraw(p);  
	} 
	
	// �ش� ������ �׸�
	public void transformer(Graphics2D g2D, Point p) { 
		g2D.setXORMode(GEConstants.BACKGROUND_COLOR ); 
		g2D.setStroke(dashedLineStroke);      
		shape.draw(g2D); 
		shape.setCoordinate(p); 
		shape.draw(g2D);  
	} 

	// �ٰ����� ��� Ŭ���ø��� ���� �߰���
	public void continueDrawing(Point p){  
		((GEPolygon)shape).continueDrawing(p);
	}
	
	// shapeList�� ����� �߰�
	public void finalize(ArrayList<GEShape> shapeList) { 
		shapeList.add(shape);
	}
} 