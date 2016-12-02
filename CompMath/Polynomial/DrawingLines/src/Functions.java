import java.util.Vector;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class Functions
{
	static double func(double x, int num)
	{
		// Init TypeFunction:
		Vector<TypeFunction> vectorFun = new Vector<TypeFunction>();

		vectorFun.add( new TypeFunction()
					   {
						   @Override
						   public double func(double x)
						   {
							   return sin(x);
						   }
					   }
		);

		vectorFun.add( new TypeFunction()
					   {
						   @Override
						   public double func(double x)
						   {
							   return sin(x) + x;
						   }
					   }
		);

		vectorFun.add( new TypeFunction()
					   {
						   @Override
						   public double func(double x)
						   {
							   return pow(0.25, x);
						   }
					   }
		);

		return vectorFun.get(num).func(x);
	}
}
