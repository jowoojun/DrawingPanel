package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EColorMenuItems;

public class GEColorMenu extends JMenu {
	public GEColorMenu(String label){
		super(label);
		
		for(EColorMenuItems btn : EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			add(menuItem);
		}
	}
}
