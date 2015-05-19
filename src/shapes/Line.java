package shapes;

import java.awt.Graphics2D;

public class Line extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawLine(x, y, width, height);
	}
}