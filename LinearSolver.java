package mcengine;
import java.util.Arrays;


public class LinearSolver {
	public static double getDet2x2(double[][] matrix)
	{
		return(matrix[0][0]*matrix[1][1]-matrix[1][0]*matrix[0][1]);
	}
	public static double getDet3x3(double[][] matrix)
	{
		
		double num=matrix[0][0]*getDet2x2(new double[][] {{matrix[1][1],matrix[1][2]},{matrix[2][1],matrix[2][2]}});
		num-=matrix[1][0]*getDet2x2(new double[][] {{matrix[0][1],matrix[0][2]},{matrix[2][1],matrix[2][2]}});
		num+=matrix[2][0]*getDet2x2(new double[][] {{matrix[0][1],matrix[0][2]},{matrix[1][1],matrix[1][2]}});
		return num;
	}
	
	public static double[] solveEquation(double[][] m)
	{
		double det = getDet3x3(m);
		double[] solVector = new double[4];
		double[][] xm = new double[3][4];
		double[][] ym = new double[3][4];
		double[][] zm = new double[3][4];
	
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				xm[i][j] = m[i][j];
				ym[i][j] = m[i][j];
				zm[i][j] = m[i][j];
			}
		}
		for(int i = 0; i < 3; i++)
		{
			xm[i][0] = xm[i][3];
			ym[i][1] = ym[i][3];
			zm[i][2] = zm[i][3];
		}
		
		solVector[0] = det;
		solVector[1] = det==0?0:getDet3x3(xm)/det;
		solVector[2] = det==0?0:getDet3x3(ym)/det;
		solVector[3] = det==0?0:getDet3x3(zm)/det;
		
		return(solVector);
	}
	static void printMatrix(double[][] matrix)
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				System.out.print(matrix[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	
	

	public static void main(String[] args) {

		

	}

}
