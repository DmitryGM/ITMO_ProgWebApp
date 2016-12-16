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
	Plane plane;
	GrahpicsPanel gp;
	DiffEquation diff;

	public MainFrame()
	{
		initFrame();
	}

	public void initFrame()
	{
		this.setTitle("DifferentialEquation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); //Open window in center of screen
		this.setVisible(true);
		this.setLayout(new BorderLayout());

		plane = new Plane();

		diff = new DiffEquation();
		gp = new GrahpicsPanel(plane, diff);

		//lst = new JList(new String[]{"y = sin(x)", "y = x + sin(x)", "y = (1/4)^x"});

		JPanel pnlRight = new JPanel();
		Button btn = new Button("Get answer");
		Label label_x0 = new Label("x0:");
		Label label_y0 = new Label("y0:");
		JSpinner jsp_x0 = new JSpinner();
		JSpinner jsp_y0 = new JSpinner();
		JSpinner jsp3 = new JSpinner();
		Label lbl = new Label();
		lbl.setText("Res: ");

		this.add(pnlRight, BorderLayout.EAST);
		this.add(gp, BorderLayout.CENTER);
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.PAGE_AXIS));
		//pnlRight.add(lst);
		pnlRight.add(label_x0);
		pnlRight.add(jsp_x0);
		pnlRight.add(label_y0);
		pnlRight.add(jsp_y0);

		pnlRight.add(lbl);
		pnlRight.add(btn);

		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int value_X = (int) jsp_x0.getValue();
				double x0 = (double) value_X;

				int value_Y = (int) jsp_y0.getValue();
				double y0 = (double) value_Y;


				diff.setStart(x0, y0);
				diff.solution();
				gp.repaint();
			}
		});

		this.setSize(600, 530);
	}
}
