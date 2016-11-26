import java.awt.*;
import javax.swing.JFrame;
import java.lang.*;
import java.lang.Math.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();

		frame.setSize(600, 400);
		frame.setTitle("My first frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		* DO_NOTHING_ON_CLOSE
		* HIDE_ON_CLOSE
		* DISPOSE_ON_CLOSE
		* EXIT_ON_CLOSE
		*/

		frame.setLocationRelativeTo(null); //center of screen
		frame.setVisible(true);
		//frame.setLayout(new ) //Нужно поставить какой-то Layout ...

		GrahpicsPanel grahpicsPanel = new GrahpicsPanel();

		frame.add(grahpicsPanel);


		/*
		MyJFrame frame2 = new MyJFrame();
     	frame2.setTitle("Drawing Graphics in Frames");
      	frame2.setBounds(100, 50, 500, 300);
      	frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      	frame2.setVisible(true);
      	*/
	}


}
