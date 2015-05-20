package paint.shapes;

import java.awt.Graphics2D;

import paint.Canvas;

public class Line extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawLine(x, y, width, height);
	}

	@Override
	public void drawIt(Graphics2D imageGraphics, Canvas canvas, int lastX,
			int lastY, int firstX, int firstY, int x, int y, boolean temp) {
		int width = x - firstX;
		int height = y - firstY;
		x = width > 0 ? firstX : lastX;
		y = height > 0 ? firstY : lastY;

		if (temp) {
			canvas.setTempShape(this);
			this.x = lastX;
			this.y = lastY;
			this.width = firstX;
			this.height = firstY;
		} else {
			canvas.setTempShape(null);
			this.x = lastX;
			this.y = lastY;
			this.width = firstX;
			this.height = firstY;
			draw(imageGraphics);
		}
		canvas.repaint();
	}
}