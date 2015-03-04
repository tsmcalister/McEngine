package mcengine;
import java.awt.Color;


public class Triangle {
	public double x1, x2, x3, y1, y2, y3, z1, z2, z3;
	public Vector pos, v1, v2;
	Color color;
	
	
	Triangle(double x1, double y1, double z1
					  ,double x2, double y2, double z2
					  ,double x3, double y3, double z3, Color color)
	{
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
		this.z1 = z1;
		this.z2 = z2;
		this.z3 = z3;
		this.pos = new Vector(x1,y1,z1);
		this.v1 = new Vector(x2-x1,y2-y1,z2-z1);
		this.v2 = new Vector(x3-x1,y3-y1,z3-z1);
		this.color = color;
	}
	public void calcVector()
	{
		this.pos = new Vector(x1,y1,z1);
		this.v1 = new Vector(x2-x1,y2-y1,z2-z1);
		this.v2 = new Vector(x3-x1,y3-y1,z3-z1);
	}
}