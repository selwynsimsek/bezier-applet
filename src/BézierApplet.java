import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class BézierApplet extends java.applet.Applet implements MouseListener,MouseMotionListener
{
	private static final int MAX_DIST_SQ=16; // 2*2
	Point p1,p2,p3,p4,transitionPoint=null;
	int numDivisions;
	BézierCurve2D curve;
	BufferedImage offscreenImage;
	Graphics2D offscreen;
	public void init()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		numDivisions=Integer.parseInt(getParameter("numDivisions"));
		p1 = new Point(Integer.parseInt(getParameter("p1x")),Integer.parseInt(getParameter("p1y")));
		p2 = new Point(Integer.parseInt(getParameter("p2x")),Integer.parseInt(getParameter("p2y")));
		p3 = new Point(Integer.parseInt(getParameter("p3x")),Integer.parseInt(getParameter("p3y")));
		p4 = new Point(Integer.parseInt(getParameter("p4x")),Integer.parseInt(getParameter("p4y")));
		curve = new BézierCurve2D(p1,p2,p3,p4);
	}
	public void mousePressed(MouseEvent evt)
	{
		Point mousePoint = evt.getPoint();
		if(p1.distanceSq(mousePoint)<MAX_DIST_SQ)transitionPoint=p1;
		else if(p2.distanceSq(mousePoint)<MAX_DIST_SQ)transitionPoint=p2;
		else if(p3.distanceSq(mousePoint)<MAX_DIST_SQ)transitionPoint=p3;
		else if(p4.distanceSq(mousePoint)<MAX_DIST_SQ)transitionPoint=p4;
		repaint();
	}
	public void mouseExited(MouseEvent evt)
	{
		if(transitionPoint!=null)
		{
			transitionPoint=null;
		}
	}
	public void mouseEntered(MouseEvent evt){}
	public void mouseClicked(MouseEvent evt){}
	public void mouseDragged(MouseEvent evt)
	{
		if(transitionPoint!=null)
		{
			transitionPoint.x=evt.getX();
			transitionPoint.y=evt.getY();
		}
		repaint();
	}
	public void mouseMoved(MouseEvent evt){}
	public void mouseReleased(MouseEvent evt)
	{
		transitionPoint=null;
		repaint();
	}
	public void paint(Graphics g)
	{
		update(g);
	}
	public void update(Graphics g)
	{
		offscreenImage = (BufferedImage)createImage(getWidth(),getHeight());
		offscreen = offscreenImage.createGraphics();
		offscreen.setColor(Color.white);
		offscreen.clearRect(0,0,getWidth(),getHeight());
		offscreen.setColor(Color.red);
		float incr = 1.0f/numDivisions;
		Point point1 = curve.getParametricValue(0.0f);
		Point point2 = curve.getParametricValue(incr);
		offscreen.drawLine(point1.x,point1.y,point2.x,point2.y);
		for(float t = incr;t<1.0f;t+=incr)
		{
			point1=curve.getParametricValue(t);
			point2=curve.getParametricValue(t+incr);
			offscreen.drawLine(point1.x,point1.y,point2.x,point2.y);
		}
		offscreen.setColor(Color.black);
		drawPoint(p1.x,p1.y,offscreen);
		drawPoint(p2.x,p2.y,offscreen);
		drawPoint(p3.x,p3.y,offscreen);
		drawPoint(p4.x,p4.y,offscreen);
		offscreen.dispose();
		g.drawImage(offscreenImage,0,0,null);
	}
	private static void drawPoint(int x,int y,Graphics g)
	{
		g.fillOval(x-2,y-2,4,4);
	}
}

