package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shapes.Oval;
import shapes.OvalFill;
import shapes.Pencil;
import shapes.Rect;
import shapes.RectFill;

public class PaintFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Canvas canvas;
	private final DrawListener listener;
	private JColorChooser chooser;
	private JPanel eastPanel; // hold all the shape and line options
	private JPanel southPanel;

	private JMenuBar menu;

	public PaintFrame() {
		setSize(750, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Paint");
		setLayout(new BorderLayout());

		canvas = new Canvas(700, 600);
		add(canvas, BorderLayout.CENTER);

		JLabel location = new JLabel("0,0");
		listener = new DrawListener(canvas, location);
		addMouseListener(listener);
		addMouseMotionListener(listener);

		addMenu();

		eastPanel = new JPanel();
		// for now using BoxLayout because only two options. If need more rows, use GridLayout(10,2).
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		addShapes();
		eastPanel.add(Box.createVerticalStrut(35));
		addLineWidths();

		// add the panel of shape and sizw options to the main frame
		add(eastPanel, BorderLayout.EAST);

		southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		addColorChooser();
		southPanel.add(location, BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void addMenu() {
		menu = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		// save - set mnemonic in constructor
		JMenuItem save = new JMenuItem("Save", KeyEvent.VK_S);
		save.addActionListener(saveListen);
		file.add(save);

		// open - set mnemonic in constructor
		JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O);
		open.addActionListener(openListen);
		file.add(open);

		menu.add(file);
		setJMenuBar(menu);
	}

	private ActionListener saveListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showSaveDialog(null);
			File file = fileChooser.getSelectedFile();
			if (file != null) {
				String filename = file.getPath();
				try {
					ImageIO.write(canvas.getImage(), "png", new File(filename + ".png"));
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	};

	private ActionListener openListen = new ActionListener() {
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
	};

	private void addShapes() {
		eastPanel.add(new ShapeButton(new Pencil(), listener, "\u270E"));
		eastPanel.add(new ShapeButton(new Rect(), listener, "\u25A2"));
		eastPanel.add(new ShapeButton(new RectFill(), listener, "\u25FC"));
		eastPanel.add(new ShapeButton(new Oval(), listener, "\u25EF"));
		eastPanel.add(new ShapeButton(new OvalFill(), listener, "\u2B24"));
	}

	private void addLineWidths() {
		eastPanel.add(new WidthButton(2, canvas, "\u2500"));
		eastPanel.add(new WidthButton(5, canvas, "\u2500"));
		eastPanel.add(new WidthButton(7, canvas, "\u2501"));
		eastPanel.add(new WidthButton(9, canvas, "\u2501"));
	}

	private void addColorChooser() {
		// create color chooser with initial color set to black
		chooser = new JColorChooser(Color.BLACK);

		// remove unwanted, extra color chooser features like other color options and preview
		AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
		AbstractColorChooserPanel[] newPanels = { panels[0] };
		chooser.setChooserPanels(newPanels);
		chooser.setPreviewPanel(new JPanel());

		chooser.getSelectionModel().addChangeListener(colorChange);
		southPanel.add(chooser, BorderLayout.WEST);
	}

	ChangeListener colorChange = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			canvas.updateColor(chooser.getColor());
		}
	};
}