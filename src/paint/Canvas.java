package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import shapes.DrawableShape;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private Graphics2D imageGraphics;

	private Color color;
	private Stroke stroke;

	private DrawableShape tempShape;

	final static Color BKGD_COLOR = Color.WHITE;

	public Canvas(int width, int height) {
		// use buffer to save pixels so still there after minimize
		// save byte of memory by using the TYPE_INT_RGB because leaving out alpha part. Have less colors, but don't need more.
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		imageGraphics = (Graphics2D) image.getGraphics();

		// clear screen and start it at white instead of black
		updateColor(BKGD_COLOR);
		imageGraphics.fillRect(0, 0, width, height);
		updateColor(Color.BLACK);
		updateStrokeWidth(2);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(color);
		g2d.setStroke(stroke);

		// image observer tells you when image is finished being drawn
		// if small image, don't care when done, so can leave observer as null
		g2d.drawImage(image, 0, 0, null);

		if (tempShape != null) {
			tempShape.draw(g2d);
		}
	}

	// method called by JColorChooser ChangeListener
	public void updateColor(Color color) {
		this.color = color;
		imageGraphics.setColor(color);
	}

	// method called by WidthButton ActionListener
	public void updateStrokeWidth(int width) {
		stroke = new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		imageGraphics.setStroke(stroke);
	}

	// on Open, set image to chosen image
	public void setImage(BufferedImage image) {
		this.image = image;
		imageGraphics = (Graphics2D) image.getGraphics();
		repaint();
	}

	public BufferedImage getImage() {
		return image;
	}

	public Graphics2D getImageGraphics() {
		return imageGraphics;
	}

	// when repaint, paint tempShape if one exists
	public void setTempShape(DrawableShape shape) {
		tempShape = shape;
	}

	public void resetGraphics() {
		imageGraphics.setColor(color);
		imageGraphics.setStroke(stroke);
	}
}