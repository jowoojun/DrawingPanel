package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EFileMenuItems;

public class GEFileMenu extends JMenu {
	public GEFileMenu(String label){
		super(label);
		
		for(EFileMenuItems btn : EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			add(menuItem);
		}
	}
}
