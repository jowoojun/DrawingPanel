package transformer;

import java.awt.Graphics2D;
import java.awt.Point;

import Shapes.GEShape;

public abstract class GETransformer {
	protected GEShape shape;
	
	public GETransformer(GEShape shape){
		this.shape = shape;
	}
	
	abstract public void init(Point p);
	abstract public void tranformer(Graphics2D g2d, Point p);
}
