package classcode;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import paint.Canvas;

public abstract class BrushListener implements MouseListener, MouseMotionListener {
	protected int startX;
	protected int startY;
	protected int lastX;
	protected int lastY;
	
	private Canvas canvas;

	public BrushListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		draw(canvas.getImageGraphics());
	}

	public abstract void draw(Graphics2D g);

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}