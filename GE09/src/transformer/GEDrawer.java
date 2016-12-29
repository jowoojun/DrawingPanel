package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import Shapes.GEPolygon;
import Shapes.GEShape;

public class GEDrawer extends GETransformer {

	public GEDrawer(GEShape shape){
		super(shape);
	}

	@Override
	public void init(Point p) {
		shape.initDraw(p);
	}

	@Override
	public void tranformer(Graphics2D g2d, Point p) {
		g2d.setXORMode(g2d.getBackground());
		shape.draw(g2d);
		shape.setCoordinate(p);
		shape.draw(g2d);
	}
	
	public void continueDrawing(Point p){
		((GEPolygon)shape).continueDrawing(p);
	}

}
