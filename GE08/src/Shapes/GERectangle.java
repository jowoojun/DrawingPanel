package Shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class GERectangle extends GEShape{
	public GERectangle(){
		super(new Rectangle());
	}
	
	public void initDraw(Point startP){
		this.startP = startP;
	}
	
	public void setCoordinate(Point currentP){
		Rectangle rectangle = (Rectangle)myshape;
		rectangle.setBounds(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
	}
	
	@Override
	public GEShape clone(){
		return new GERectangle();
	}
}
