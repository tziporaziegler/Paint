package paint;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import paint.modes.Mode;

public class ModeButton extends JButton {
	private static final long serialVersionUID = 1L;
	private Mode shape;

	public ModeButton(Mode shape, String unicode) {
		this.shape = shape;
		setText(unicode);
	}

	public ModeButton(Mode shape, ImageIcon image) {
		this.shape = shape;
		setIcon(image);
		setBackground(null);
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	public Mode getShape() {
		return shape;
	}
}