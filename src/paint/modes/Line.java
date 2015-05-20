package paint.modes;

import java.awt.Graphics2D;

import paint.Canvas;
import paint.DrawListener;

public class Line extends Mode {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawLine(x, y, width, height);
	}

	@Override
	public void drawIt(DrawListener listener, Canvas canvas, Graphics2D g2d, int x, int y, boolean temp) {
		set(listener.getLastX(), listener.getLastY(), listener.getFirstX(), listener.getFirstY());
		continueTemp(canvas, g2d);
	}
}