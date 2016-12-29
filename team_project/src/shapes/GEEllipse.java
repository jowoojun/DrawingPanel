package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape{
	// GEEllipse의 생성자
	public GEEllipse(){
		super(new Ellipse2D.Double());
	}
	
	// 초기 지점을 지정
	public void initDraw(Point startP){
		this.startP = startP;
	}
	
	// 그림을 조정하면서 그림
	public void setCoordinate(Point currentP){
		Ellipse2D ellipse = (Ellipse2D)myShape;
		ellipse.setFrame(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// 만들어진 도형의 클론을 반환
	@Override
	public GEShape clone(){
		return new GEEllipse();
	}
}
