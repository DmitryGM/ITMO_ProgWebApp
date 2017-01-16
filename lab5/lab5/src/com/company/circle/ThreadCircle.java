package com.company.circle;

import com.company.BlueArea;
import com.company.GrahpicsPanel;

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
	public void run()
	{
		gp.repaint();
		Point cursor = circle.getCenterPoint();

		// Transformation coordinates:
		Point selectedPoint = gp.pointToGP(cursor);

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