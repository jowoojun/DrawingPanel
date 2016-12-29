package Shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

public abstract class GEShape {
	protected Shape myshape;
	protected Point startP;
	
	public GEShape(Shape myshape){
		this.myshape = myshape;
	}
	
	public void draw(Graphics2D g2D){
		g2D.draw(myshape);
	}
	
	abstract public void initDraw(Point startP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();
}
