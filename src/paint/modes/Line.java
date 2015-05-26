package paint.modes;

import java.awt.Graphics2D;

import paint.Canvas;

public class Line extends Mode {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawLine(x, y, width, height);
	}

	@Override
	public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX, int lastY) {
		set(lastX, lastY, firstX, firstY);

		if (temp) {
			canvas.setTempMode(this);
		}
		else {
			canvas.setTempMode(null);
			draw(g2d);
		}

		canvas.repaint();
	}
}