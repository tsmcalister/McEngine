package mcengine;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Callable;


public class HeightCalcTask implements Callable<Boolean> {

	public int i;
	public Graphics g;
	public int screenHeight;
	public R3 world;
	public Camera mainCam;
	public Light mainLight;
	
	
	public HeightCalcTask(int i, Graphics g, int screenHeight, R3 world){
		this.i = i;
		this.g = g;
		this.screenHeight = screenHeight;
		this.world = world;
	}
	
	public Boolean call() throws Exception{
		for(int j = 0; j < screenHeight; j++)
		{
			
				Color col = world.getPixel(i,j, mainCam, mainLight);
				if(!(col == null))
				{
					g.setColor(col);
					g.drawLine(i, j, i, j);
				}
			
		}
		return true;
	}
}
