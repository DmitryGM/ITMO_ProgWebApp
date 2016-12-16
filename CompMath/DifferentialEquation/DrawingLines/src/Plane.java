import java.util.Vector;

public class Plane
{
	//Множество точек:
	public Vector<Double> xPointVector;
	public Vector<Double> yPointVector;

	//public int num; //Номер выбранной функции ?!!!

	public Plane()
	{
		xPointVector = new Vector<Double>();
		yPointVector = new Vector<Double>();
	}

	//Сюда бы было не плохо добавить функцию

    public double interpolation()
    {
        return 0;
    }

}
