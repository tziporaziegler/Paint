package paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JLabel;

import paint.modes.Mode;
import paint.modes.Pencil;

public class DrawListener implements MouseListener, MouseMotionListener {
	private Canvas canvas;
	private JColorChooser chooser;
	private JLabel location;

	private Graphics2D imageGraphics;
	private Mode mode;

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
		mode = new Pencil();
	}

	public void updateShape(Mode shape) {
		this.mode = shape;
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
		Color color = new Color(red, green, blue);
		canvas.updateColor(color);
		chooser.setColor(color);
		chooser.getPreviewPanel().setBackground(color);
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

		mode.drawIt(canvas, imageGraphics, lastX, lastY, false, firstX, firstY, lastX, lastY);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY() - OFFSET;

		mode.drawIt(canvas, imageGraphics, x, y, true, firstX, firstY, lastX, lastY);

		lastX = x;
		lastY = y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mode.release(canvas, imageGraphics, e.getX(), e.getY() - OFFSET, false, firstX, firstY, lastX, lastY);
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