import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame
{
	Plane plane;
	GrahpicsPanel gp;
	public JList lst;

	public MainFrame()
	{
		initFrame();
	}

	public void initFrame()
	{
		this.setSize(600, 530);
		this.setTitle("Interpolation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //Open window in center of screen
		this.setVisible(true);
		this.setLayout(new BorderLayout());

		plane = new Plane();
		gp = new GrahpicsPanel(plane);

		lst = new JList(new String[]{"y = sin(x)", "y = x + sin(x)", "y = (1/4)^x"});

		JPanel pnlRight = new JPanel();
		//Button btn = new Button("Select Point");

		this.add(pnlRight, BorderLayout.EAST);
		this.add(gp, BorderLayout.CENTER);
		pnlRight.add(lst);
		//pnlRight.add(btn);

		gp.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				Point coursor = e.getPoint();
				System.out.println("Component coords: x = " + coursor.getX() + ", y = " + coursor.getY());

				//Преобразование координат
				Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
				Point selectedPoint = new Point((int) coursor.getX() - (int) center.getX(), -((int) coursor.getY() - (int) center.getY()));

				//Создаем новый круг
				Circle goodCircle = new Circle(coursor, 20);

				//Создаем новый поток
				ThreadCircle threadCircle = new ThreadCircle(gp, goodCircle); //!
				threadCircle.start(); //?!

				double x = selectedPoint.getX()/gp.getUnit();

				boolean isInCollection = false;
				for (int i = 0; i < plane.xPointVector.size(); i++)
				{
					if(plane.xPointVector.get(i) == x)
						isInCollection = true;
				}

				gp.addNewCircle(goodCircle);

				//Добавляю точку x
				if(!isInCollection)
					plane.xPointVector.add(x);

				System.out.println("Component coords: x = " + selectedPoint.getX()/gp.getUnit() + ", y = " + selectedPoint.getY()/gp.getUnit());
			}
		});

		lst.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				plane.num = lst.getSelectedIndex();
				gp.repaint();
			}
		});
	}
}
