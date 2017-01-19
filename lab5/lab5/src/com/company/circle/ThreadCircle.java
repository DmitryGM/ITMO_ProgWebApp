package com.company.circle;

import com.company.BlueArea;
import com.company.GrahpicsPanel;
import com.company.client.Client;
import com.company.client.Convert;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class ThreadCircle extends Thread
{
	private Circle circle;
	private GrahpicsPanel gp;
	private Client client;
	

	public ThreadCircle(GrahpicsPanel gp, Circle circle, Client client) {
	
		this.gp = gp;
		this.circle = circle;
		this.client = client;
	}
	
	@Override
	public void run()
	{
		System.out.println("hello from run( in )"); // Debug

		if(circle != null) {
			Point cursor = circle.getCenterPoint();

			// Transformation coordinates:
			Point selectedPoint = BlueArea.pointToBlueArea(cursor, gp);

			// Define state
			Circle.State state = defineState(selectedPoint, gp.getRadius()); // Waiting...
			circle.setState(state);
			gp.repaint();


			if (state == Circle.State.Unknown) {
				gp.addUnknownCircle(circle);
			} else {
				// Redrawing:
				redrawing();
				gp.repaint();
			}
		}
		else
		{
			redrawing();
		}
		
		System.out.println("hello from run( out )"); // Debug
	}
	
	private synchronized void redrawing()
	{
		if (gp.circlesUnknown.isEmpty()) return;
		
		for (Iterator circleIterator = gp.circlesUnknown.iterator(); circleIterator.hasNext() ; )
		{
			Circle circle = (Circle) circleIterator.next();
			Point cursor = circle.getCenterPoint();
			
			// Transformation coordinates:
			Point selectedPoint = BlueArea.pointToBlueArea(cursor, gp);
			
			// Define state
			Circle.State state = defineState(selectedPoint, gp.getRadius()); // Waiting...
			circle.setState(state);
		}
		
		gp.circlesUnknown.clear();
	}
	
	private synchronized Circle.State defineState(Point point, int radius)
	{
		return client.send(Convert.toByteArray(point.getX(), point.getY(), (double)radius));
	}
}
