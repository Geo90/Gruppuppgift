import javax.swing.JFrame;

public class App {

	public static void main(String[] args) {

		JFrame frame = new MainFrame("Testing Media Library");

		frame.setSize(650, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
