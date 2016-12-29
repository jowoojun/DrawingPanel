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
	
	// GEEditMenu�� ������
	public GEEditMenu(String label){
		super(label);
		
		for(EEditMenuItems btn : EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem(btn.toString());
			menuItem.addActionListener(new EditMenuHandler());
			menuItem.setActionCommand(btn.toString());
			add(menuItem);
		}
	}
	
	// GEEditMenu�� �ʱ�ȭ
	public void init(GEDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}

	// �޴��ٿ��� �ش� �޴��� �������� ������ �Լ��� ����ǰ���
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
			case ����ȸ�� : 
				rotate(); 
				break;
			default:
				break;
			}
		}

	  // ungroup�� ���É�����
	  private void ungroup() {  
		  drawingPanel.ungroup(); 
	  }
	  
	  // group�� ���É�����
	  private void group() { 
		  drawingPanel.group(new GEGroup());
	  }
	  private void rotate() {
		  String result = JOptionPane.showInputDialog("������ �Է����ּ���.");
		  if(result != null){
			  drawingPanel.setRotate(result);
		  }
	  }

	}	
}
