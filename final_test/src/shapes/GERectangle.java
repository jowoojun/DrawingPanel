package shapes;

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
		Rectangle tempRectangle = (Rectangle)myShape;
		tempRectangle.setBounds(startP.x, startP.y,
				currentP.x - startP.x, currentP.y - startP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}

	@Override
	public GEShape clone() {
		return new GERectangle();
	}	
}
