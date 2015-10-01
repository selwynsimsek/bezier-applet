import java.awt.Point;

public abstract class CubicCurve2D
{
	public abstract Jama.Matrix getBasisMatrix();
	public abstract Jama.Matrix getGeometryMatrix();
	public Point getParametricValue(float t)
	{
		Jama.Matrix basisMatrix = getBasisMatrix();
		Jama.Matrix geometryMatrix = getGeometryMatrix();
		Jama.Matrix result = geometryMatrix.times(basisMatrix);
		double[][] finalMat = new double[][]{{1.0f},{t},{t*t},{t*t*t}};
		result = result.times(new Jama.Matrix(finalMat));
		double[][] vector= result.getArray();
		double x =vector[0][0];
		double y =vector[1][0];
		double z =vector[2][0]; // should be zero
		return new Point((int)Math.round(x),(int)Math.round(y));
	}
}