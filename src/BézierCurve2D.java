import java.awt.Point;

public class B�zierCurve2D extends CubicCurve2D
{
	Point p1,p2,p3,p4;
	public B�zierCurve2D()
	{
		this(new Point(),new Point(),new Point(),new Point());
	}
	public B�zierCurve2D(Point p1,Point p2,Point p3,Point p4)
	{
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
		this.p4=p4;
	}
	public Jama.Matrix getBasisMatrix()
	{
		double[][] array = new double[][]{
			{1.0,-3.0,3.0,-1.0},
			{0.0,3.0,-6.0,3.0},
			{0.0,0.0,3.0,-3.0},
			{0.0,0.0,0.0,1.0}
		};
		return new Jama.Matrix(array);
	}
	public Jama.Matrix getGeometryMatrix()
	{
		double[][] array = new double[][]{
			{p1.x,p2.x,p3.x,p4.x},
			{p1.y,p2.y,p3.y,p4.y},
			{0.0,0.0,0.0,0.0}
		};
		return new Jama.Matrix(array);
	}
}