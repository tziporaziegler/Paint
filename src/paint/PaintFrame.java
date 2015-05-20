package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import paint.modes.Mode;
import paint.modes.Eraser;
import paint.modes.Line;
import paint.modes.Oval;
import paint.modes.OvalFill;
import paint.modes.Pencil;
import paint.modes.Rect;
import paint.modes.RectFill;

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
		int height = 620;
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Paint");
		setResizable(false);
		setLayout(new BorderLayout());

		canvas = new Canvas(width - 100, height - 200);
		add(canvas, BorderLayout.CENTER);

		// create color chooser with initial color set to black
		chooser = new JColorChooser(Color.BLACK);

		JLabel location = new JLabel("0,0");
		listener = new DrawListener(canvas, chooser, location);
		// BrushListener listener = new RectangleListener(canvas);
		addMouseListener(listener);
		addMouseMotionListener(listener);

		addMenu();

		eastPanel = new JPanel();
		// for now using BoxLayout because only two options. If need more rows,
		// use GridLayout(10,2).
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
		addShapes();
		eastPanel.add(Box.createVerticalStrut(35));

		// add line widths
		addWidth(2, "\u2500");
		addWidth(5, "\u2500");
		addWidth(7, "\u2500");
		addWidth(9, "\u2500");
		addWidth("custom");

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
		addMode(new Pencil(), "\u270E");
		addMode(new Line(), "\u2571");
		addMode(new Rect(), "\u25A2");
		addMode(new RectFill(), "\u25FC");
		addMode(new Oval(), "\u25EF");
		addMode(new OvalFill(), "\u2B24");
		addMode(new Eraser(), new ImageIcon(getClass().getResource("pics/eraser.png")));
		eastPanel.add(new DropperButton(listener));
	}

	private void addMode(Mode shape, String unicode) {
		ModeButton mode = new ModeButton(shape, unicode);
		mode.addActionListener(shapeListen);
		eastPanel.add(mode);
	}

	private void addMode(Mode shape, ImageIcon image) {
		ModeButton mode = new ModeButton(shape, image);
		mode.addActionListener(shapeListen);
		eastPanel.add(mode);
	}

	private void addWidth(int width, String unicode) {
		WidthButton button = new WidthButton(width, canvas, unicode);
		button.addActionListener(widthListen);
		eastPanel.add(button);
	}

	private void addWidth(String unicode) {
		WidthButton button = new WidthButton(canvas, unicode);
		button.addActionListener(otherListen);
		eastPanel.add(button);
	}

	private void addColorChooser() {
		// remove unwanted, extra color chooser features like other color
		// options and preview
		AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
		AbstractColorChooserPanel[] newPanels = { panels[0] };
		chooser.setChooserPanels(newPanels);

		// customize preview panel
		JLabel preview = new JLabel("\u25FC\u25FC\u25FC\u25FC\u25FC", JLabel.CENTER);
		preview.setFont(new Font("Serif", Font.BOLD, 18));
		chooser.setPreviewPanel(preview);

		chooser.getSelectionModel().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				canvas.updateColor(chooser.getColor());
			}
		});

		southPanel.add(chooser, BorderLayout.WEST);
	}

	ActionListener widthListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			WidthButton button = (WidthButton) e.getSource();
			button.updateStroke();
		}
	};

	ActionListener otherListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			WidthButton button = (WidthButton) e.getSource();
			String widthS = JOptionPane.showInputDialog("Enter the stroke width:");
			if (widthS != null) {
				button.setWidth(Integer.valueOf(widthS));
				button.updateStroke();
			}
		}
	};

	ActionListener shapeListen = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ModeButton button = (ModeButton) e.getSource();
			listener.updateShape(button.getShape());
		}
	};
}