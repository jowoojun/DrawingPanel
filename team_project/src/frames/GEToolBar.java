package frames;

import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GESelect;

public class GEToolBar extends JToolBar {
	private GEDrawingPanel drawingPanel;
	
	// GEToolBar의 생성자
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
	
	// 실행했을때 기본으로 클릭되어있을 도형
	public void clickDefault(){
		JRadioButton rbutton = (JRadioButton)this.getComponent(EToolBarButtons.Rectangle.ordinal());
		rbutton.doClick();
	}
	
	// GEToolBar를 초기화
	public void init(GEDrawingPanel dp){
		this.drawingPanel = dp;
		clickDefault();
	}
	
	// 버튼에 대한 행동을 조작
	private class ButtonHandler implements ActionListener{
		// 어떤 버튼이 눌렸을 때에 따라 각각의 그림 객체가 생성된다.
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
				drawingPanel.setCurrentShape(new GESelect());
		}
	}
}
