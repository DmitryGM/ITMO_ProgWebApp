import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Ellipse2D {

	private Point point; // center of circle
	private double radius;

	public Circle() {
		point = new Point();
	}

	public Circle(int x, int y, double radius) {
		point = new Point(x, y);
		this.radius = radius;
	}

	public Circle(Point point,  double radius) {
		this.point = point;
		this.radius = radius;
	}

	public double getRadius()
	{
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Point getCenterPoint()
	{
		return point;
	}

	@Override
	public double getX()
	{
		return point.getX() - radius;
	}

	@Override
	public double getY()
	{
		return point.getY() - radius;
	}

	@Override
	public double getWidth()
	{
		return 2*radius;
	}

	@Override
	public double getHeight()
	{
		return 2*radius;
	}

	@Override
	public boolean isEmpty()
	{
		return false;
	}

	@Override
	public void setFrame(double x, double y, double w, double h)
	{
		point.setLocation(x, y);
		this.radius = w / 2;
	}

	@Override
	public Rectangle2D getBounds2D()
	{
		return null;
	}
}
