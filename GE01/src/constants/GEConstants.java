package constants;

public class GEConstants {
	//GEMainFrame
	public static final int WIDTH_MAINFRAME = 400;
	public static final int HEIGHT_MAINFRAME = 600;
	public static final String TITLE_MAINFRAME = "Graphic Editor - 01";
	
}
/* enum..
	public enum Eweek{~~~~~};
	
	public static void main(string[] args){
		for(Eweek week : Eweek.values()){
		system.out.println("week.name() +  ""+week.ordinal());
		}
	}
}
Eweek week : Eweek.values() -> enum에 있는 전체 값들
name -> enum에 있는 이름
ordinal -> enum에 있는 숫자

------------------------>>>> 메뉴를 구성할때 enum을 사용할꺼임!!!
*/

/* menu
setJmenubar <----> setmenubar 조심!!!
setIcon(사진추가) 메소드를 사용하기 위해, 또 setAccelerator(단축키)를 쓰기 위해 메뉴의 구성요소를 필드(변수로)선언해줌....
addseparateor -> 분리선..
*/