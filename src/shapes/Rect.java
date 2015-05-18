package shapes;

import java.awt.Graphics2D;

public class Rect extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x, int y, int width, int height) {
		g2d.drawRect(x, y, width, height);
	}

	@Override
	public void drawTemp(Graphics2D g2d) {
		g2d.drawRect(tempX, tempY, tempW, tempH);
	}

}