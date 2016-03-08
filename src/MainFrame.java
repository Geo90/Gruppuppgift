import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

		private WestPanel westPanel;
		private EastPanel eastPanel;
		public MainFrame(String title){
			super(title);
			
			
			// Set Layoutmanager
			setLayout(new BorderLayout());
			
			
			//Create Swing components
			JTextField enterID = new JTextField("Enter ID");
			JLabel borrowedLbl = new JLabel("Media you have borrowed:");
			JTextArea borrowedArea = new JTextArea(".....");
			JButton refreshBtn = new JButton("Refresh");
			JTextField searchLbl = new JTextField("Search ID, Title");
			
			westPanel = new WestPanel();
			eastPanel = new EastPanel();
			//Add Swing components to frames content pane
			Container c = getContentPane();
			
			//c.add(enterID, BorderLayout.WEST);
			//c.add(borrowedLbl, BorderLayout.WEST);
			//c.add(borrowedArea, BorderLayout.WEST);
			//c.add(searchLbl, BorderLayout.EAST);
			c.add(westPanel, BorderLayout.WEST);
			c.add(eastPanel, BorderLayout.EAST);
		
			
		}
}
