package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.undo.UndoManager;

import constants.GEConstants;
import menus.GEMenuBar;

public class GEMainFrame extends JFrame {
	private GEDrawingPanel drawingPanel;
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	
	// GEMainFrame의 생성자
	private GEMainFrame(String label){
		super(label);
		
		drawingPanel = new GEDrawingPanel();
		add(drawingPanel);
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		toolBar = new GEToolBar(GEConstants.TITLE_TOOLBAR);
		add(BorderLayout.NORTH, toolBar);
	}
	
	// GEMainFrame의 객체를 생성한다.
	private static GEMainFrame uniqueFrame = new GEMainFrame(GEConstants.TITLE_MAINFRAME);
	
	// 생성한 객체를 반환
	public static GEMainFrame getInstance(){
		return uniqueFrame;
	}
	
	// GEMainFrame을 초기화
	public void init(){
		toolBar.init(drawingPanel);
		menuBar.init(drawingPanel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(GEConstants.WIDTH_MAINFRAME, GEConstants.HEIGHT_MAINFRAME);
	}
}
