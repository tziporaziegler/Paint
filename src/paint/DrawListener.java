package paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import shapes.DrawableShape;
import shapes.Pencil;

public class DrawListener implements MouseListener, MouseMotionListener {
	private Canvas canvas;
	private JLabel location;

	private Graphics2D imageGraphics;
	private DrawableShape shape;
	private boolean isPencil;

	private int lastX;
	private int lastY;
	private int firstX;
	private int firstY;

	private int tempX;
	private int tempY;
	private int tempW;
	private int tempH;

	// use offset to start drawing at tip of mouse pointer to counteract size of title and menu bars
	// TODO get sizes from PaintFrame after set up menu
	private final int SIZE_OF_MENUBAR = 23;
	private final int OFFSET = SIZE_OF_MENUBAR + 25;

	public DrawListener(Canvas canvas, JLabel location) {
		this.canvas = canvas;
		this.location = location;
		isPencil = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY() - OFFSET;

		firstX = lastX;
		firstY = lastY;

		// every time the mouse is pressed, update the Graphics
		// normally don't save the Graphics variable,
		// however here ok here since only other place using it is in Dragged and every Drag is for sure preceeded by a click
		imageGraphics = canvas.getImageGraphics();

		draw(lastX, lastY, false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY() - OFFSET;

		if (isPencil) {
			draw(x, y, false);
		}
		else {
			draw(x, y, true);
		}

		lastX = x;
		lastY = y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!isPencil) {
			draw(e.getX(), e.getY() - OFFSET, false);
		}
	}

	private void draw(int x, int y, boolean temp) {
		if (isPencil) {
			imageGraphics.drawLine(lastX, lastY, x, y);
		}
		else {
			int width = x - firstX;
			int height = y - firstY;
			x = width > 0 ? firstX : lastX;
			y = height > 0 ? firstY : lastY;
			drawShape(x, y, Math.abs(width), Math.abs(height), temp);
		}
		canvas.repaint();
	}

	private void drawShape(int x, int y, int width, int height, boolean temp) {
		canvas.setTempShape(temp, this);

		if (temp) {
			tempX = x;
			tempY = y;
			tempW = width;
			tempH = height;
		}
		else {
			drawAShape(x, y, width, height, imageGraphics);
		}
	}

	public void drawTempShape(Graphics2D g) {
		drawAShape(tempX, tempY, tempW, tempH, g);
	}

	public void drawAShape(int x, int y, int width, int height, Graphics2D g2d) {
		shape.draw(g2d, x, y, width, height);
	}

	public void updateShape(DrawableShape shape) {
		this.shape = shape;
		if ((shape instanceof Pencil)) {
			isPencil = true;
		}
		else {
			isPencil = false;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		location.setText(e.getX() + ", " + (e.getY() - SIZE_OF_MENUBAR));
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