package frames;

import javax.swing.JFrame;

import constants.GEConstants;

public class GEMainFrame extends JFrame {
	
	private GEDrawingPanel drawingpanel;
	
	private GEMainFrame(String title){
		super(title);
		
		drawingpanel = new GEDrawingPanel();
		
		uniqueMainFrame.add(drawingpanel);
		
	}
	
	private static GEMainFrame uniqueMainFrame = 
			new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	
	
	
	
	public static GEMainFrame getInstance(){
		return uniqueMainFrame;
	}
	
	public void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GEConstants.WIDTH_MAINFRAME,GEConstants.HEIGHT_MAINFRAME);
		this.setVisible(true);
	}
}
