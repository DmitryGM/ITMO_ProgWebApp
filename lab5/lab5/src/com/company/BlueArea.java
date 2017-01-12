package com.company;

import java.awt.*;

public class BlueArea {
	
	public static boolean isInArea(Point point, GrahpicsPanel gp) {
		
		int radius = gp.getRadius();
		int x = point.x;
		int y = point.y;

		// 1-quarter
		if (x >= 0 && y >= 0)
		{
			if (x <= radius && y <= radius / 2)
				return true;
		}

		// 3-quarter
		if (x <= 0 && y <= 0)
		{
			if (y >= -2*x - radius)
				return true;
		}

		// 4-quarter
		if (x >= 0 && y <= 0)
		{
			if (x*x + y*y <= radius*radius)
				return true;
		}

		return false;
	}

	public static Point pointToBlueArea(Point point, GrahpicsPanel gp) {
		
		// Transformation point to BlueArea
		
		Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
		Point transformPoint =  new Point((int) (point.getX() - center.getX()), -(int) (point.getY() - center.getY()));
		
		return transformPoint;
	}
}
