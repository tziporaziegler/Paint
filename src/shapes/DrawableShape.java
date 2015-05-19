package shapes;

import java.awt.Graphics2D;

public abstract class DrawableShape {
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public abstract void draw(Graphics2D g2d);
	
	public void setPoints(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
}