package shapes;

import java.awt.Graphics2D;

public class OvalFill extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x, int y, int width, int height) {
		g2d.fillOval(x, y, width, height);
	}

	@Override
	public void drawTemp(Graphics2D g2d) {
		g2d.fillOval(tempX, tempY, tempW, tempH);
	}

}
