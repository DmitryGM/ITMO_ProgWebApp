package com.company;

import java.awt.*;

public class ThreadCircle extends Thread
{
	private Circle circle;
	private GrahpicsPanel gp;
	private int radiusOld;

	public ThreadCircle(GrahpicsPanel gp, Circle circle) {
	
	this.gp = gp;
	this.circle = circle;
	this.radiusOld = gp.getRadius();
}

	@Override
	public void run() {
		
		gp.repaint();
		Point cursor = circle.getCenterPoint();

		// Transformation coordinates
		Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
		Point selectedPoint = new Point((int) cursor.getX() - (int) center.getX(), -((int) cursor.getY() - (int) center.getY()));

		while (gp.getRadius() == radiusOld)
		{
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		while (BlueArea.isInArea(selectedPoint, gp))
		{
			System.out.println("0");

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		while(circle.getRadius() >= 0)
		{
			// Draw circle
			circle.setRadius(circle.getRadius() - 1);

			gp.repaint();

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}

		circle.setRadius(0);

		gp.repaint();
	}
}