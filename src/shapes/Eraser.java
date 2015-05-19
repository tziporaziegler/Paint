package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import paint.DrawListener;

public class Eraser extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x, int y, int width, int height) {
	}

	@Override
	public void drawTemp(Graphics2D g2d) {
		Graphics2D g = g2d;
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.BLACK);

		// adjust so pointer is in middle of eraser circle
		int adjust = (int) DrawListener.ERASER_HEIGHT / 2;

		// use tempW twice to ensure it is a circle
		g.drawOval(tempX - adjust, tempY - adjust, tempW, tempW);
	}

}
