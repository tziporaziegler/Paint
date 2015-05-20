package paint;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class WidthButton extends JButton {
	private static final long serialVersionUID = 1L;
	private int width;
	private Canvas canvas;

	public WidthButton(int width, Canvas canvas) {
		this.width = width;
		this.canvas = canvas;
	}

	public WidthButton(int width, Canvas canvas, String unicode) {
		this(width, canvas);
		setText(unicode + " " + width + "p");
	}

	public WidthButton(Canvas canvas, String string) {
		this.canvas = canvas;
		setText(string);
	}

	public WidthButton(int width, Canvas canvas, ImageIcon image) {
		this(width, canvas);
		setIcon(image);
	}

	protected void updateStroke() {
		canvas.updateStrokeWidth(width);
	}

	public void setWidth(int width) {
		this.width = width;
	}
}