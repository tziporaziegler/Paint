package shapes;

import java.awt.Graphics2D;

public abstract class DrawableShape {
	protected int tempX;
	protected int tempY;
	protected int tempW;
	protected int tempH;

	public abstract void draw(Graphics2D g2d, int x, int y, int width, int height);
	
	public abstract void drawTemp(Graphics2D g2d);
	
	public void setTemp(int tempX, int tempY, int tempW, int tempH){
		this.tempX = tempX;
		this.tempY = tempY;
		this.tempW = tempW;
		this.tempH = tempH;
	}

}
