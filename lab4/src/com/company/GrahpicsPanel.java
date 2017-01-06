package com.company;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class GrahpicsPanel extends JPanel {

	private int radius;
	private Vector<Circle> circleVector;

	{
		circleVector = new Vector<Circle>();
		radius = 100;
	}
	public GrahpicsPanel() {}

	public GrahpicsPanel(int radius)
	{
		this.radius = radius;
	}

	public void addNewCircle(Circle newCircle)
	{
		circleVector.add(newCircle);
	}

	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	public int getRadius()
	{
		return this.radius;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		this.setBackground(new Color(255, 255, 153));

		Graphics2D g2 = (Graphics2D) g;

		double length = 0.7 * getHeight();
		Point center = new Point(getWidth() / 2, getHeight() / 2);

		//BlueArea:
		g2.setColor(new Color(32, 132, 67));

		for (int x = (int)(-length/2); x <= length/2; x++)
		{
			for (int y = (int) (-length / 2); y <= length / 2; y++)
			{
				if (BlueArea.isInArea(new Point(x, y), this))
				{
					g2.fill(new Circle((int) center.getX() + x, (int) center.getY() - y, 1));
				}
			}
		}

		//Circles:

		for (Iterator circleIterator = circleVector.iterator(); circleIterator.hasNext() ; )
		{
			Circle circle = (Circle) circleIterator.next();

			if(BlueArea.isInArea(BlueArea.pointToBlueArea(circle.getCenterPoint(), this), this))
			{
				g2.setColor(Color.CYAN);
			}
			else
			{
				g2.setColor(Color.PINK);
			}

			g2.fill(circle);
		}

		//Lines:
		Line lineOY = new Line(center.getX(), center.getY() - length/2, center.getX(), center.getY() + length/2);
		Line lineOX = new Line(center.getX() - length/2, center.getY(), center.getX() + length/2, center.getY());

		g2.setColor(Color.BLACK);
		g2.draw(lineOY);
		g2.draw(lineOX);

		int N = (int)length / 2 / (radius / 2);

		for (int k = 1; k <= N; k++)
		{
			if(radius/2 * k > length/2 - 10 ) break;

			g2.setColor(Color.BLACK);
			g2.draw(new Line(center.getX() + radius*k/2, center.getY() - 3, center.getX() + radius*k/2, center.getY() + 3));
			g2.draw(new Line(center.getX() - radius*k/2, center.getY() - 3, center.getX() - radius*k/2, center.getY() + 3));

			g2.draw(new Line(center.getX() - 3, center.getY() + radius*k/2, center.getX() + 3, center.getY() + radius*k/2));
			g2.draw(new Line(center.getX() - 3, center.getY() - radius*k/2, center.getX() + 3, center.getY() - radius*k/2));
		}

		System.out.println("!"); //debug
	}

}