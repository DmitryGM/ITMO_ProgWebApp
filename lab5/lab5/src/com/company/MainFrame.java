package com.company;

import com.company.circle.Circle;
import com.company.circle.ThreadCircle;
import com.company.client.Client;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainFrame extends JFrame {
	
	private Client client;
	
	private GrahpicsPanel gp;
	private JTextField txt;
	private JSpinner spinner;
	private Button btn;
	private JList lst;
	private JPanel panelRight;
	
	private JCheckBox checkBoxM;
	private JCheckBox checkBox0;
	private JCheckBox checkBox1;
	private JCheckBox checkBox2;
	
	private Locale locale;
	
	public MainFrame(Client client) {
		
		this.client = client;
		
		initFrame();
		
		// Locale:
		//this.locale = new Locale.Builder().setLanguage("en").build();
		//ResourceBundle bundle = ResourceBundle.getBundle("com.company.bundle.Bundle", locale);
		
		//this.btn.setLabel((String) bundle.getObject("Button"));
		//this.setTitle((String) bundle.getObject("Title"));
	}

	public void initFrame()	{
		
		this.setSize(600, 400);
		this.setTitle("lab5; var-20108");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //open window in center of screen
		this.setVisible(true);

		this.setLayout(new BorderLayout());

		gp = new GrahpicsPanel();
		txt = new JTextField();
		spinner = new JSpinner();
		spinner.setValue(100);

		btn = new Button("Select Point");
		lst = new JList(new String[]{"x = 1 R", "x = 2 R", "x = -1 R", "x = -2 R"});

		panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));

		this.add(panelRight, BorderLayout.EAST);
		this.add(txt, BorderLayout.SOUTH);
		this.add(gp, BorderLayout.CENTER);

		panelRight.add(lst);

		checkBoxM = new JCheckBox();
		checkBox0 = new JCheckBox();
		checkBox1 = new JCheckBox();
		checkBox2 = new JCheckBox();

		panelRight.add(checkBoxM);
		panelRight.add(checkBox0);
		panelRight.add(checkBox1);
		panelRight.add(checkBox2);

		panelRight.add(btn);
		panelRight.add(spinner);

		checkBoxM.setText("y <= 0");
		checkBox0.setText("y += R");
		checkBox1.setText("y += 2R");
		checkBox2.setText("y += 4R");

		// Events:
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Point selectedPoint = new Point();
				
				// get x:
				switch (lst.getSelectedIndex()) {
					case 0:
						selectedPoint.x = gp.getRadius();
						break;
					case 1:
						selectedPoint.x = 2 * gp.getRadius();
						break;
					case 2:
						selectedPoint.x = -gp.getRadius();
						break;
					case 3:
						selectedPoint.x = -2 * gp.getRadius();
						break;
				}

				// get y:
				int k = 0;
				if (checkBox0.isSelected()) k += 1;
				if (checkBox1.isSelected()) k += 2;
				if (checkBox2.isSelected()) k += 4;
				if (checkBoxM.isSelected()) k *= -1;
				
				selectedPoint.y = k * gp.getRadius();
				
				// Transformation coordinates:
				Point cursor = gp.pointToGP(selectedPoint);
				
				// Draw point:
				drawPoint(cursor);
				
				// Output:
				writeText(selectedPoint);
				
				// Debug:
				System.out.println("Component coords: x = " + selectedPoint.getX() + ", y = " + selectedPoint.getY());
			}
		});

		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = (Integer) spinner.getValue(); //helios_fix: int -> Integer
				if (value <= 2) spinner.setValue(100);

				gp.setRadius((Integer) spinner.getValue()); //helios_fix: int -> Integer
				repaint();
			}
		});

		gp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				Point cursor = e.getPoint();
				
				// Transformation coordinates:
				Point selectedPoint = BlueArea.pointToBlueArea(cursor, gp);
				
				// Draw point:
				drawPoint(cursor);
				
				// Output:
				writeText(selectedPoint);
				
				// Debug:
				System.out.println("Component coords: x = " + cursor.getX() + ", y = " + cursor.getY());
				System.out.println("Component coords: x = " + selectedPoint.getX() + ", y = " + selectedPoint.getY());
			}
		});
	}
	
	private void writeText(Point point)
	{
		// Output:
		txt.setText("Your point: (" + point.getX() + ", " + point.getY() + ")");
	}
	
	private void drawPoint(Point cursor)
	{
		// Create new Circle:
		Circle newCircle = new Circle(cursor, 5);
		
		// Create new Thread:
		ThreadCircle threadCircle = new ThreadCircle(gp, newCircle, client);
		threadCircle.start();
		
		gp.addNewCircle(newCircle);
	}
}
