import java.awt.*;

public class BlueArea {

	public static boolean isInArea(Point point, int radius)
	{
		int x = point.x;
		int y = point.y;

		// 1
		if (x >= 0 && y >= 0)
		{
			if (x <= radius && y <= radius / 2)
				return true;
		}

		// 3
		if (x <= 0 && y <= 0)
		{
			if (y >= -2*x - radius)
				return true;
		}

		// 4
		if (x >= 0 && y <= 0)
		{
			if (x*x + y*y <= radius*radius)
				return true;
		}

		return false;
	}

}
