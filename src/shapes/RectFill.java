package shapes;

import java.awt.Graphics2D;

public class RectFill extends DrawableShape {

	@Override
	public void draw(Graphics2D g2d, int x, int y, int width, int height) {
		g2d.fillRect(x, y, width, height);
	}

	@Override
	public void drawTemp(Graphics2D g2d) {
		g2d.fillRect(tempX, tempY, tempW, tempH);
	}
	
}