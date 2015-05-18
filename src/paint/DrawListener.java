package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import shapes.DrawableShape;
import shapes.Eraser;
import shapes.Pencil;

public class DrawListener implements MouseListener, MouseMotionListener {
	private Canvas canvas;
	private JLabel location;

	private Graphics2D imageGraphics;
	private DrawableShape shape;
	private boolean isPencil;
	private boolean isEraser;
	private Stroke eraserStroke = new BasicStroke(30);
	private Color bkgdColor = Color.WHITE;
	private boolean dropperSelected;

	private int lastX;
	private int lastY;
	private int firstX;
	private int firstY;

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
		if (dropperSelected) {
			// get color of pixel in image that clicked on
			BufferedImage image = canvas.getImage();
			int clr = image.getRGB(e.getX(), e.getY());
			int red = (clr & 0x00ff0000) >> 16;
			int green = (clr & 0x0000ff00) >> 8;
			int blue = clr & 0x000000ff;
			canvas.updateColor(new Color(red, green, blue));
			dropperSelected = false;
		}

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

		draw(x, y, !(isPencil || isEraser));

		lastX = x;
		lastY = y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		draw(e.getX(), e.getY() - OFFSET, false);
	}

	private void draw(int x, int y, boolean temp) {
		if (!dropperSelected) {
			if (isPencil) {
				imageGraphics.drawLine(lastX, lastY, x, y);
			}
			else if (isEraser) {
				imageGraphics.setColor(bkgdColor);
				imageGraphics.setStroke(eraserStroke);
				imageGraphics.drawLine(lastX, lastY, x, y);
				canvas.resetGraphics();
			}
			else {
				int width = x - firstX;
				int height = y - firstY;
				int widthABS = Math.abs(width);
				int heightABS = Math.abs(height);
				x = width > 0 ? firstX : lastX;
				y = height > 0 ? firstY : lastY;

				if (temp) {
					canvas.setTempShape(shape);
					shape.setTemp(x, y, widthABS, heightABS);
				}
				else {
					canvas.setTempShape(null);
					shape.draw(imageGraphics, x, y, widthABS, heightABS);
				}
			}
			canvas.repaint();
		}
	}

	// FIXME get rid of instanceof
	public void updateShape(DrawableShape shape) {
		this.shape = shape;
		if (shape instanceof Pencil) {
			isPencil = true;
		}
		else if (shape instanceof Eraser) {
			isEraser = true;
			isPencil = false;
		}
		else {
			isPencil = false;
			isEraser = false;
		}
	}

	public void selectDropper() {
		dropperSelected = true;
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