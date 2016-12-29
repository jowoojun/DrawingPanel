package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EEditMenuItems;

public class GEEditMenu extends JMenu {
	public GEEditMenu(String label){
		super(label);
		
		for(EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			add(menuItem);
		}
	}
}
