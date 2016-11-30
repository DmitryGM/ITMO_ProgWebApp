import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D {

	private Point point;
	private double width;
	private double height;

	public Rectangle() {
		point = new Point();
	}

	public Rectangle(int x, int y, int width, int height) {
		point = new Point(x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void setRect(double x, double y, double width, double height)
	{
		point.setLocation(x, y);
		this.width = width;
		this.height = height;
	}

	@Override
	public int outcode(double x, double y)
	{
		return 0;
	}

	@Override
	public Rectangle2D createIntersection(Rectangle2D r)
	{
		// Пересечение 2х прямоугольников
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D r)
	{
		// Объединение 2х прямоугольников
		return null;
	}

	@Override
	public double getX()
	{
		return point.getX();
	}

	@Override
	public double getY()
	{
		return point.getY();
	}

	@Override
	public double getWidth()
	{
		return this.width;
	}

	@Override
	public double getHeight()
	{
		return this.height;
	}

	@Override
	public boolean isEmpty()
	{
		return (height <= 0 || width <= 0);
	}
}
