package Shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape {
	public GEPolygon(){
		super(new Polygon());
	}
	
	@Override
	public void initDraw(Point startP) {
		((Polygon)myShape).addPoint(startP.x, startP.y);
	}
	
	public void continueDrawing(Point currentP){	
		((Polygon)myShape).addPoint(currentP.x,currentP.y);
	}

	@Override
	public void setCoordinate(Point currentP) {
		Polygon tempPolygon = (Polygon)myShape;
		tempPolygon.xpoints[tempPolygon.npoints - 1] = currentP.x;
		tempPolygon.ypoints[tempPolygon.npoints - 1] = currentP.y;
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}

	@Override
	public GEShape clone() {
		return new GEPolygon();
	}
}
