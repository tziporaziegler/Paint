package paint.modes;

import java.awt.Graphics2D;

public class OvalFill extends Mode {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.fillOval(x, y, width, height);
	}
}