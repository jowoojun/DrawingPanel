package shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GEPolygon extends GEShape {
	// GEPolygon�� ������
	public GEPolygon(){
		super(new Polygon());
	}
	
	// �ʱ� ������ ����
	@Override
	public void initDraw(Point startP) {
		((Polygon)myShape).addPoint(startP.x, startP.y);
	}
	
	// �ٰ����� �� ���� �ϳ��� �߰��ϸ鼭 �׸���.
	public void continueDrawing(Point currentP){	
		((Polygon)myShape).addPoint(currentP.x,currentP.y);
	}

	// �׸��� �����ϸ鼭 �׸�
	@Override
	public void setCoordinate(Point currentP) {
		Polygon tempPolygon = (Polygon)myShape;
		tempPolygon.xpoints[tempPolygon.npoints - 1] = currentP.x;
		tempPolygon.ypoints[tempPolygon.npoints - 1] = currentP.y;
		if(anchorList != null){
			anchorList.setPosition(myShape.getBounds());
		}
	}
	
	// ������� ������ Ŭ���� ��ȯ
	@Override
	public GEShape clone() {
		return new GEPolygon();
	}
}
