package paint.shapes;

import java.awt.Graphics2D;

import paint.Canvas;

public class Pencil extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d) {
	}

	@Override
	public void drawIt(Graphics2D imageGraphics, Canvas canvas, int lastX,
			int lastY, int firstX, int firstY, int x, int y, boolean temp) {
		imageGraphics.drawLine(lastX, lastY, x, y);
		canvas.repaint();
	}
}
