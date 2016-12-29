package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import menus.GEMenuBar;
import constants.GEConstants;

public class GEMainFrame extends JFrame{
	
	private static GEMainFrame uniqueMainFrame = 
			new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	private GEDrawingPanel drawingPanel;
	private GEMenuBar menuBar;
	private GEToolBar toolBar;

	private GEMainFrame(String title){
		super(title);
		
		drawingPanel = new GEDrawingPanel();
		add(drawingPanel);		
		
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		toolBar = new GEToolBar(GEConstants.TITLE_TOOLBAR);
		this.add(BorderLayout.NORTH, toolBar);
	}
	
	public static GEMainFrame getInstance(){
		return uniqueMainFrame;
	}
	
	public void init(){
		toolBar.init(drawingPanel);
		menuBar.init(drawingPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GEConstants.WIDTH_MAINFRAME, 
				GEConstants.HEIGHT_MAINFRAME);
		this.setVisible(true);	
	}
}
