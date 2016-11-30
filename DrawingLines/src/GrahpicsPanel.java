import javax.swing.JPanel;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class GrahpicsPanel extends JPanel {

	private int radius;
	private Vector<Circle> vector;

	public GrahpicsPanel() {
		vector = new Vector<Circle>();
		radius = 100;

		//Good!
		//addNewCircle(new Circle(100, 100, 200));
		//addNewCircle(new Circle(10, 10, 20));
	}

	public GrahpicsPanel(int radius) {
		this.radius = radius;
	}

	public void addNewCircle(Circle newCircle)
	{
		vector.add(newCircle);
	}

	public void setRadius(int radius) {

		this.radius = radius;
	}

	public int getRadius() {
		return this.radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		//super.paint(g); // ?!
		//setBackground(Color.GREEN);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle(0, 0, this.getWidth(), this.getHeight()));

		double l = 0.7 * getHeight();

		Point center = new Point(getWidth() / 2, getHeight() / 2);
		Line lineOY = new Line(center.getX(), center.getY() - l/2, center.getX(), center.getY() + l/2);
		Line lineOX = new Line(center.getX() - l/2, center.getY(), center.getX() + l/2, center.getY());

		g2.setColor(Color.YELLOW);

		for (int x = (int)(-l/2); x <= l/2; x++)
		{
			for (int y = (int)(-l/2); y <= l/2; y++)
			{
				if (BlueArea.isInArea(new Point(x, y), radius))
				{
					g2.fill(new Circle((int)center.getX() + x, (int)center.getY() - y, 1));
				}
			}
		}

		//Circles:

		for (Iterator circleIterator = vector.iterator(); circleIterator.hasNext() ; )
		{
			Circle circle = (Circle) circleIterator.next();

			if(circle.isRight()) {
				g2.setColor(Color.MAGENTA);
			}
			else
			{
				g2.setColor(Color.PINK);
			}

			g2.fill(circle);
		}

		//int[] xpoints = {-radius / 2, 0, 0, -radius / 2};
		//int[] ypoints = {0, 0, radius, 0};

		//Polygon triangle = new Polygon(xpoints, ypoints, 3);
		//g2.setColor(Color.BLUE);
		//g2.draw(triangle);

		/*
		 g2.setColor(Color.BLUE);
		 g2.draw(rectangle);

		 g2.setColor(Color.RED);
		 g2.fill(new Rectangle(270, 300, 75, 111));

		 g2.draw(new Line(10, 10, 333, 333));

		 g2.setColor(Color.YELLOW);
		 g2.fill(new Circle(10, 10, 10));
		*/

		g2.setColor(Color.BLACK);
		g2.draw(lineOY);
		g2.draw(lineOX);

		int N = (int)l / 2 / (radius / 2);

		for (int k = 1; k <= N; k++)
		{
			if(radius/2 * k > l/2 - 10 ) break;

			g2.setColor(Color.BLACK);
			g2.draw(new Line(center.getX() + radius*k/2, center.getY() - 3, center.getX() + radius*k/2, center.getY() + 3));
			g2.draw(new Line(center.getX() - radius*k/2, center.getY() - 3, center.getX() - radius*k/2, center.getY() + 3));

			g2.draw(new Line(center.getX() - 3, center.getY() + radius*k/2, center.getX() + 3, center.getY() + radius*k/2));
			g2.draw(new Line(center.getX() - 3, center.getY() - radius*k/2, center.getX() + 3, center.getY() - radius*k/2));
		}

		System.out.println("!");
	}


}