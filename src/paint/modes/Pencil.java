package paint.modes;

import java.awt.Graphics2D;

import paint.Canvas;
import paint.DrawListener;

public class Pencil extends Mode {

	@Override
	public void draw(Graphics2D g2d) {
	}

	@Override
	public void drawIt(DrawListener listener, Canvas canvas, Graphics2D g2d, int x, int y, boolean temp) {
		g2d.drawLine(listener.getLastX(), listener.getLastY(), x, y);
		canvas.repaint();
	}
}