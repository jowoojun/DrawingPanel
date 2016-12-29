package shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class GERectangle extends GEShape{
	// GERectangle의 생성자
	public GERectangle(){
		super(new Rectangle());
	}
	
	// 초기 지점을 지정
	public void initDraw(Point startP){
		this.startP = startP;
	}

	
	// 그림을 조정하면서 그림
	public void setCoordinate(Point currentP){
		Rectangle rectangle = (Rectangle)myShape;
		rectangle.setBounds(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// 만들어진 도형의 클론을 반환
	@Override
	public GEShape clone(){
		return new GERectangle();
	}
}
