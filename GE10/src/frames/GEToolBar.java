package frames;

import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import Shapes.GEEllipse;
import Shapes.GELine;
import Shapes.GEPolygon;
import Shapes.GERectangle;
import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;

public class GEToolBar extends JToolBar {
	private GEDrawingPanel drawingPanel;
	public GEToolBar(String label){
		super(label);
		
		JRadioButton rbutton;
		ButtonGroup group = new ButtonGroup();
		ButtonHandler buttonHandler = new ButtonHandler();
		
		for(EToolBarButtons btn : EToolBarButtons.values()){
			rbutton = new JRadioButton();
			rbutton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN));
			rbutton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN_SLT));
			
			rbutton.setActionCommand(btn.toString());
			rbutton.addActionListener(buttonHandler);
			add(rbutton);
			group.add(rbutton);
		}
	}
	public void clickDefault(){
		JRadioButton rbutton = (JRadioButton)this.getComponent(EToolBarButtons.Rectangle.ordinal());
		rbutton.doClick();
	}
	
	public void init(GEDrawingPanel dp){
		this.drawingPanel = dp;
		clickDefault();
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JRadioButton rbutton = (JRadioButton)e.getSource();
			if(rbutton.getActionCommand().equals(EToolBarButtons.Rectangle.name()))
				drawingPanel.setCurrentShape(new GERectangle());
			else if(rbutton.getActionCommand().equals(EToolBarButtons.Ellipse.name()))
				drawingPanel.setCurrentShape(new GEEllipse());
			else if(rbutton.getActionCommand().equals(EToolBarButtons.Line.name()))
				drawingPanel.setCurrentShape(new GELine());
			else if(rbutton.getActionCommand().equals(EToolBarButtons.Polygon.name()))
				drawingPanel.setCurrentShape(new GEPolygon());
			else if(rbutton.getActionCommand().equals(EToolBarButtons.Select.name()))
				drawingPanel.setCurrentShape(null);
		}
	}
}
