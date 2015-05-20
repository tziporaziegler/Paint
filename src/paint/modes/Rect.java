package paint.modes;

import java.awt.Graphics2D;

public class Rect extends Mode {

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawRect(x, y, width, height);
	}
}