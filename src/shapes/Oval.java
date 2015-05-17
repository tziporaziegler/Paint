package shapes;

import java.awt.Graphics2D;

public class Oval implements DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x, int y, int width, int height) {
		g2d.drawOval(x, y, width, height);
	}

}
