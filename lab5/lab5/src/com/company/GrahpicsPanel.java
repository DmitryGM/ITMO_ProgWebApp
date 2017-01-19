package com.company;

import com.company.circle.Circle;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class GrahpicsPanel extends JPanel {

	private int unit;
	public Vector<Circle> circles;
	public Vector<Circle> circlesUnknown; // Unknown circles

	{
		circles = new Vector<Circle>();
		circlesUnknown = new Vector<Circle>();
		unit = 100;
	}
	public GrahpicsPanel() {}

	public GrahpicsPanel(int unit) {
		
		this.unit = unit;
	}

	public void addNewCircle(Circle newCircle) {
		
		circles.add(newCircle);
	}
	
	public void addUnknownCircle(Circle circle)
	{
		circlesUnknown.add(circle);
	}

	public void setRadius(int unit) {
		
		this.unit = unit;
	}

	public int getRadius() {
		
		return this.unit;
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		this.setBackground(new Color(255, 255, 153));

		Graphics2D g2 = (Graphics2D) g;

		double length = 0.7 * getHeight();
		Point center = new Point(getWidth() / 2, getHeight() / 2);

		// BlueArea:
		g2.setColor(new Color(32, 132, 67));

		for (int x = (int)(-length/2); x <= length/2; x++)
		{
			for (int y = (int) (-length / 2); y <= length / 2; y++)
			{
				Point point = new Point(x, y);
				
				if (BlueArea.isInArea(point, this))
				{
					g2.fill(new Circle(pointToGP(point), 1));
				}
			}
		}

		// Circles:
		for (Iterator circleIterator = circles.iterator(); circleIterator.hasNext() ; )
		{
			Circle circle = (Circle) circleIterator.next();
			
			if (circle.getState() == Circle.State.Unknown)
				g2.setColor(Color.GRAY);
			
			if (circle.getState() == Circle.State.InArea)
				g2.setColor(Color.CYAN);
			
			if (circle.getState() == Circle.State.OutArea)
				g2.setColor(Color.PINK);
			
			g2.fill(circle);
		}

		// Lines:
		Line lineOY = new Line(center.getX(), center.getY() - length/2, center.getX(), center.getY() + length/2);
		Line lineOX = new Line(center.getX() - length/2, center.getY(), center.getX() + length/2, center.getY());

		g2.setColor(Color.BLACK);
		g2.draw(lineOY);
		g2.draw(lineOX);

		int N = (int)length / 2 / (unit / 2);

		for (int k = 1; k <= N; k++)
		{
			if ( unit/2 * k > length/2 - 10 ) break;

			g2.setColor(Color.BLACK);
			g2.draw(new Line(center.getX() + unit*k/2, center.getY() - 3, center.getX() + unit*k/2, center.getY() + 3));
			g2.draw(new Line(center.getX() - unit*k/2, center.getY() - 3, center.getX() - unit*k/2, center.getY() + 3));

			g2.draw(new Line(center.getX() - 3, center.getY() + unit*k/2, center.getX() + 3, center.getY() + unit*k/2));
			g2.draw(new Line(center.getX() - 3, center.getY() - unit*k/2, center.getX() + 3, center.getY() - unit*k/2));
		}

		System.out.println("!"); // Debug
	}
	
	public Point pointToGP(Point point)
	{
		int x = point.x;
		int y = point.y;
		
		Point center = new Point(getWidth() / 2, getHeight() / 2);
		Point pointGP =  new Point((int) center.getX() + x, (int) center.getY() - y);
		
		return pointGP;
	}
}