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
	private DiffEquation ode;

	public GrahpicsPanel(Plane plane, DiffEquation ode)
	{
		circleVector = new Vector<Circle>();
		unit = 20;
		this.plane = plane;
		this.ode = ode;
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


		
		//Множество точек
		Vector<Double> pointVectorX = ode.x;
		Vector<Double> pointVectorY = ode.y;


		//!
		double x1Last = 0;
		double y1Last = 0;
		boolean firstPoint = true;

		for (int x1 = (int)(-length/2); x1 <= length/2; x1++)
		{
			double x = (double) x1 / (double) unit;
			double y = 0;

			for (int k = 0; k < pointVectorX.size(); k++)
			{
				double p = 1.0;

				for (int j = 0; j < pointVectorX.size(); j++)
				{
					if (j != k)
					{
						p *= x - pointVectorX.get(j);
						p /= pointVectorX.get(k) - pointVectorX.get(j);
					}
				}

				y += p * pointVectorY.get(k); //?
			}

			int y1 = (int) (y * unit);

			if(!firstPoint)
				g2.draw(new Line((int)center.getX() + x1Last, (int)center.getY() - y1Last, (int)center.getX() + x1, (int)center.getY() - y1));
			else
				firstPoint = false;

			x1Last = x1;
			y1Last = y1;
		}
		//!


		//Points:
		//pointVector
		for (int k = 0; k < pointVectorX.size(); k++)
		{
			double x = pointVectorX.get(k);
			int x1 = (int) (x*unit);
			double y = pointVectorY.get(k);
			int y1 = (int) (y*unit);

			Circle circle = new Circle((int) (center.getX()) + x1, (int) (center.getY() - y1), 3);
			g2.setColor(Color.RED);
			g2.fill(circle);
		}

	}
}
