package frames;

public class GELauncher {
	// 그림판을 실행한다.
	public static void main(String[] args){
		GEMainFrame frame = GEMainFrame.getInstance();
		frame.init();
	}
}