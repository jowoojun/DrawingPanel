package shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GEPolygon extends GEShape {
	// GEPolygon의 생성자
	public GEPolygon(){
		super(new Polygon());
	}
	
	// 초기 지점을 지정
	@Override
	public void initDraw(Point startP) {
		((Polygon)myShape).addPoint(startP.x, startP.y);
	}
	
	// 다각형의 각 점을 하나씩 추가하면서 그린다.
	public void continueDrawing(Point currentP){	
		((Polygon)myShape).addPoint(currentP.x,currentP.y);
	}

	// 그림을 조정하면서 그림
	@Override
	public void setCoordinate(Point currentP) {
		Polygon tempPolygon = (Polygon)myShape;
		tempPolygon.xpoints[tempPolygon.npoints - 1] = currentP.x;
		tempPolygon.ypoints[tempPolygon.npoints - 1] = currentP.y;
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// 만들어진 도형의 클론을 반환
	@Override
	public GEShape clone() {
		return new GEPolygon();
	}
}
