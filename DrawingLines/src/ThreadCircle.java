import java.awt.*;

public class ThreadCircle extends Thread
{
	Circle circle;
	GrahpicsPanel gp;
	//!!!
	int radiusOld;

	public ThreadCircle(GrahpicsPanel gp, Circle circle)
	{
		this.gp = gp;
		this.circle = circle;
		this.radiusOld = gp.getRadius();
	}

	@Override
	public void run()
	{
		gp.repaint();
		try
		{
			Thread.sleep(9);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		//111
		Point coursor = circle.getCenterPoint();

		//Преобразование координат
		Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
		Point selectedPoint = new Point((int) coursor.getX() - (int) center.getX(), -((int) coursor.getY() - (int) center.getY()));

		//222

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



		while (BlueArea.isInArea(selectedPoint, gp.getRadius()))
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
			//Рисуем тут Круги)
			circle.setRadius(circle.getRadius() - 1); //Уменьшаем

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