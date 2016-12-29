package frames;

import javax.swing.JFrame;

import constants.GEConstants;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame {
	
	private GEDrawingPanel drawingpanel;
	private GEMenuBar menuBar;
	
	private GEMainFrame(String title){
		super(title);
		
		drawingpanel = new GEDrawingPanel();
		add(drawingpanel);
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
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
