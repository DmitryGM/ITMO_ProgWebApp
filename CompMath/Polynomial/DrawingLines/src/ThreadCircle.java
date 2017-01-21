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
		// Drawing
		while(circle.getRadius() >= 0)
		{
			// Deawing circles
			circle.setRadius(circle.getRadius() - 1);

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
		
		gp.removeCircle(circle);
		gp.repaint();
	}
}