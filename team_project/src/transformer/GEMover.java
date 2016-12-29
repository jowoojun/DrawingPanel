package transformer;  
import java.awt.Graphics2D;
import java.awt.Point;

import constants.GEConstants;
import shapes.GEShape;  

public class GEMover extends GETransformer{ 
	private Point previousP;
	
	// GEMover의 생성자
	public GEMover(GEShape shape){ 
		super(shape);  
		previousP = new Point(); 
	} 
	
	// 초기지점을 지정
	@Override
	public void init(Point p) {
		this.previousP = p;
	}
	
	// 초기지점부터 얼마나 움직였는지 측정해고 움직인 이후 도형을 다시그림
	@Override
	public void transformer(Graphics2D g2d, Point p) {
		Point tempP = new Point(p.x - previousP.x, p.y - previousP.y);
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		shape.draw(g2d); 
		shape.moveCoordinate(tempP); 
		shape.draw(g2d); 
		previousP = p; 
	}
} 