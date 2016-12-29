package shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape {
	public GEPolygon(){
		super(new Polygon());	
	}

	@Override
	public void initDraw(Point startP) {
		((Polygon)myShape).addPoint(startP.x,  startP.y);
	}

	@Override
	public void setCoordinate(Point currentP) {
		Polygon tempPolygon = (Polygon)myShape;
		tempPolygon.xpoints[((Polygon)myShape).npoints - 1] = currentP.x;
		tempPolygon.ypoints[((Polygon)myShape).npoints - 1] = currentP.y;
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	public void continueDrawing(Point currentP){
		((Polygon)myShape).addPoint(currentP.x, currentP.y);
	}
	
	@Override
	public GEShape clone() {
		return new GEPolygon();
	}

}
