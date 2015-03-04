package mcengine;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class R3 {
	ArrayList<Triangle> triList = new ArrayList<Triangle>();
	
	public void addTri(double x1, double y1, double z1
					  ,double x2, double y2, double z2
					  ,double x3, double y3, double z3,Color color)
	{
		triList.add(new Triangle(x1,y1,z1,x2,y2,z2,x3,y3,z3,color));
	}
	
	public Color filmPixel(Vector a, Vector b, Vector lightDir)
	{
		Vector max = new Vector(1000,1000,1000);
		Color color = null;
		for(int i = 0; i < triList.size(); i ++)
		{
			Vector tester = checkCol(a,b,triList.get(i));
			if(tester != null && tester.getLength() < max.getLength())
			{
				max = tester;
				color = triList.get(i).color;
				/*
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				*/
				//double norm = 1+triList.get(i).v2.cross(triList.get(i).v1).normalize().mult(lightDir);
				//System.out.println(norm);
				/*
				for(double j = 1.5f; j < max.getLength(); j+=0.1)
				{
					
					red /=1.001;
					blue /=1.001;
					green /=1.001;
				}*/
				//color = new Color(red/255f,green/255f,blue/255f);
			}
		}
		
		
		
		return(color);
	}
	
	public Vector checkCol(Vector a, Vector b, Triangle t)
	{
		double[][] augMatrix = {{t.v1.x,t.v2.x,b.mult(-1).x,a.x-t.pos.x}
							   ,{t.v1.y,t.v2.y,b.mult(-1).y,a.y-t.pos.y}
							   ,{t.v1.z,t.v2.z,b.mult(-1).z,a.z-t.pos.z}};
//		LinearSolver.printMatrix(augMatrix);
//		System.out.println();
		double[] sol = LinearSolver.solveEquation(augMatrix);
		if(!(sol[0]==0)&&sol[3]>=0)
		{
//			System.out.println(sol[0]+" "+sol[1]+" "+sol[2]+" "+sol[3]);
//			System.out.println();
			Vector hitPoint = a.add(b.mult(sol[3]));
			
			double dot1 = t.v1.cross(t.v2).mult(t.v1.cross(hitPoint.add(t.pos.mult(-1))));
			double dot2 = t.v2.add(t.v1.mult(-1)).cross(t.v1.mult(-1)).mult(t.v2.add(t.v1.mult(-1)).cross(hitPoint.add(t.pos.add(t.v1).mult(-1))));
			double dot3 = t.v2.mult(-1).cross(t.v1.add(t.v2.mult(-1))).mult(t.v2.mult(-1).cross(hitPoint.add(t.pos.add(t.v2).mult(-1))));
			if(dot1 > 0 && dot2 > 0 && dot3 > 0)
			{
				return(hitPoint.add(a.mult(-1)));
			}
		}
		return(null);
	}
	
	public Color getPixel(int x, int y, Camera cam, Light light)
	{
		Vector screenStart = cam.normal.mult(0.5).add(cam.normal.cross(cam.dir).mult(0.5)).add(cam.dir.mult((double)(1.0/(2.0*Math.tan(cam.angle/2.0)))));
		Vector screenX = cam.dir.cross(cam.normal).mult(1.0/cam.xResolution);
		Vector screenY = cam.normal.mult(-1.0).mult(1.0/cam.yResolution);
		Color col = filmPixel(cam.pos,screenStart.add(screenX.mult(x)).add(screenY.mult(y)),light.dir);
		if(!(col == null))
		{
			return col;
		}
		return null;
	}
}
