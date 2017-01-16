package com.company.circle;

import com.company.BlueArea;
import com.company.GrahpicsPanel;
import com.company.client.Client;
import com.company.client.Convert;

import java.awt.*;

public class ThreadCircle extends Thread
{
	private Circle circle;
	private GrahpicsPanel gp;
	private int radiusOld;
	private Client client;

	public ThreadCircle(GrahpicsPanel gp, Circle circle, Client client) {
	
		this.gp = gp;
		this.circle = circle;
		this.client = client;
		this.radiusOld = gp.getRadius();
	}
	
	@Override
	public void run()
	{
		System.out.println("hello from run( in )"); // Debug
		
		Point cursor = circle.getCenterPoint();
		
		// Transformation coordinates:
		Point selectedPoint = BlueArea.pointToBlueArea(cursor, gp);
		
		// Define state
		circle.setState(defineState(selectedPoint)); //waiting...
		
		gp.repaint();
		System.out.println("hello from run( out )"); // Debug
	}
	
	private Circle.State defineState(Point point)
	{
		return client.send(Convert.toByteArray(point.getX(), point.getY()));
	}
}