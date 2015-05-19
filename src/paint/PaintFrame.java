package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import shapes.DrawableShape;
import shapes.Eraser;
import shapes.Line;
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
		int width = 800;
		int height = 600;
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Paint");
		setResizable(false);
		setLayout(new BorderLayout());

		canvas = new Canvas(width - 100, height - 160);
		add(canvas, BorderLayout.CENTER);

		// create color chooser with initial color set to black
		chooser = new JColorChooser(Color.BLACK);

		JLabel location = new JLabel("0,0");
		listener = new DrawListener(canvas, chooser, location);
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
		save.addActionListener(new SaveListener(canvas));
		file.add(save);

		// open - set mnemonic in constructor
		JMenuItem open = new JMenuItem("Open", KeyEvent.VK_O);
		open.addActionListener(new OpenListener(canvas));
		file.add(open);

		menu.add(file);
		setJMenuBar(menu);
	}

	private void addShapes() {
		addShape(new Pencil(), "\u270E");
		addShape(new Line(), "\u2571");
		addShape(new Rect(), "\u25A2");
		addShape(new RectFill(), "\u25FC");
		addShape(new Oval(), "\u25EF");
		addShape(new OvalFill(), "\u2B24");
		addShape(new Eraser(), new ImageIcon(getClass().getResource("pics/eraser.png")));
		eastPanel.add(new DropperButton(listener));
	}

	private void addShape(DrawableShape shape, String unicode) {
		eastPanel.add(new ShapeButton(shape, listener, unicode));
	}

	private void addShape(DrawableShape shape, ImageIcon image) {
		eastPanel.add(new ShapeButton(shape, listener, image));
	}

	private void addLineWidths() {
		eastPanel.add(new WidthButton(2, canvas, "\u2500"));
		eastPanel.add(new WidthButton(5, canvas, "\u2500"));
		eastPanel.add(new WidthButton(7, canvas, "\u2501"));
		eastPanel.add(new WidthButton(9, canvas, "\u2501"));
		eastPanel.add(new WidthButton(canvas, "custom"));
	}

	private void addColorChooser() {
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