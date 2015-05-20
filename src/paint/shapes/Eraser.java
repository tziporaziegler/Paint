package paint.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import paint.Canvas;

public class Eraser extends DrawableShape {

	public final static int ERASER_HEIGHT = 30;
	private Stroke eraserStroke = new BasicStroke(ERASER_HEIGHT,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

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
	public void drawIt(Graphics2D imageGraphics, Canvas canvas, int lastX,
			int lastY, int firstX, int firstY, int x, int y, boolean temp) {
		imageGraphics.setColor(Canvas.BKGD_COLOR);
		imageGraphics.setStroke(eraserStroke);
		imageGraphics.drawLine(lastX, lastY, x, y);
		this.x = x;
		this.y = y;
		width = ERASER_HEIGHT;
		height = ERASER_HEIGHT;
		canvas.setTempShape(this);
		canvas.resetGraphics();

		canvas.repaint();
	}
}