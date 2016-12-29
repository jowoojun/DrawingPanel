package menus;

import javax.swing.JMenuBar;

import constants.GEConstants;

public class GEMenuBar extends JMenuBar {
	private GEFileMenu fileMenu;
	private GEEditMenu editMenu;
	private GEColorMenu colorMenu;
	
	public GEMenuBar(){
		super();
		
		fileMenu = new GEFileMenu(GEConstants.TITLE_FILEMENU);
		add(fileMenu);
		editMenu = new GEEditMenu(GEConstants.TITLE_EDITMENU);
		add(editMenu);
		colorMenu = new GEColorMenu(GEConstants.TITLE_COLORMENU);
		add(colorMenu);
	}
}
