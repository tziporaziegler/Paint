package shapes;

import java.awt.Graphics2D;

public class Line extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		g2d.drawLine(x1, y1, x2, y2);
	}

	@Override
	public void drawTemp(Graphics2D g2d) {
		g2d.drawLine(tempX, tempH, tempW, tempH);
	}

}
