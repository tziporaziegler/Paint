package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveListener implements ActionListener {
	private JFileChooser chooser;
	private Canvas canvas;

	public SaveListener(Canvas canvas) {
		this.canvas = canvas;

		chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(false);
		chooser.setAcceptAllFileFilterUsed(false);

		addFilter("PNG", "png");
		addFilter("BMP", "bmp");
		addFilter("JPG", "jpg");
		addFilter("JPEG", "jpeg");
		addFilter("GIF", "gif");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.showSaveDialog(null);
		FileFilter chosenFilter = chooser.getFileFilter();
		String ext = chosenFilter.getDescription().toLowerCase();

		File file = chooser.getSelectedFile();

		if (file != null) {
			String filename = file.getPath();
			try {
				ImageIO.write(canvas.getImage(), ext, new File(filename + "." + ext));
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void addFilter(String description, String... extentions) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extentions);
		chooser.addChoosableFileFilter(filter);
	}
}