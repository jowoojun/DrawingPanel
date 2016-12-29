package menus;

import javax.swing.JMenuBar;

import constants.GEConstants;
import frames.GEDrawingPanel;

public class GEMenuBar extends JMenuBar {
	private GEFileMenu fileMenu;
	private GEEditMenu editMenu;
	private GEColorMenu colorMenu;
	
	// GEMenuBar의 생성자
	public GEMenuBar(){
		super();
		
		fileMenu = new GEFileMenu(GEConstants.TITLE_FILEMENU);
		add(fileMenu);
		editMenu = new GEEditMenu(GEConstants.TITLE_EDITMENU);
		add(editMenu);
		colorMenu = new GEColorMenu(GEConstants.TITLE_COLORMENU);
		add(colorMenu);
	}
	
	// GEMenuBar를 초기화
	public void init(GEDrawingPanel drawingPanel){
		colorMenu.init(drawingPanel);
		editMenu.init(drawingPanel);
	}
}
