package transformer;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import constants.GEConstants.*;
import shapes.*;

public class GERotater extends GETransformer {
	private ArrayList<GEShape> shapelist;
	private Point2D.Double ROrigin;
	private double theta;
	private String temp_theta;

	public GERotater(GEShape shape) {
		super(shape);
	}
	
	//사용자 입력에 따라 회전하는 생성자
	public GERotater(GEShape shape,Graphics2D g2d, Point p, String temp_theta) {
		super(shape);
		this.init(p);
		this.set_user_input_transformer(g2d, p, Double.parseDouble(temp_theta));		
	}

	@Override
	public void transformer(Graphics2D g2d, Point p) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		double theta2 = theta - Math.atan2(ROrigin.y- p.getY(), p.getX() - ROrigin.x);
		if(this.temp_theta!=null){
			theta2 = Double.parseDouble(this.temp_theta);
		}
		shape.draw(g2d);
		shape.rotaterCoordinate(theta2, ROrigin);
		shape.draw(g2d);
		theta = Math.atan2(ROrigin.y - p.getY(), p.getX() - ROrigin.x);
	}
	 
	@Override
	public void init(Point p) {
		ROrigin = new Point2D.Double(shape.getBounds().getCenterX(), shape.getBounds().getCenterY());
		theta = Math.atan2(ROrigin.y - p.getY(), ROrigin.x - p.getX());
	}

	//유저 입력 회전 관련 메소드
	public void set_user_input_transformer(Graphics2D g2d, Point p, double temp_theta) {
		g2d.setXORMode(g2d.getBackground());
		g2d.setStroke(dashedLineStroke);
		shape.draw(g2d);
		shape.rotaterCoordinate_for_userInput(temp_theta, ROrigin);
		shape.draw(g2d);
		theta = Math.atan2(ROrigin.y - p.getY(), p.getX() - ROrigin.x);
	}
}
