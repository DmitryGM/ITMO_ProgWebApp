import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MainFrame extends JFrame
{
	public JList lst;
	
	private Plane plane;
	private GrahpicsPanel gp;
	private JPanel pnlRight;
	private Button btnAnswer;
	private Button btnChebyshev;
	private Button btnClear;
	private JSpinner jsp;
	private Label lbl;

	public MainFrame()
	{
		initFrame();
	}

	public void initFrame()
	{
		this.setTitle("Interpolation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //Open window in center of screen
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		this.plane = new Plane();
		this.gp = new GrahpicsPanel(plane);
		
		this.lst = new JList(new String[]{"y = sin(x)", "y = x + sin(x)", "y = (1/4)^x"});

		this.pnlRight = new JPanel();
		this.btnAnswer = new Button("Get answer");
		this.btnChebyshev = new Button("Chebyshev");
		this.btnClear = new Button("Clear");
		this.jsp = new JSpinner();
		this.lbl = new Label("Res: ");

		this.add(pnlRight, BorderLayout.EAST);
		this.add(gp, BorderLayout.CENTER);
		this.pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.PAGE_AXIS));
		this.pnlRight.add(lst);
		this.pnlRight.add(jsp);

		this.pnlRight.add(btnChebyshev);

		this.pnlRight.add(lbl);
		this.pnlRight.add(btnAnswer);
		this.pnlRight.add(btnClear);

		gp.addMouseListener(new MouseAdapter()	{
			public void mousePressed(MouseEvent e)
			{
				Point coursor = e.getPoint();
				
				// Transformation coords
				Point center = new Point(gp.getWidth() / 2, gp.getHeight() / 2);
				Point selectedPoint = new Point((int) coursor.getX() - (int) center.getX(), -((int) coursor.getY() - (int) center.getY()));

				// Create new circle
				Circle goodCircle = new Circle(coursor, 20);
				gp.addNewCircle(goodCircle);

				// Create new thread
				ThreadCircle threadCircle = new ThreadCircle(gp, goodCircle);
				threadCircle.start();
				
				// Create new point
				double x = selectedPoint.getX()/gp.getUnit();
				plane.addPointX(x);
				
				// Debug
				System.out.println("Component coords: x = " + coursor.getX() + ", y = " + coursor.getY());
				System.out.println("Component coords: x = " + selectedPoint.getX()/gp.getUnit() + ", y = " + selectedPoint.getY()/gp.getUnit());
			}
		});

		lst.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				plane.num = lst.getSelectedIndex();
				gp.repaint();
			}
		});

		btnAnswer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int x = (int) jsp.getValue();
				double y = 0;

				Vector<Double> pointVector = plane.xPointVector;

				for (int k = 0; k < pointVector.size(); k++)
				{
					double p = 1.0;

					for (int j = 0; j < pointVector.size(); j++)
					{
						if (j != k)
						{
							p *= x - pointVector.get(j);
							p /= pointVector.get(k) - pointVector.get(j);
						}
					}

					y += p * (Functions.func(pointVector.get(k), plane.num)); //f(x_k)
				}

				lbl.setText("y = " + y);
			}
		});

		btnChebyshev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				double a, b;
				a = -1;
				b = 1;

				int k = 10; // Count of points

				for (int m = 0; m < k; m++)
				{
					double xm = ( (b-a)*Math.cos((double)(2*m+1)/(double)(2*k)*Math.PI) + a + b )/2;
					plane.addPointX(xm);
				}
				
				gp.repaint();
			}
		});

		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				lbl.setText("Res: ");
				plane.xPointVector.clear();
				gp.repaint();
			}
		});

		this.setSize(600, 530);
	}
}
