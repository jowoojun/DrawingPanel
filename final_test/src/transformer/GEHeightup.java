package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import constants.GEConstants;
import constants.GEConstants.EAnchorTypes;
import shapes.GEShape;

public class GEHeightup {
	private GEShape shape;
	private Point resizeAnchor;
	
	public GEHeightup(GEShape shape) {
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
		double currentH = shape.getBounds().getHeight();
		double deltaH = GEConstants.plus_5px;
		double xFactor = 1.0;
		
		double yFactor = 1.0;
		if(currentH > 0.0){
			yFactor = (1.0 + deltaH / currentH);
		}
		
		return new Point2D.Double(xFactor, yFactor);
	}
	
	private Point getResizeAnchor(){
		Point resizeAnchor = new Point();
		Ellipse2D.Double tempAnchor = null;
			tempAnchor = shape.getAnchorList().getAnchors().get(EAnchorTypes.NN.ordinal());
		resizeAnchor.setLocation(tempAnchor.x, tempAnchor.y + GEConstants.plus_5px);
		return resizeAnchor;
	}
	
	public void finalize(){
		shape.move(resizeAnchor);
	}
}
