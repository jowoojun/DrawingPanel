// ������� �����ϴ� class
package constants;

import java.awt.Color;

public class GEConstants {
	//GEMainFrame
	public static final int WIDTH_MAINFRAME = 400; // ������������ ����
	public static final int HEIGHT_MAINFRAME = 600; // ������������ ����
	public static final String TITLE_MAINFRAME = "Graphic Editor - 10"; // ������������ �̸�
	
	//GEMenu
	public static final String TITLE_FILEMENU = "����"; // �޴����� ���ϸ޴��̸�
	public static final String TITLE_EDITMENU = "����"; // �޴����� �����޴��̸�
	public static final String TITLE_COLORMENU = "�÷�"; // �޴����� �÷��޴��̸�
	
	//GEMenuItems
	public static enum EFileMenuItems {���θ����, ����, ����, 
		�ٸ��̸���������, ����} // ���ϸ޴��� �� item��
	public static enum EEditMenuItems {undo, redo, ����, �߶󳻱�, ����,
		���̱�, group, ungroup} // �����޴��� �� item��
	public static enum EColorMenuItems {SetLineColor, ClearLineColor, 
		SetFillColor, ClearFillColor} // �÷��޴��� �� item��
	
	//GEToolBar
	public static final String TITLE_TOOLBAR = "Shape Tools"; // �׸� �׸��� ������ �̸�
	public static enum EToolBarButtons{ Select, Rectangle, Line, Ellipse, Polygon} // ���ٿ����� ��ɵ�
	public static final String IMG_URL = "images/"; // img�� url
	public static final String TOOLBAR_BTN = ".gif"; // img�� Ȯ���ڸ�
	public static final String TOOLBAR_BTN_SLT = "SLT.gif"; // ���õ� ����� img�� Ȯ���ڸ�

	// GEDraingPanel
	public static final Color FOREGROUND_COLOR = Color.BLACK; // �г��� �׸� ���� 
	public static final Color BACKGROUND_COLOR = Color.white; // �г��� ��� ��
	public static enum EState {Idle, TwoPointsDrawing, NPointsDrawing} // ���� �׸��� �׸��� ����
	public static final Color COLOR_LINE_DEFAULT = Color.black;
	public static final Color COLOR_FILL_DEFAULT = Color.white;
	
	//GEAnchorList
	public static final int ANCHOR_W = 6;
	public static final int ANCHOR_H = 6;
	public static final int RR_OFFSET = 30;
	public static final Color ANCHOR_LINECOLOR = Color.black;
	public static final Color ANCHOR_FILLCOLOR = Color.white;
	public static enum EAnchorTypes { NW, NN, NE, WW, EE, SW, SS, SE, RR, NONE}
	
	//GEMenuColor
	public static final String FILL_COOR_TITLE = "Select fill color";
	public static final String LINE_COLOR_TITLE = "Select line color";

	// GETransformer
	public static final int DEFAULT_DASH_OFFSET = 4;
	public static final int DEFAULT_DASHEDLINE_WIDTH = 1;
		
}

