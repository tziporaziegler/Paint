package paint.shapes;

import java.awt.Graphics2D;

import paint.Canvas;

public abstract class DrawableShape {
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public abstract void draw(Graphics2D g2d);

	// FIXME remove duplicate code
	public void drawIt(Graphics2D imageGraphics, Canvas canvas, int lastX,
			int lastY, int firstX, int firstY, int x, int y, boolean temp) {
		int width = x - firstX;
		int height = y - firstY;
		int widthABS = Math.abs(width);
		int heightABS = Math.abs(height);
		x = width > 0 ? firstX : lastX;
		y = height > 0 ? firstY : lastY;

		this.x = x;
		this.y = y;
		this.width = widthABS;
		this.height = heightABS;
		
		if (temp) {
			canvas.setTempShape(this);	
		} else {
			canvas.setTempShape(null);
			draw(imageGraphics);
		}

		canvas.repaint();
	}
}