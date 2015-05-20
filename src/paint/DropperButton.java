package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class DropperButton extends JButton {
	private static final long serialVersionUID = 1L;

	public DropperButton(DrawListener listener) {
		setIcon(new ImageIcon(getClass().getResource("pics/dropper1.png")));
		setBackground(null);
		setBorder(new EmptyBorder(0, 0, 0, 0));

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.selectDropper();
			}
		});
	}
}