package shapes;

import java.awt.Point;
import java.awt.geom.Line2D;

public class GELine extends GEShape{
	// GELine�� ������
	public GELine(){
		super(new Line2D.Double());
	}
	
	// �ʱ� ������ ����
	public void initDraw(Point startP){
		this.startP = startP;
	}
	
	// �׸��� �����ϸ鼭 �׸�
	public void setCoordinate(Point currentP){
		Line2D line = (Line2D)myShape;
		line.setLine(startP.x, startP.y, currentP.x, currentP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// ������� ������ Ŭ���� ��ȯ
	@Override
	public GEShape clone(){
		return new GELine();
	}
}
