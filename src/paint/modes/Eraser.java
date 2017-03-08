package paint.modes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import paint.Canvas;

public class Eraser extends Mode {

	public static final int ERASER_HEIGHT = 30;
	private Stroke eraserStroke = new BasicStroke(ERASER_HEIGHT, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

	@Override
	public void draw(Graphics2D g2d) {
		Graphics2D g = g2d;
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);

		// adjust so pointer is in middle of eraser circle
		int adjust = (int) ERASER_HEIGHT / 2;

		// use width twice to ensure it is a circle
		g.drawOval(x - adjust, y - adjust, width, width);
	}

	@Override
	public void drawIt(Canvas canvas, Graphics2D g2d, int x, int y, boolean temp, int firstX, int firstY, int lastX,
			int lastY) {
		g2d.setColor(Canvas.BKGD_COLOR);
		g2d.setStroke(eraserStroke);
		g2d.drawLine(lastX, lastY, x, y);
		set(x, y, ERASER_HEIGHT, ERASER_HEIGHT);
		canvas.setTempMode(this);
		canvas.resetGraphics();

		canvas.repaint();
	}

	@Override
	public void release(Canvas canvas, Graphics2D g2d, int x, int y, boolean b, int firstX, int firstY, int lastX,
			int lastY) {
		canvas.setTempMode(null);
		canvas.repaint();
	}
}