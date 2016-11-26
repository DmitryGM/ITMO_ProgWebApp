import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
 
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.util.*;
 
public class DrawComponent extends JPanel {
   private static final int PREF_W = 800;
   private static final int PREF_H = 650;
   private static final int BORDER_GAP = 30;
   private static final Color GRAPH_COLOR = Color.green;
   private static final Color GRAPH_POINT_COLOR = new Color(150, 50, 50, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
   private static final int GRAPH_POINT_WIDTH = 12;
   private static final int SIZE_OF_HATCH = 20;
   private static final int SCALE_FACTOR = 3;
 
   private static final int BUFF = 70;
   
   private Vector<Punkt> points;
   private double radius;
 
   public DrawComponent(Vector<Punkt> p, double r) {
      this.points = p;
      this.radius = r;
   }
 
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      double lenOfInc = 10;
      int hatchCount = 10;
 
      double xScale = (getWidth() - 2 * BORDER_GAP - BUFF) / (radius*SCALE_FACTOR);
 
     
      hatchCount = (int)(SCALE_FACTOR * 2);
      System.out.println("width:" + getWidth() + " xScale" + xScale + "YHATCH:" + hatchCount);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP - BUFF) / (radius*SCALE_FACTOR);
      double scale = xScale>yScale?yScale:xScale;
      lenOfInc = scale/radius;
 
 
 
      // create x and y axes
  {    
      Point2D.Double p1 = new Point2D.Double(getWidth()/2, getHeight() - BORDER_GAP);
      Point2D.Double p2 = new Point2D.Double((getWidth())/2, BORDER_GAP);
      Point2D.Double p3 = new Point2D.Double(BORDER_GAP, getHeight()/2);
      Point2D.Double p4 = new Point2D.Double((getWidth() - BORDER_GAP), getHeight()/2);
      g2.draw(new Line2D.Double(p1,p2));
      g2.draw(new Line2D.Double(p3,p4));
  }
     
 
 
      // create hatch marks for y axis. R marks
      for (int i = 1; i <= hatchCount/2; i++) {
 
        Point2D.Double p1 = new Point2D.Double(getWidth()/2 - SIZE_OF_HATCH/2, getHeight()/2 + i*scale);
        Point2D.Double p2 = new Point2D.Double(getWidth()/2 + SIZE_OF_HATCH/2, getHeight()/2 + i*scale);
        g2.draw(new Line2D.Double(p1, p2));
 
        p1 = new Point2D.Double(getWidth()/2 - SIZE_OF_HATCH/2, getHeight()/2 - i*scale);
        p2 = new Point2D.Double(getWidth()/2 + SIZE_OF_HATCH/2, getHeight()/2 - i*scale);
        g2.draw(new Line2D.Double(p1, p2));
      }
 
 
      // and for x axis
      for (int i = 1; i <= hatchCount/2; i++) {
 
        Point2D.Double p1 = new Point2D.Double(getWidth()/2 + i*scale, getHeight()/2 - SIZE_OF_HATCH/2);
        Point2D.Double p2 = new Point2D.Double(getWidth()/2 + i*scale, getHeight()/2 + SIZE_OF_HATCH/2);
        g2.draw(new Line2D.Double(p1, p2));
 
        p1 = new Point2D.Double(getWidth()/2 - i*scale, getHeight()/2 - SIZE_OF_HATCH/2);
        p2 = new Point2D.Double(getWidth()/2 - i*scale, getHeight()/2 + SIZE_OF_HATCH/2);
        g2.draw(new Line2D.Double(p1, p2));
 
      }
 
        for (Iterator i = points.iterator(); i.hasNext(); ) {
            Punkt point = (Punkt)i.next();
            double x1 = (double) (point.getX() * lenOfInc + getWidth()/2 - GRAPH_POINT_WIDTH/2);
            double y1 = (double) (point.getY() * lenOfInc + getHeight()/2 - GRAPH_POINT_WIDTH/2);
            g2.fill(new Ellipse2D.Double(x1,y1, GRAPH_POINT_WIDTH, GRAPH_POINT_WIDTH));
        }
 
        //left triangle
        Path2D nw = new Path2D.Double();
        nw.moveTo(getWidth()/2,getHeight()/2);
        nw.lineTo(-scale/2+getWidth()/2, getHeight()/2);
        nw.lineTo(getWidth()/2,getHeight()/2 - scale/2);
        nw.append(new Arc2D.Double(getWidth()/2-scale/2, getHeight()/2 - scale/2, scale, scale, 90, -90, Arc2D.OPEN),false);
        //nw.append(new Rectangle2D.Double(getWidth()/2,getHeight()/2,scale,scale),true);
        nw.lineTo(getWidth()/2+scale,getHeight()/2);
        nw.lineTo(getWidth()/2+scale,getHeight()/2+scale);
        nw.lineTo(getWidth()/2,getHeight()/2+scale);
        nw.lineTo(getWidth()/2,getHeight()/2);
        nw.closePath();
        g2.fill(nw);
 
   }
 
   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }
 
   public static void createAndShowGui(Vector<Punkt> points, double radius) {
 
      DrawComponent mainPanel = new DrawComponent(points, radius);
 
      JFrame frame = new JFrame("DrawComponent");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }
}