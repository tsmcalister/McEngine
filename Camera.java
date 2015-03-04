package mcengine;

public class Camera {
	public Vector pos;
	public Vector normal;
	public Vector dir;
	public double angle;
	public int xResolution;
	public int yResolution;
	
	Camera(double xPos,double yPos,double zPos,Vector normal,Vector dir,double angle, int xResolution, int yResolution)
	{
		this.pos = new Vector(xPos,yPos,zPos);
		this.normal = normal;
		this.dir = dir;
		this.angle = angle;
		this.xResolution = xResolution;
		this.yResolution = yResolution;
	}
	public void moveForward(double d)
	{
		this.pos = this.pos.add(this.dir.mult(d));
	}
	public void moveBackward(double d)
	{
		this.pos = this.pos.add(this.dir.mult(-d));
	}
	public void moveRight(double d)
	{
		this.pos = this.pos.add(this.dir.cross(this.normal).mult(d));
	}
	public void moveLeft(double d)
	{
		this.pos = this.pos.add(this.dir.cross(this.normal).mult(-d));
	}
	public void tiltUp(double a)
	{
		Vector temp = this.dir.mult(Math.cos(a)).add(this.normal.mult(Math.sin(a)));
		this.normal = this.normal.mult(Math.cos(a)).add(this.dir.mult(-Math.sin(a)));
		this.dir = temp;
	}
	public void tiltDown(double a)
	{
		Vector temp = this.dir.mult(Math.cos(a)).add(this.normal.mult(-Math.sin(a)));
		this.normal = this.normal.mult(Math.cos(a)).add(this.dir.mult(Math.sin(a)));
		this.dir = temp;
	}
	public void tiltLeft(double a)
	{
		
	}
	public void tiltRight(double a)
	{
		
	}

}
