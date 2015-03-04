package mcengine;

public class Vector {
	double x;
	double y;
	double z;
	
	Vector(double x, double y, double z)
	{
		this.x = x; 
		this.y = y; 
		this.z = z;
	}
	
	public double getLength()
	{
		return(Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2)+Math.pow(this.z, 2)));
	}
	
	public double getX()
	{
		return(this.x);
	}
	
	public double getY()
	{
		return(this.y);
	}
	public double getZ()
	{
		return(this.z);
	}
	public void print()
	{
		System.out.println(this.x+" "+this.y+" "+this.z);
	}
	public Vector normalize()
	{
		return(new Vector(this.x / this.getLength(),this.y/this.getLength(),this.z/this.getLength()));
	}
	public Vector add(Vector a)
	{
		return(new Vector(this.x+a.getX(),this.y+a.getY(),this.z+a.getZ()));
	}
	public Vector mult(double b)
	{
		return(new Vector(this.x*b,this.y*b,this.z*b));
	}
	public double mult(Vector a)
	{
		return(this.getX()*a.getX()+this.getY()*a.getY()+this.getZ()*a.getZ());
	}
	public Vector cross(Vector a)
	{
		return(new Vector(this.y*a.getZ()-this.z*a.getY(),this.z*a.getX()-this.x*a.getZ(),this.x*a.getY()-this.y*a.getX()));
	}
	public Vector rotate(double xAngle, double yAngle, double zAngle)
	{
		Matrix xRot = new Matrix(new Vector(1,0,0)
		                        ,new Vector(0,Math.cos(xAngle),-Math.sin(xAngle))
		                        ,new Vector(0,Math.sin(xAngle),Math.cos(xAngle)));
		
		Matrix yRot = new Matrix(new Vector(Math.cos(yAngle),0,Math.sin(yAngle))
		                        ,new Vector(0,1,0)
		                        ,new Vector(-Math.sin(yAngle),0,Math.cos(yAngle)));
		
		Matrix zRot = new Matrix(new Vector(Math.cos(zAngle),-Math.sin(zAngle),0)
		                        ,new Vector(Math.sin(zAngle),Math.cos(zAngle),0)
		                        ,new Vector(0,0,1));

		return((xRot.mult(yRot).mult(zRot)).mult(this));
	}

}
