package paint.modes;

import java.awt.Graphics2D;

import paint.Canvas;

public abstract class Mode {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public abstract void draw(Graphics2D g2d);

	public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX, int lastY) {
		int width = x - firstX;
		int height = y - firstY;
		x = width > 0 ? firstX : lastX;
		y = height > 0 ? firstY : lastY;

		set(x, y, Math.abs(width), Math.abs(height));
		
		if (temp) {
			canvas.setTempMode(this);
		}
		else {
			canvas.setTempMode(null);
			draw(g2d);
		}

		canvas.repaint();
	}

	protected void set(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void release(Canvas canvas, Graphics2D g2d, int x, int y, boolean b, int firstX, int firstY, int lastX, int lastY) {
		drawIt(canvas, g2d, x, y, false, firstX, firstY, lastX, lastY);
	}
}