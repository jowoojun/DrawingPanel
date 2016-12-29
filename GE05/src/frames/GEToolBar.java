package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;

public class GEToolBar extends JToolBar {
	private GEDrawingPanel drawingPanel;
	private GEToolBarHandler toolBarHandler;
	
	public GEToolBar(String label){
		super(label);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rbutton = null;
		GEToolBarHandler toolBarHandler = new GEToolBarHandler();
		
		for(EToolBarButtons btn : EToolBarButtons.values()){
			rbutton = new JRadioButton();
			rbutton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN));
			
			rbutton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN_SLT));
			rbutton.setActionCommand(btn.name());
			rbutton.addActionListener(toolBarHandler);
			this.add(rbutton);
			buttonGroup.add(rbutton);
		}
	}
	
	public void clickDefaltButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(
				EToolBarButtons.Rectangle.ordinal());
		rButton.doClick();
	}
		
	public void init(GEDrawingPanel dp){
		this.drawingPanel = dp;
		this.clickDefaltButton();
	}
	
	private class GEToolBarHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JRadioButton btn = (JRadioButton)e.getSource();
	
			if(btn.getActionCommand().equals(EToolBarButtons.Rectangle.name())){
				drawingPanel.setSelectShape(EToolBarButtons.Rectangle);
			}else if(btn.getActionCommand().equals(EToolBarButtons.Ellipse.name())){
				drawingPanel.setSelectShape(EToolBarButtons.Ellipse);
			}else if(btn.getActionCommand().equals(EToolBarButtons.Line.name())){
				drawingPanel.setSelectShape(EToolBarButtons.Line);
			}
		}
	}
}
