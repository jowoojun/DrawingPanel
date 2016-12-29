package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import shapes.GEGroup;
import constants.GEConstants.EEditMenuItems;
import frames.GEDrawingPanel;

public class GEEditMenu extends JMenu {
	private GEDrawingPanel drawingPanel;
//	private JOptionPane thetaInputMessage;
	
	// GEEditMenu의 생성자
	public GEEditMenu(String label){
		super(label);
		
		for(EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.addActionListener(new EditMenuHandler());
			menuItem.setActionCommand(btn.toString());
			add(menuItem);
		}
	}
	
	// GEEditMenu를 초기화
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}

	// 메뉴바에서 해당 메뉴가 눌렸을때 각각의 함수가 실행되게함
	private class EditMenuHandler implements ActionListener{
	  @Override
		public void actionPerformed(ActionEvent e){
			switch(EEditMenuItems.valueOf(e.getActionCommand())){
			case group : 
				group(); 
				break;     
			case ungroup : 
				ungroup(); 
				break;
			case 도형회전 : 
				rotate(); 
				break;
			default:
				break;
			}
		}

	  // ungroup가 선택됬을때
	  private void ungroup() {  
		  drawingPanel.ungroup(); 
	  }
	  
	  // group가 선택됬을때
	  private void group() { 
		  drawingPanel.group(new GEGroup());
	  }
	  private void rotate() {
		  String result = JOptionPane.showInputDialog("각도를 입력해주세요.");
		  if(result != null){
			  drawingPanel.setRotate(result);
		  }
	  }

	}	
}
