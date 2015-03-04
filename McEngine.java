package mcengine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class McEngine extends JPanel implements ActionListener, KeyEventDispatcher{

	static int screenWidth = 200;
	static int screenHeight = 200;
	static String screenText = "McEngine";
	public Camera mainCam = new Camera(0,0,2,new Vector(0,0,1),new Vector(0,1,0),Math.PI/2,screenWidth,screenHeight);
	public Light mainLight = new Light(0,0,0,new Vector(0,0,1).rotate(0, 0, 0),new Vector(0,0,1).rotate(0, 0, 0),Math.PI/2,screenWidth,screenHeight);
	Color bgColor = Color.black; 
	R3 world = new R3();
	//Color[][] buffer = new Color[screenWidth][screenHeight];
	BufferedImage buffer = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
	public int paintCount =0;
	public int paintFrequency = 10;
	public int threadCount = 4;
	public int framecount = 0;
	public double startTime;
	public double endTime;

	public void addRect(Vector a, Vector b, Vector c, Vector d, Color col)
	{
		world.addTri(a.x, a.y, a.z, b.x, b.y, b.z, c.x, c.y, c.z, col);
		world.addTri(c.x, c.y, c.z, d.x, d.y, d.z, a.x, a.y, a.z, col);
	}

	public void putPixel(Graphics g,int x, int y, Color c)
	{
		g.setColor(c);
		g.drawLine(x, y, x, y);
	}

	public void drawScreen(Graphics g)
	{	
		g.drawImage(buffer,0,0,this);
		framecount ++;
		endTime = System.currentTimeMillis();
		g.setColor(Color.white);
		g.drawString((int)(1/((endTime-startTime)/1000))+"fps", 10, 20);
		
		/*
		for(int i = 0; i < screenWidth; i++)
		{
			for(int j = 0; j < screenHeight; j++)
			{
				putPixel(g,i,j,buffer[i][j]);

			}
		}			
		 */




		/*
		int decimals = 3;
		g.setColor(Color.black);
		g.drawString("X: "+Math.round(mainCam.pos.x*Math.pow(10, decimals))/Math.pow(10, decimals), 5, 15); 
		g.drawString("Y: "+Math.round(mainCam.pos.y*Math.pow(10, decimals))/Math.pow(10, decimals), 5, 30);
		g.drawString("Z: "+Math.round(mainCam.pos.z*Math.pow(10, decimals))/Math.pow(10, decimals), 5, 45);
		 */

	}



	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED)
		{
			double speed = 0.5f;
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_UP:
				mainCam.moveForward(speed);
				break;
			case KeyEvent.VK_DOWN:
				mainCam.moveBackward(speed);
				break;
			case KeyEvent.VK_LEFT:
				mainCam.moveLeft(speed);
				break;
			case KeyEvent.VK_RIGHT:
				mainCam.moveRight(speed);
				break;
			case KeyEvent.VK_J:
				mainCam.tiltUp(Math.PI/32);
				break;
			case KeyEvent.VK_M:
				mainCam.tiltDown(Math.PI/32);
				break;
			case KeyEvent.VK_D:
				mainCam.dir = mainCam.dir.rotate(0, 0, -Math.PI/32f);
				break;
			case KeyEvent.VK_A:
				mainCam.dir = mainCam.dir.rotate(0, 0,Math.PI/32);
				break;
			case KeyEvent.VK_W:
				mainCam.pos.z+=0.5;
				break;
			case KeyEvent.VK_S:
				mainCam.pos.z-=0.5;
				break;
			}
		}

		//Allow the event to be redispatched
		return false;
	}

	Timer timer = new Timer(0, this);

	public McEngine() {
		timer.start();
		KeyboardFocusManager manager =
				KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);

		
		world.addTri(-5.0f, 5, 5.0f
				,-5.0f, 5, -5.0f
				,5, 5, -5.0f,Color.cyan);
		world.addTri(-5.0f, 5, 5.0f
				,5.0f, 5, 5.0f
				,5.0f, 5, -5.0f,Color.cyan);
		world.addTri(-5.0f, 15, 5.0f
				,-5.0f, 15, -5.0f
				,5.0f, 15, -5.0f,Color.magenta);
		world.addTri(-5.0f, 15, 5.0f
				,5.0f, 15, 5.0f
				,5.0f, 15, -5.0f,Color.magenta);
		world.addTri(-5.0f, 15, 5.0f
				,-5.0f, 5, -5.0f
				,-5.0f, 15, -5.0f,Color.blue);
		world.addTri(-5.0f, 15, 5.0f
				,-5.0f, 5, 5.0f
				,-5.0f, 5, -5.0f,Color.blue);
		world.addTri(5.0f, 15, 5.0f
				,5.0f, 5, -5.0f
				,5.0f, 15, -5.0f,Color.red);
		world.addTri(5.0f, 15, 5.0f
				,5.0f, 5, 5.0f
				,5.0f, 5, -5.0f,Color.red);
		world.addTri(5.0f, 5, 5.0f
				,5.0f, 15, 5.0f
				,-5.0f, 15, 5.0f,Color.green);
		world.addTri(-5.0f, 15, 5.0f
				,-5.0f, 5, 5.0f
				,5.0f, 5, 5.0f,Color.green);
		world.addTri(5.0f, 5, -5.0f
				,5.0f, 15, -5.0f
				,-5.0f, 15, -5.0f,Color.orange);
		world.addTri(5.0f, 5, -5.0f
				,-5.0f, 5, -5.0f
				,-5.0f, 15, -5.0f,Color.orange);
	}
	public void updateBuffer()
	{
		startTime = System.currentTimeMillis();
		for(int i = 0; i < screenWidth; i++)
		{
			for(int j = 0; j < screenHeight; j++)
			{
				Color col = world.getPixel(i,j, mainCam, mainLight);
				if(col == null)
				{
					buffer.setRGB(i, j, bgColor.getRGB());
				}
				else
				{
					buffer.setRGB(i, j, col.getRGB());
				}
			}
		}
		repaint();
	}
	public void paintComponent(Graphics g)
	{
		drawScreen(g);	
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame(screenText);
		frame.add(new McEngine());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenWidth, screenHeight);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public double alpha = 0;

	public void actionPerformed(ActionEvent e) {
		updateBuffer();
		
    	mainCam.pos.x = Math.cos(alpha-Math.PI/2)*12;
    	mainCam.pos.y = Math.sin(alpha-Math.PI/2)*12+10;
    	mainCam.dir = ((new Vector(0,10,0)).add(mainCam.pos.mult(-1))).normalize();
    	mainCam.normal = mainCam.normal.rotate(0, 0, 0.05);
    	alpha+=0.1;
		 
	}
}