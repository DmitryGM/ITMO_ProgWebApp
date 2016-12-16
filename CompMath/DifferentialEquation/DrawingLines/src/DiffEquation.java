import java.util.Vector;

public class DiffEquation
{
	public Vector<Double> x = new Vector<Double>();
	public Vector<Double> y = new Vector<Double>();
	Vector<Double> y1 = new Vector<Double>(); //Производная y

	//Дано:
	double x0, y0; //Типа ввел пользователь

	public DiffEquation()
	{

	}

	double f(double x, double y)
	{
		return Math.cos(x);
	}

	public void setStart(double x0, double y0)
	{
		this.x0 = x0;
		this.y0 = y0;

		x.clear();
		y.clear();
		y1.clear();
	}

	public void solution()
	{
		double A, B, h, E;
		double k1, k2, k3, k4;

		//ДАНО:
		x.add(x0);
		y.add(y0);
		h = 1.0; //Типа ввел пользователь
		E = 0.001; //Типа ввел пользователь

		y1.add(0, f(x.get(0), y.get(0))); //Производная в точке (0); Не нужна вроде...

		//Первые 3 точки: (Метод Рунге-Кутта)
		for (int i = 1; i <= 3; i++)
		{
			k1 = h * f(x.get(i-1), y.get(i-1));
			k2 = h * f(x.get(i-1) + h/2, y.get(i-1) + k1/2);
			k3 = h * f(x.get(i-1) + h/2, y.get(i-1) + k2/2);
			k4 = h * f(x.get(i-1) + h, y.get(i-1) + k3);

			x.add(i, x.get(i-1) + h);
			y.add(i, y.get(i-1) + (k1 + 2*k2 + 2*k3 + k4)/6);

			y1.add(i, f(x.get(i), y.get(i)) ); //Производная в точке (i)
		}

		//Метод Милна:
		for(int i = 3; i < 10; i++)
		{
			// Делаем точку i+1
			y.add(i+1, y.get(i-3) + 4.0 / 3.0 * h * (2.0 * y1.get(i) - y1.get(i-1) + 2.0 * y1.get(i-2)));
			x.add(i+1, x.get(i) + h);

			y1.add(i+1, f(x.get(i+1), y.get(i+1))); // ?

			//Формула Симпсона:
			{
				B = y.get(i + 1);

				do
				{
					A = B;
					y1.set(i + 1, f(x.get(i + 1), A));

					B = y.get(i - 1) + h * (y1.get(i + 1) + 4 * y1.get(i) + y1.get(i - 1)) / 3;
				}
				while (Math.abs(A - B) >= E);

				y.set(i + 1, B);
			}
		}


	}

}
