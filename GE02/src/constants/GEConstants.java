package constants;

public class GEConstants {
	//GEMainFrame
	public static final int WIDTH_MAINFRAME = 400;
	public static final int HEIGHT_MAINFRAME = 600;
	public static final String TITLE_MAINFRAME = "Graphic Editor - 02";
	
	//GEMenu
	public static final String TITLE_FILEMENU = "파일"; //
	public static final String TITLE_EDITMENU = "편집"; //
	public static final String TITLE_COLORMENU = "컬러"; //
	
	//GEMenuItems
	public static enum EFileMenuItems {새로만들기, 열기, 저장, 
		다른이름으로저장, 종료}
	public static enum EEditMenuItems {undo, redo, 삭제, 잘라내기, 복사,
		붙이기, group, ungroup}
	public static enum EColorMenuItems {setLineColor, ClearLineColor, 
		SetFillColor, ClearFileColor}
}

