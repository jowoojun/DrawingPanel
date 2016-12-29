package shapes;

import java.awt.Point;
import java.awt.geom.Line2D;

public class GELine extends GEShape{
	// GELine의 생성자
	public GELine(){
		super(new Line2D.Double());
	}
	
	// 초기 지점을 지정
	public void initDraw(Point startP){
		this.startP = startP;
	}
	
	// 그림을 조정하면서 그림
	public void setCoordinate(Point currentP){
		Line2D line = (Line2D)myShape;
		line.setLine(startP.x, startP.y, currentP.x, currentP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// 만들어진 도형의 클론을 반환
	@Override
	public GEShape clone(){
		return new GELine();
	}
}
