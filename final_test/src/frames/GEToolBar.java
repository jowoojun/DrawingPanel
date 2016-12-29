package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;

public class GEToolBar extends JToolBar {
	private GEDrawingPanel drawingPanel;
	private GEToolBarHandler toolBarHandler;
	
	public GEToolBar(String label){
		super(label);
		
		toolBarHandler = new GEToolBarHandler();
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rButton = null;
		
		for(EToolBarButtons btn : EToolBarButtons.values()){
			rButton = new JRadioButton();
			rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + 
					btn.toString() + GEConstants.TOOLBAR_BTN));
			
			if(btn.toString().equals(EToolBarButtons.WidthUp.name()) || btn.toString().equals(EToolBarButtons.HeightUP.name())){
				rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN));
			}
			else{
				rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.TOOLBAR_BTN_SLT));
			}
			
			rButton.setActionCommand(btn.toString());
			rButton.addActionListener(toolBarHandler);
			
			this.add(rButton);
			buttonGroup.add(rButton);
		}
	}
	
	public void init(GEDrawingPanel dp){
		this.drawingPanel = dp;
		this.cliciDefaultButton();
	}
	
	private void cliciDefaultButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(
				EToolBarButtons.Rectangle.ordinal());
		rButton.doClick();
	}
	
	private class GEToolBarHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton button = (JRadioButton)e.getSource();
			if(button.getActionCommand().equals(
					EToolBarButtons.Rectangle.name())){
				drawingPanel.setCurrentShape(new GERectangle());
			}else if(button.getActionCommand().equals(
					EToolBarButtons.Ellipse.name())){
				drawingPanel.setCurrentShape(new GEEllipse());
			}else if(button.getActionCommand().equals(
					EToolBarButtons.Line.name())){
				drawingPanel.setCurrentShape(new GELine());
			}else if(button.getActionCommand().equals(
					EToolBarButtons.Polygon.name())){
				drawingPanel.setCurrentShape(new GEPolygon());
			}else if(button.getActionCommand().equals(
					EToolBarButtons.Select.name())){
				drawingPanel.setCurrentShape(null);
			}else if(button.getActionCommand().equals(
					EToolBarButtons.WidthUp.name())){
				drawingPanel.widthUp();
			}else if(button.getActionCommand().equals(
					EToolBarButtons.HeightUP.name())){
				drawingPanel.HeightUp();
			}
			
			
		}
	}
}
