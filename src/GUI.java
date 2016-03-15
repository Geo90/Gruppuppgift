
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is responsible for the Graphical User interface.
 * 
 * @author Erik Lewis Åkerman, Kablai Tokhi 15/3 - 2016
 *
 */
public class GUI {
	private JFrame frame = new JFrame("Media Library");
	private JTabbedPane theTabbedPane;
	private JPanel master, panelOne, panelTwo, panelThree;
	private JTextField logInField, searchField, borrowField, returnField;
	private JButton logInBtn, searchBtn, borrowBtn, returnBtn, refreshBtn;
	private JTextArea searchArea, myMediaArea;

	GUI() {

		GridBagConstraints gbc = new GridBagConstraints();
		// Get the JFrames and cast it as JPanel
		master = (JPanel) frame.getContentPane();

		// Set the Layout
		master.setLayout(new BorderLayout());

		// Initialize JTabbedPane
		theTabbedPane = new JTabbedPane();

		// Initialize components for Tab1

		logInField = new JTextField("Enter ID", 10);
		logInBtn = new JButton("Log in");

		// Initialize components for Tab2
		searchField = new JTextField("Search Media ID");
		searchBtn = new JButton("Search");
		borrowField = new JTextField("Borrow Media ID");
		borrowBtn = new JButton("Borrow");
		searchArea = new JTextArea(".......", 20, 25);

		// Initialize components for Tab3
		returnField = new JTextField("Return Media ID");
		returnBtn = new JButton("Return Media");

		refreshBtn = new JButton("Refresh");
		myMediaArea = new JTextArea(".......", 20, 25);

		// Initialize panelOne and add components
		panelOne = new JPanel(new GridBagLayout());
		panelOne.setBorder(BorderFactory.createTitledBorder("User Information"));

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelOne.add(logInField, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panelOne.add(logInBtn, gbc);

		// Initialize panelTwo and components
		gbc.anchor = GridBagConstraints.LINE_START;

		panelTwo = new JPanel(new GridBagLayout());
		panelTwo.setBorder(BorderFactory.createTitledBorder("Media Information"));

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelTwo.add(searchField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelTwo.add(searchBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelTwo.add(searchArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelTwo.add(borrowField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panelTwo.add(borrowBtn, gbc);

		// PanelThree
		panelThree = new JPanel(new GridBagLayout());
		panelThree.setBorder(BorderFactory.createTitledBorder("Your Media"));

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelThree.add(returnField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		panelThree.add(returnBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelThree.add(myMediaArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelThree.add(refreshBtn, gbc);

		// Add panelOne and panelTwo to theTabbedPane and name them
		theTabbedPane.add("Log In", panelOne);
		theTabbedPane.add("Search & Borrow", panelTwo);
		theTabbedPane.add("Return", panelThree);

		// Add theTabbedPane to the masterContainer
		master.add(theTabbedPane, BorderLayout.CENTER);

		// JFrame settings
		frame.setSize(650, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.theTabbedPane.setEnabledAt(1, false);
		this.theTabbedPane.setEnabledAt(2, false);

		addButtonListeners();

	}

	private void addButtonListeners() {
		ButtonListener listener = new ButtonListener();

		logInBtn.addActionListener(listener);
		searchBtn.addActionListener(listener);
		borrowBtn.addActionListener(listener);
		returnBtn.addActionListener(listener);
		refreshBtn.addActionListener(listener);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == logInBtn) {
				theTabbedPane.setEnabledAt(1, true);
				theTabbedPane.setEnabledAt(2, true);

			}
			if (e.getSource() == searchBtn) {
				searchArea.setText("Testing searchBtn....");
			}
			if (e.getSource() == borrowBtn) {
				searchArea.setText("Testing borrowBtn....");
			}
			if (e.getSource() == returnBtn) {
				myMediaArea.setText("Testing returnBtn....");
			}
			if (e.getSource() == refreshBtn) {
				myMediaArea.setText("Testing refreshBtn....");
			}

		}

	}

	public static void main(String[] args) {
		new GUI();

	}

}
