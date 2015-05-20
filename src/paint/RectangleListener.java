package paint;

import java.awt.Graphics2D;

public class RectangleListener extends BrushListener {

	public RectangleListener(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawRect(startX, startY, lastX - startX, lastY - startY);
	}

}