package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import shapes.DrawableShape;

public class ShapeButton extends JButton {
	private static final long serialVersionUID = 1L;
	private DrawListener listener;
	private DrawableShape shape;

	public ShapeButton(DrawableShape shape, DrawListener listener) {
		this.listener = listener;
		this.shape = shape;
		addActionListener(shapeListen);
	}

	public ShapeButton(DrawableShape shape, DrawListener listener, String unicode) {
		this(shape, listener);
		setText(unicode);
	}

	public ShapeButton(DrawableShape shape, DrawListener listener, ImageIcon image) {
		this(shape, listener);
		setIcon(image);
	}

	ActionListener shapeListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			listener.updateShape(shape);
		}
	};
}