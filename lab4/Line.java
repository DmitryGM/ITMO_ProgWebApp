import java.awt.geom.Rectangle2D;

public class Line extends Line2D {

	private Point p1;
	private Point p2;

	public Line() {

	}

	public double getX2() {
		return p2.getX();
	}

	public Point2D getP1() {
		return p1;
	}

	public Point2D getP2() {
		return p2;
	}
}