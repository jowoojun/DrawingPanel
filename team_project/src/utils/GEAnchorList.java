package utils;  
import java.awt.Graphics2D; 
import java.awt.Point; 
import java.awt.Rectangle; 
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;
import java.util.Vector;
import constants.GEConstants; 
import constants.GEConstants.EAnchorTypes; 

public class GEAnchorList {  
	private ArrayList<Double> anchors;  
	
	// GEAnchorList의 생성자
	public GEAnchorList() {   
		anchors = new ArrayList<Ellipse2D.Double>();  
		for (int i=0; i<GEConstants.EAnchorTypes. values ().length-1; i++) { 
			anchors.add(new Ellipse2D.Double(0, 0, 0, 0));  
		}  
	} 
	
	// 앵커들의 위치를 지정
	public void setPosition(Rectangle r) { 
		int x = r.x;  
		int y = r.y; 
		int w = r.width;  
		int h = r.height;
		int dx = GEConstants. ANCHOR_W /2; 
		int dy = GEConstants. ANCHOR_H /2; 
		anchors.get(GEConstants.EAnchorTypes. NW .ordinal()).setFrame(x-dx, y-dy, GEConstants. ANCHOR_W , GEConstants. ANCHOR_H ); 
		anchors.get(GEConstants.EAnchorTypes. NN .ordinal()).setFrame(x+w/2-dx,y-dy,GEConstants. ANCHOR_W , GEConstants. ANCHOR_H );  
		anchors.get(GEConstants.EAnchorTypes. NE .ordinal()).setFrame(x+w-dx, y-dy, GEConstants. ANCHOR_W , GEConstants. ANCHOR_H ); 
		anchors.get(GEConstants.EAnchorTypes. WW .ordinal()).setFrame(x-dx,y+h/2-dy, GEConstants. ANCHOR_W , GEConstants. ANCHOR_H );  
		anchors.get(GEConstants.EAnchorTypes. EE .ordinal()).setFrame(x+w-dx,y+h/2-dy, GEConstants. ANCHOR_W , GEConstants. ANCHOR_H ); 
		anchors.get(GEConstants.EAnchorTypes. SW .ordinal()).setFrame(x-dx, y+h-dy,GEConstants. ANCHOR_W , GEConstants. ANCHOR_H );  
		anchors.get(GEConstants.EAnchorTypes. SS .ordinal()).setFrame(x+w/2-dx,y+h-dy, GEConstants. ANCHOR_W , GEConstants. ANCHOR_H );  
		anchors.get(GEConstants.EAnchorTypes. SE .ordinal()).setFrame(x+w-dx,  y+h-dy, GEConstants. ANCHOR_W , GEConstants. ANCHOR_H );  
		anchors.get(GEConstants.EAnchorTypes. RR .ordinal()).setFrame(x+w/2-dx, y-GEConstants. RR_OFFSET , GEConstants. ANCHOR_W , GEConstants. ANCHOR_H ); 
	} 
	
	// 마우스가 앵커위에 있다면 해당 앵커값을 반환
	public EAnchorTypes onAnchors(Point p){   
		for(Ellipse2D ellipse: anchors){   
			if(ellipse.contains(new Point(p))){  
				return EAnchorTypes.values()[anchors.indexOf(ellipse)]; 
			}  
		}  
		return EAnchorTypes.NONE ; 
	} 
	
	// 앵커들을 그림
	public void draw(Graphics2D g2D) { 
		for (int i=0; i < EAnchorTypes.values().length - 1; i++) {  
			Ellipse2D.Double ellipse = anchors.get(i); 
			g2D.setColor(GEConstants. ANCHOR_FILLCOLOR );   
			g2D.fill(ellipse); 
			g2D.setColor(GEConstants. ANCHOR_LINECOLOR );   
			g2D.draw(ellipse); 
		} 
	}
	
	// 앵커들을 반환
	public ArrayList<Ellipse2D.Double> getAnchors() {
		return anchors;
	}
	
}