import java.util.Vector;

//Interpolation
public class Plane
{
	public Vector<Double> xPointVector; //Точки x
	public int num; //Номер выбранной функции

	public Plane()
	{
		num = 0;
		xPointVector = new Vector<Double>();
	}

	public boolean addPointX(double x)
	{
		if (xPointVector.contains(x))
		{
			return false;
		}
		else
		{
			xPointVector.add(x);
			return true;
		}
	}

	//Сюда бы было не плохо добавить функцию

    public double interpolation()
    {
        return 0;
    }

}
