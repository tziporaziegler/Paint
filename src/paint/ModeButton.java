package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import paint.shapes.DrawableShape;

public class ModeButton extends JButton {
	private static final long serialVersionUID = 1L;
	private DrawListener listener;
	private DrawableShape shape;

	public ModeButton(DrawableShape shape, DrawListener listener) {
		this.listener = listener;
		this.shape = shape;
		addActionListener(shapeListen);
	}

	public ModeButton(DrawableShape shape, DrawListener listener, String unicode) {
		this(shape, listener);
		setText(unicode);
	}

	public ModeButton(DrawableShape shape, DrawListener listener,
			ImageIcon image) {
		this(shape, listener);
		setIcon(image);
		setBackground(null);
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}

	// FIXME move out of here to canvas so only have one actionListener
	ActionListener shapeListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			listener.updateShape(shape);
		}
	};
}