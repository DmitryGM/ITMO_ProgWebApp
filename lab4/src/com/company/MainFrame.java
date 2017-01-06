package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
	
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
	
	private Point selectedTextPoint;
	
	
	public MainFrame()
	{
		initFrame();
	}

	public void initFrame()
	{
		this.setSize(600, 400);
		this.setTitle("lab4; var-20108");
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
		this.add(gp, BorderLayout.CENTER);
		this.add(txt, BorderLayout.SOUTH);

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

		//Кнопка, которую выбрали через интерфейс
		selectedTextPoint = new Point();

		//Events:
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//x:
				lst.getSelectedIndex();
				System.out.println(lst.getSelectedIndex());

				switch (lst.getSelectedIndex()) {
					case 0:
						selectedTextPoint.x = gp.getRadius();
						break;
					case 1:
						selectedTextPoint.x = 2 * gp.getRadius();
						break;
					case 2:
						selectedTextPoint.x = -gp.getRadius();
						break;
					case 3:
						selectedTextPoint.x = -2 * gp.getRadius();
						break;
				}

				//y:
				int k = 0;
				if (checkBox0.isSelected()) k++;
				if (checkBox1.isSelected()) k += 2;
				if (checkBox2.isSelected()) k += 4;
				if (checkBoxM.isSelected()) k *= -1;

				selectedTextPoint.y = k * gp.getRadius();

				System.out.println("hello world!");
				txt.setText("Your point: (" + selectedTextPoint.getX() + ", " + selectedTextPoint.getY() + ")");
				repaint();


				// :
				// selectedTextPoint

				//Преобразование координат
				Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
				Point selectedPoint = selectedTextPoint; // Она и есть
				Point pointOnR2 = new Point((int) selectedTextPoint.getX() + (int) center.getX(), ((int) -selectedTextPoint.getY() + (int) center.getY()));

				//Создаем новый круг
				Circle goodCircle = new Circle(pointOnR2, 5);

				//Новый поток
				ThreadCircle threadCircle = new ThreadCircle(gp, goodCircle); //!
				threadCircle.start();

				gp.addNewCircle(goodCircle); //?

				System.out.println("Component coords: x = " + selectedPoint.getX() + ", y = " + selectedPoint.getY());
			}
		});

		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e)
			{
				int value = (int) spinner.getValue();
				if (value <= 2) spinner.setValue(100);

				gp.setRadius((int) spinner.getValue());
				repaint();
			}
		});

		gp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				Point cursor = e.getPoint();
				System.out.println("Component coords: x = " + cursor.getX() + ", y = " + cursor.getY());

				//Transformation coordinates
				Point bluePoint = BlueArea.pointToBlueArea(cursor, gp);

				txt.setText("Your point: (" + bluePoint.getX() + ", " + bluePoint.getY() + ")");

				//Create new Circle:
				Circle newCircle = new Circle(cursor, 5);

				//Create new Thread:
				ThreadCircle threadCircle = new ThreadCircle(gp,  newCircle);
				threadCircle.start();

				gp.addNewCircle( newCircle);

				System.out.println("Component coords: x = " + bluePoint.getX() + ", y = " + bluePoint.getY()); //debug
			}
		});
	}
}
