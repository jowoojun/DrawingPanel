package frames;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;

public class GEToolBar extends JToolBar {
	public GEToolBar(String label){
		super(label);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rbutton = null;
		
		for(EToolBarButtons btn : EToolBarButtons.values()){
			rbutton = new JRadioButton();
			rbutton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN));
			
			rbutton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN_SLT));
			
			this.add(rbutton);
			buttonGroup.add(rbutton);
		}
	}
}
