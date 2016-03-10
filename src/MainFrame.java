import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private WestPanel westPanel;
	private EastPanel eastPanel;

	public MainFrame(String title) {
		super(title);

		// Set Layoutmanager
		setLayout(new BorderLayout());

		westPanel = new WestPanel();
		eastPanel = new EastPanel();
		// Add Swing components to frames content pane
		Container c = getContentPane();

		c.add(westPanel, BorderLayout.WEST);
		c.add(eastPanel, BorderLayout.EAST);

	}
}
