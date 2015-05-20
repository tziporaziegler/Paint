package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class OpenListener implements ActionListener {
	private Canvas canvas;

	public OpenListener(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		if (file != null) {
			String filename = file.getPath();
			try {
				BufferedImage image = ImageIO.read(new File(filename));
				canvas.setImage(image);
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}