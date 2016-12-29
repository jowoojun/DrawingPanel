package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GEConstants;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame {
	
	private GEDrawingPanel drawingpanel;
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	
	private GEMainFrame(String title){
		super(title);
		
		drawingpanel = new GEDrawingPanel();
		add(drawingpanel);
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		toolBar = new GEToolBar(GEConstants.TITLE_TOOLBAR);
		this.add(BorderLayout.NORTH, toolBar);
	}
	
	private static GEMainFrame uniqueMainFrame = 
			new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	
	
	public static GEMainFrame getInstance(){
		return uniqueMainFrame;
	}
	
	public void init(){
		toolBar.init(drawingpanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GEConstants.WIDTH_MAINFRAME,GEConstants.HEIGHT_MAINFRAME);
		this.setVisible(true);
	}
}
