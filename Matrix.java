package mcengine;

public class Matrix {
	private Vector[] matrix = new Vector[3];
	
	public Vector[] getMatrix()
	{
		return this.matrix;
	}
	
	Matrix(Vector a, Vector b, Vector c)
	{
		this.matrix[0] = a;
		this.matrix[1] = b;
		this.matrix[2] = c;
	}
	public Matrix transpose()
	{
		return(new Matrix(new Vector(this.matrix[0].getX(),this.matrix[1].getX(),this.matrix[2].getX())
						 ,new Vector(this.matrix[0].getY(),this.matrix[1].getY(),this.matrix[2].getY())
						 ,new Vector(this.matrix[0].getZ(),this.matrix[1].getZ(),this.matrix[2].getZ())));
	}
	public Matrix mult(Matrix A)
	{
		return(new Matrix(new Vector(this.matrix[0].mult(A.transpose().matrix[0]),this.matrix[0].mult(A.transpose().matrix[1]),this.matrix[0].mult(A.transpose().matrix[2]))
						 ,new Vector(this.matrix[1].mult(A.transpose().matrix[0]),this.matrix[1].mult(A.transpose().matrix[1]),this.matrix[1].mult(A.transpose().matrix[2]))
						 ,new Vector(this.matrix[2].mult(A.transpose().matrix[0]),this.matrix[2].mult(A.transpose().matrix[1]),this.matrix[2].mult(A.transpose().matrix[2]))));
	}
	public void print()
	{
		for(int i = 0; i < 3; i ++)
		{
			System.out.println(this.matrix[i].getX()+" "+this.matrix[i].getY()+" "+this.matrix[i].getZ());
		}
	}
	public Vector mult(Vector a)
	{
		return(new Vector(this.matrix[0].mult(a),this.matrix[1].mult(a),this.matrix[2].mult(a)));
	}
}
