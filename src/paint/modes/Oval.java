package paint.modes;

import java.awt.Graphics2D;

public class Oval extends Mode {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawOval(x, y, width, height);
	}
}