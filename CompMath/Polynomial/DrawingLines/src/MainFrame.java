import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {

	public MainFrame()
	{
		initFrame();
		//resize(600, 401); // Странная констукция
	}

	public void initFrame()
	{
		//this.setSize(600, 400);
		this.setTitle("My first frame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		* DO_NOTHING_ON_CLOSE
		* HIDE_ON_CLOSE
		* DISPOSE_ON_CLOSE
		* EXIT_ON_CLOSE
		*/

		this.setLocationRelativeTo(null); //Open window in center of screen
		this.setVisible(true);
		this.setLayout(new BorderLayout());

		GrahpicsPanel grahpicsPanel = new GrahpicsPanel();
		grahpicsPanel.setBackground(Color.CYAN); // ?!


		grahpicsPanel.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				Point coursor = e.getPoint();
				System.out.println("Component coords: x = " + coursor.getX() + ", y = " + coursor.getY());

				//Преобразование координат
				Point center = new Point(grahpicsPanel.getWidth() / 2, grahpicsPanel.getHeight() / 2);
				Point selectedPoint = new Point((int) coursor.getX() - (int) center.getX(), -((int) coursor.getY() - (int) center.getY()));

				//Создаем новый круг
				Circle goodCircle = new Circle(coursor, 20);

				//Создаем новый поток
				ThreadCircle threadCircle = new ThreadCircle(grahpicsPanel, goodCircle); //!
				threadCircle.start(); //?!

				grahpicsPanel.addNewCircle(goodCircle);

				System.out.println("Component coords: x = " + selectedPoint.getX() + ", y = " + selectedPoint.getY());
			}
		});

		this.add(grahpicsPanel, BorderLayout.CENTER);

		JPanel panelRight = new JPanel();
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));

		Point myFavoritePoint = new Point();

		//JList:
		JList lst = new JList(new String[]{"y = sin(x)", "y = cos(x)", "y = x^3"});
		panelRight.add(lst);
		lst.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				/*
				switch (lst.getSelectedIndex()) {
					case 0:
						myFavoritePoint.x = grahpicsPanel.getUnit();
						break;
					case 1:
						myFavoritePoint.x = 2 * grahpicsPanel.getUnit();
						break;
					case 2:
						myFavoritePoint.x = -grahpicsPanel.getUnit();
						break;
					case 3:
						myFavoritePoint.x = -2 * grahpicsPanel.getUnit();
						break;
				}
				*/
			}
		});


		JTextField txt = new JTextField();
		this.add(txt, BorderLayout.SOUTH);

		this.add(panelRight, BorderLayout.EAST);

		JCheckBox checkBoxM = new JCheckBox();
		checkBoxM.setText("y <= 0");

		JCheckBox checkBox0 = new JCheckBox();
		checkBox0.setText("y += R");
		JCheckBox checkBox1 = new JCheckBox();
		checkBox1.setText("y += 2R");
		JCheckBox checkBox2 = new JCheckBox();
		checkBox2.setText("y += 4R");

		panelRight.add(checkBoxM);
		panelRight.add(checkBox0);
		panelRight.add(checkBox1);
		panelRight.add(checkBox2);

		//Button btn = new Button("Select Point");
		/*
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//x:
				lst.getSelectedIndex();
				System.out.println(lst.getSelectedIndex());

				switch (lst.getSelectedIndex()) {
					case 0:
						myFavoritePoint.x = grahpicsPanel.getUnit();
						break;
					case 1:
						myFavoritePoint.x = 2 * grahpicsPanel.getUnit();
						break;
					case 2:
						myFavoritePoint.x = -grahpicsPanel.getUnit();
						break;
					case 3:
						myFavoritePoint.x = -2 * grahpicsPanel.getUnit();
						break;
				}

				//y:
				int k = 0;
				if (checkBox0.isSelected()) k++;
				if (checkBox1.isSelected()) k += 2;
				if (checkBox2.isSelected()) k += 4;
				if (checkBoxM.isSelected()) k *= -1;

				myFavoritePoint.y = k * grahpicsPanel.getUnit();

				System.out.println("hello world!");
				txt.setText("Your point: (" + myFavoritePoint.getX() + ", " + myFavoritePoint.getY() + ")");
				repaint();


				// :
				// myFavoritePoint

				//Преобразование координат
				Point center = new Point(grahpicsPanel.getWidth() / 2, grahpicsPanel.getHeight() / 2);
				Point selectedPoint = myFavoritePoint; // Она и есть
				Point pointOnR2 = new Point((int) myFavoritePoint.getX() + (int) center.getX(), ((int) -myFavoritePoint.getY() + (int) center.getY()));

				//Создаем новый круг
				Circle goodCircle = new Circle(pointOnR2, 20);

				ThreadCircle threadCircle = new ThreadCircle(grahpicsPanel, goodCircle); //!
				threadCircle.start();

				grahpicsPanel.addNewCircle(goodCircle); //?

				System.out.println("Component coords: x = " + selectedPoint.getX() + ", y = " + selectedPoint.getY());
			}
		});
		panelRight.add(btn);
		*/

		JSpinner spinner = new JSpinner();
		spinner.setValue(20);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) spinner.getValue();
				if (value <= 2)
				{
					value = 20;
					spinner.setValue(value);
				}

				grahpicsPanel.setUnit(value);
				repaint();
			}
		});
		panelRight.add(spinner);

		this.setSize(600, 530);
	}
}
