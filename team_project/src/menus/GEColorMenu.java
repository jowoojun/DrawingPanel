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
	
	// GEColorMenu의 생성자
	public GEColorMenu(String label){
		super(label);
		
		for(EColorMenuItems btn : EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.setActionCommand(btn.toString());
			menuItem.addActionListener(new ColorMenuHandler());
			add(menuItem);
		}
	}
	
	// GEColorMenu를 초기화
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	// 메뉴바에서 해당 메뉴가 눌렸을때 각각의 함수가 실행되게함
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
	
	// setLineColor가 선택됬을때
	public void setLineColor() {
		Color lineColor = JColorChooser.showDialog(null, GEConstants.LINE_COLOR_TITLE, null);
		drawingPanel.setLineColor(lineColor);
	}
	
	// ClearFillColor가 선택됬을때
	public void ClearFileColor() {
		drawingPanel.setFillColor(GEConstants.COLOR_FILL_DEFAULT);
	}
	
	// ClearLineColor가 선택됬을때
	public void clearLineColor() {
		drawingPanel.setLineColor(GEConstants.COLOR_LINE_DEFAULT);
		
	}
	
	// setFillColor가 선택됬을때
	public void setFillColor() {
		Color fillColor = JColorChooser.showDialog(null, GEConstants.FILL_COOR_TITLE, null);
		drawingPanel.setFillColor(fillColor);
	}
}
