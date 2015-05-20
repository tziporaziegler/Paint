package paint.shapes;

import java.awt.Graphics2D;

public class Oval extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawOval(x, y, width, height);
	}
}