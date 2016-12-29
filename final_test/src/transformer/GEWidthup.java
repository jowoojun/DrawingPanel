package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import shapes.GEShape;

public class GEWidthup {
	private GEShape shape;
	private Point resizeAnchor;
	
	public GEWidthup(GEShape shape) {
		this.shape = shape;
	}
	
	
	public void init() {
		resizeAnchor = getResizeAnchor();
		shape.moveReverse(resizeAnchor);
	}
	
	public void transformer(Graphics2D g2d) {
		g2d.setXORMode(g2d.getBackground());
		AffineTransform tempAffine = g2d.getTransform();
		Point2D resizeFactor = computeResizeFactor();
		g2d.translate(resizeAnchor.x, resizeAnchor.y);
		shape.draw(g2d);
		shape.resizeCoordinate(computeResizeFactor());
		shape.draw(g2d);
		g2d.setTransform(tempAffine);
	}
	
	private Point2D.Double computeResizeFactor(){
		double currentW = shape.getBounds().getWidth();
		
		double deltaW = GEConstants.plus_5px;
		
		double xFactor = 1.0;
		if(currentW > 0.0){
			xFactor = (1.0 + deltaW / currentW);
		}
		
		double yFactor = 1.0;
		
		return new Point2D.Double(xFactor, yFactor);
	}
	
	private Point getResizeAnchor(){
		Point resizeAnchor = new Point();
		Ellipse2D.Double tempAnchor = null;
			tempAnchor = shape.getAnchorList().getAnchors().get(EAnchorTypes.WW.ordinal());
		resizeAnchor.setLocation(tempAnchor.x + GEConstants.plus_5px, tempAnchor.y);
		return resizeAnchor;
	}
	
	public void finalize(){
		shape.move(resizeAnchor);
	}
}
