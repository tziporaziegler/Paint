package paint.modes;

import java.awt.Graphics2D;

import paint.Canvas;
import paint.DrawListener;

public abstract class Mode {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean temp;

	public abstract void draw(Graphics2D g2d);

	public void drawIt(DrawListener listener, Canvas canvas, Graphics2D g2d, int x, int y, boolean temp) {
		int firstX = listener.getFirstX();
		int width = x - firstX;
		int firstY = listener.getFirstY();
		int height = y - firstY;
		x = width > 0 ? firstX : listener.getLastX();
		y = height > 0 ? firstY : listener.getLastY();

		set(x, y, Math.abs(width), Math.abs(height));
		this.temp = temp;
		continueTemp(canvas, g2d);
	}

	protected void continueTemp(Canvas canvas, Graphics2D g2d) {
		canvas.setTempShape(temp ? this : null);
		if (!temp) {
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
}