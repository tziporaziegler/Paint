package shapes;

import java.awt.Graphics2D;

public class Rect implements DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x, int y, int width, int height) {
		g2d.drawRect(x, y, width, height);
	}

}