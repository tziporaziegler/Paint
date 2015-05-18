package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class WidthButton extends JButton {
	private static final long serialVersionUID = 1L;
	private int width;
	private Canvas canvas;

	public WidthButton(int width, Canvas canvas) {
		this.width = width;
		this.canvas = canvas;
		addActionListener(widthListen);
	}

	public WidthButton(int width, Canvas canvas, String unicode) {
		this(width, canvas);
		setText(unicode + " " + width + "p");
	}

	public WidthButton(Canvas canvas, String string) {
		this.canvas = canvas;
		setText(string);
		addActionListener(otherListen);
	}

	public WidthButton(int width, Canvas canvas, ImageIcon image) {
		this(width, canvas);
		setIcon(image);
	}

	private void updateStroke() {
		canvas.updateStrokeWidth(width);
	}

	ActionListener widthListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			updateStroke();
		}
	};

	ActionListener otherListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String widthS = JOptionPane.showInputDialog("Enter the stroke width:");
			if (widthS != null) {
				width = Integer.valueOf(widthS);
				updateStroke();
			}
		}
	};
}