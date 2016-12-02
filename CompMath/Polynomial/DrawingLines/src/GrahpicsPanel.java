import javax.swing.JPanel;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

import static java.lang.Math.PI;
import static java.lang.Math.*;

public class GrahpicsPanel extends JPanel {

	private int unit;
	private Vector<Circle> circleVector;

	private Plane plane;
	//public int num; //!

	public GrahpicsPanel(Plane plane)
	{
		circleVector = new Vector<Circle>();
		unit = 20;
		//num = 0;
		this.plane = plane;
	}

	public void addNewCircle(Circle newCircle)
	{
		circleVector.add(newCircle);
	}

	public void setUnit(int unit)
	{
		this.unit = unit;
	}

	public int getUnit()
	{
		return this.unit;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		setBackground(Color.WHITE);

		Graphics2D g2 = (Graphics2D) g;

		//Lines:
		double length = this.getWidth() < this.getHeight() ? this.getWidth() : this.getHeight();
		length *= 0.8; //80% of screen

		Point center = new Point(getWidth() / 2, getHeight() / 2);
		Line lineOY = new Line(center.getX(), center.getY() - length / 2, center.getX(), center.getY() + length / 2);
		Line lineOX = new Line(center.getX() - length / 2, center.getY(), center.getX() + length / 2, center.getY());
		//

		g2.setColor(Color.BLACK);
		g2.draw(lineOY);
		g2.draw(lineOX);

		int N = (int) length / 2 / unit;

		for (int k = 1; k <= N; k++)
		{
			g2.setColor(Color.BLACK);
			g2.draw(new Line(center.getX() + unit * k, center.getY() - 2, center.getX() + unit * k, center.getY() + 2));
			g2.draw(new Line(center.getX() - unit * k, center.getY() - 2, center.getX() - unit * k, center.getY() + 2));

			g2.draw(new Line(center.getX() - 2, center.getY() + unit * k, center.getX() + 2, center.getY() + unit * k));
			g2.draw(new Line(center.getX() - 2, center.getY() - unit * k, center.getX() + 2, center.getY() - unit * k));
		}

		//Function:

		//f(x) = sin(x) + x

		g2.setColor(Color.GREEN);
		for (int x1 = (int)(-length/2); x1 <= length/2; x1++)
		{
			double x = (double) x1 / (double) unit;
			double y = Functions.func(x, plane.num); // sin(x) + x;
			int y1 = (int) (y * unit);
			g2.fill(new Circle((int)center.getX() + x1, (int)center.getY() - y1, 1)); //Странный способ ставить точки
		}

		//fi(x) = Pn(x)
		g2.setColor(Color.MAGENTA);
		
		//FUN:

		Vector<Double> pointVector = new Vector<Double>(); //plane.xPointVector
		pointVector.add(PI*0/4);
		pointVector.add(PI*1/4);
		pointVector.add(PI*2/4);
		pointVector.add(PI*3/4);
		pointVector.add(PI*4/4);

		int x1Last = 0, y1Last = 0;

		for (int x1 = (int)(-length/2); x1 <= length/2; x1++)
		{
			double x = (double) x1 / (double) unit;
			double y = 0;

			for (int k = 0; k < pointVector.size(); k++)
			{
				double p = 1.0;

				for (int j = 0; j < pointVector.size(); j++)
				{
					if (j != k)
					{
						p *= x - pointVector.get(j);
						p /= pointVector.get(k) - pointVector.get(j);
					}
				}

				y += p*(sin(pointVector.get(k)) + pointVector.get(k)); //f(x_k)
			}

			int y1 = (int) (y * unit);
			g2.draw(new Line((int)center.getX() + x1Last, (int)center.getY() - y1Last, (int)center.getX() + x1, (int)center.getY() - y1));

			x1Last = x1;
			y1Last = y1;
		}


		//Circles:
		for (Iterator circleIterator = circleVector.iterator(); circleIterator.hasNext(); )
		{
			Circle circle = (Circle) circleIterator.next();

			g2.setColor(Color.RED);
			g2.fill(circle);
		}

		System.out.println("paint(!2)");
	}
}
