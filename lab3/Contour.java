import java.lang.*;
import java.lang.Math.*;

class Contour
{
	private float r;

	public Contour(float r) {
		this.r = r;
	}

	public boolean isInContour(Spot s) {

		float x = (float) s.x;
		float y = (float) s.y;

		if (x < 0)
		{
			if (y < 0)
			{
				if (x > -r && y > -r) return true; // Square
			}

			if (y > 0)
			{
				if (y < 0.5f * (x + r)) return true; // Circle
			}

			if (y == 0)
			{
				if (x > -r) return true; // Interval
			}
		}

		if (x > 0)
		{
			if (y > 0)
			{
				if (x*x + y*y < r*r/4) return true; // Triangle
			}
		}

		if (x == 0)
		{
			if (y > 0 && y < r/2) return true; // Interval
		}

		return false;
	}

}
