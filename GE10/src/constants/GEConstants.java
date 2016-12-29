// 상수들을 저장하는 class
package constants;

import java.awt.Color;

public class GEConstants {
	//GEMainFrame
	public static final int WIDTH_MAINFRAME = 400; // 메인프레임의 넓이
	public static final int HEIGHT_MAINFRAME = 600; // 메인프레임의 높이
	public static final String TITLE_MAINFRAME = "Graphic Editor - 10"; // 메인프레임의 이름
	
	//GEMenu
	public static final String TITLE_FILEMENU = "파일"; // 메뉴바의 파일메뉴이름
	public static final String TITLE_EDITMENU = "편집"; // 메뉴바의 편집메뉴이름
	public static final String TITLE_COLORMENU = "컬러"; // 메뉴바의 컬러메뉴이름
	
	//GEMenuItems
	public static enum EFileMenuItems {새로만들기, 열기, 저장, 
		다른이름으로저장, 종료} // 파일메뉴에 들어갈 item들
	public static enum EEditMenuItems {undo, redo, 삭제, 잘라내기, 복사,
		붙이기, group, ungroup} // 편집메뉴에 들어갈 item들
	public static enum EColorMenuItems {SetLineColor, ClearLineColor, 
		SetFillColor, ClearFillColor} // 컬러메뉴에 들어갈 item들
	
	//GEToolBar
	public static final String TITLE_TOOLBAR = "Shape Tools"; // 그림 그리는 툴바의 이름
	public static enum EToolBarButtons{ Select, Rectangle, Line, Ellipse, Polygon} // 툴바에서의 기능들
	public static final String IMG_URL = "images/"; // img의 url
	public static final String TOOLBAR_BTN = ".gif"; // img의 확장자명
	public static final String TOOLBAR_BTN_SLT = "SLT.gif"; // 선택된 경우의 img의 확장자명

	// GEDraingPanel
	public static final Color FOREGROUND_COLOR = Color.BLACK; // 패널의 그림 선색 
	public static final Color BACKGROUND_COLOR = Color.white; // 패널의 배경 색
	public static enum EState {Idle, TwoPointsDrawing, NPointsDrawing} // 현재 그리는 그림의 상태
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

