package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JLabel;

import shapes.DrawableShape;
import shapes.Eraser;
import shapes.Line;
import shapes.Pencil;

public class DrawListener implements MouseListener, MouseMotionListener {
	private Canvas canvas;
	private JColorChooser chooser;
	private JLabel location;

	private Graphics2D imageGraphics;
	private DrawableShape shape;

	public final static float ERASER_HEIGHT = 30;
	private Stroke eraserStroke = new BasicStroke(ERASER_HEIGHT, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
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

	public DrawListener(Canvas canvas, JColorChooser chooser, JLabel location) {
		this.canvas = canvas;
		this.chooser = chooser;
		this.location = location;
		shape = new Pencil();
	}

	// FIXME remove instance of
	private void draw(int x, int y, boolean temp) {
		if (shape instanceof Pencil) {
			imageGraphics.drawLine(lastX, lastY, x, y);
		}
		else if (shape instanceof Eraser) {
			imageGraphics.setColor(bkgdColor);
			imageGraphics.setStroke(eraserStroke);
			imageGraphics.drawLine(lastX, lastY, x, y);
			shape.setTemp(x, y, 30, 30);
			canvas.setTempShape(shape);
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
				if (shape instanceof Line) {
					shape.setTemp(lastX, lastY, firstX, firstY);
				}
				else {
					shape.setTemp(x, y, widthABS, heightABS);
				}
			}
			else {
				canvas.setTempShape(null);
				if (shape instanceof Line) {
					shape.draw(imageGraphics, lastX, lastY, firstX, firstY);
				}
				else {
					shape.draw(imageGraphics, x, y, widthABS, heightABS);
				}
			}
		}
		canvas.repaint();
	}

	public void updateShape(DrawableShape shape) {
		this.shape = shape;
	}

	public void selectDropper() {
		dropperSelected = true;
	}

	private void updateDropperColor(int x, int y) {
		// get color of pixel in image that clicked on
		BufferedImage image = canvas.getImage();
		int clr = image.getRGB(x, y);
		int red = (clr & 0x00ff0000) >> 16;
		int green = (clr & 0x0000ff00) >> 8;
		int blue = clr & 0x000000ff;
		canvas.updateColor(new Color(red, green, blue));
		chooser.setColor(red, green, blue);
		dropperSelected = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastX = e.getX();
		lastY = e.getY() - OFFSET;

		if (dropperSelected) {
			updateDropperColor(lastX, lastY);
		}

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

		if (dropperSelected) {
			updateDropperColor(x, y);
		}

		draw(x, y, true);

		lastX = x;
		lastY = y;
	}

	// FIXME remove instanceof
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (shape instanceof Eraser) {
			canvas.setTempShape(null);
			canvas.repaint();
		}
		else {
			if (dropperSelected) {
				updateDropperColor(x, y);
			}
			draw(x, y - OFFSET, false);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		location.setText(e.getX() + ", " + (e.getY() - SIZE_OF_MENUBAR));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (dropperSelected) {
			updateDropperColor(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}