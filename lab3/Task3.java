import java.lang.*;
import java.lang.Math.*;
import java.util.*;

public class Task3 {

	public static void main(String[] args)
	{
		Vector points = new Vector();

		points.add(new Spot(0, 0));
		points.add(new Spot(3, -5));
		points.add(new Spot(3, 3));
		points.add(new Spot(-2, 0));
		points.add(new Spot(2, 1));
		points.add(new Spot(-3, -5));
		points.add(new Spot(-4, 5));
		points.add(new Spot(-1, 1));
		points.add(new Spot(-2, 0));

		float r = getRadius();
		Contour contour = new Contour(r);

		for (Object point : points)
		{
			Spot sp = (Spot) point;

			if (contour.isInContour(sp))
			{
				System.out.println("Point (" + sp.x + ", " + sp.y + ") belongs to an open set");
			}
			else
			{
				System.out.println("Point (" + sp.x + ", " + sp.y + ") doesn't belong to an open set");
			}
		}
	}

	private static float getRadius()
	{
		float r;
		Scanner sc = new Scanner(System.in);

		System.out.println("Please, input R");

		while (!sc.hasNextFloat())
		{
		    System.out.println("Please, input real number!");
		    sc.nextLine();
		}
		
		r = sc.nextFloat();

		if (r < 0) r *= -1;
		System.out.println("R = " + r);
		return r;
	}
}
