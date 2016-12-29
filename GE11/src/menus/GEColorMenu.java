package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants;
import constants.GEConstants.EColorMenuItems;
import frames.GEDrawingPanel;

public class GEColorMenu extends JMenu {
	
	private GEDrawingPanel drawingPanel;
	
	public GEColorMenu(String label){
		super(label);
		
		for(EColorMenuItems btn : EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.setActionCommand(btn.toString());
			menuItem.addActionListener(new ColorMenuHandler());
			add(menuItem);
		}
	}
	
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	private class ColorMenuHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e){
			switch(EColorMenuItems.valueOf(e.getActionCommand())){
			case SetLineColor:
				setLineColor();
				break;				
			case SetFillColor:
				setFillColor();
				break;
			case ClearLineColor:
				clearLineColor();
				break;
			case ClearFillColor:
				ClearFileColor();
				break;
			}
		}
	}
	public void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, GEConstants.LINE_COLOR_TITLE, null);
		drawingPanel.setLineColor(lineColor);
	}
	public void ClearFileColor() {
		drawingPanel.setFillColor(GEConstants.COLOR_FILL_DEFAULT);
	}
	public void clearLineColor() {
		drawingPanel.setLineColor(GEConstants.COLOR_LINE_DEFAULT);
		
	}
	public void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null, GEConstants.FILL_COOR_TITLE, null);
		drawingPanel.setFillColor(fillColor);
	}
}
