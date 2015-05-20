package paint.shapes;

import java.awt.Graphics2D;

public class OvalFill extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillOval(x, y, width, height);
	}
}