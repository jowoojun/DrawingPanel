package Shapes;

import java.awt.Point;
import java.awt.Polygon;

public class GEPolygon extends GEShape {
	public GEPolygon(){
		super(new Polygon());
	}
	
	@Override
	public void initDraw(Point startP) {
		((Polygon)myshape).addPoint(startP.x, startP.y);
	}
	
	public void continueDrawing(Point currentP){	
		((Polygon)myshape).addPoint(currentP.x,currentP.y);
	}

	@Override
	public void setCoordinate(Point currentP) {
		Polygon tempPolygon = (Polygon)myshape;
		tempPolygon.xpoints[tempPolygon.npoints - 1] = currentP.x;
		tempPolygon.ypoints[tempPolygon.npoints - 1] = currentP.y;

	}

	@Override
	public GEShape clone() {
		return new GEPolygon();
	}
}
