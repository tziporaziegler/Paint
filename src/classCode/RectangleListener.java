package classcode;

import java.awt.Graphics2D;

import paint.Canvas;

public class RectangleListener extends BrushListener {

	public RectangleListener(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawRect(startX, startY, lastX - startX, lastY - startY);
	}

}