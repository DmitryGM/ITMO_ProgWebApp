import java.awt.*;

public class ThreadCircle extends Thread
{
	Circle circle;
	GrahpicsPanel gp;

	public ThreadCircle(GrahpicsPanel gp, Circle circle)
	{
		this.gp = gp;
		this.circle = circle;
	}

	@Override
	public void run()
	{
		Point cursor = circle.getCenterPoint();

		//Преобразование координат
		Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
		Point selectedPoint = new Point((int) cursor.getX() - (int) center.getX(), -((int) cursor.getY() - (int) center.getY()));

		//Drawing

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

		gp.repaint(); //?!
	}
}