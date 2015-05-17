package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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

	public WidthButton(int width, Canvas canvas, ImageIcon image){
		this(width, canvas);
		setIcon(image);
	}
	
	ActionListener widthListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.updateStrokeWidth(width);
		}
	};
}