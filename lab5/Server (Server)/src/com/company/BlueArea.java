package com.company;

import java.awt.*;

public class BlueArea {
	
	public static boolean isInArea(Point point, double radius) {
		
		int x = point.x;
		int y = point.y;

		// 1-quarter +
		if (x >= 0 && y >= 0)
		{
			if (x*x + y*y <= radius*radius/4)
				return true;
		}
		
		// 2-quarter +
		if (x <= 0 && y >= 0)
		{
			if (x >= -radius/2 && y <= radius)
				return true;
		}

		// 3-quarter
		if (x <= 0 && y <= 0)
		{
			if (y + 2*x + radius >= 0)
				return true;
		}
		
		return false;
	}
}
