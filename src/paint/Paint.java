package paint;

public class Paint {
	public static void main(String[] args) {
		// make mnemonics always visible, without pressing alt
		// UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);

		new PaintFrame();
	}
}