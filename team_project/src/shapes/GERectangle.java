package shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class GERectangle extends GEShape{
	// GERectangle�� ������
	public GERectangle(){
		super(new Rectangle());
	}
	
	// �ʱ� ������ ����
	public void initDraw(Point startP){
		this.startP = startP;
	}

	
	// �׸��� �����ϸ鼭 �׸�
	public void setCoordinate(Point currentP){
		Rectangle rectangle = (Rectangle)myShape;
		rectangle.setBounds(startP.x, startP.y, currentP.x - startP.x, currentP.y - startP.y);
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// ������� ������ Ŭ���� ��ȯ
	@Override
	public GEShape clone(){
		return new GERectangle();
	}
}
