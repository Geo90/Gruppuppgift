import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.*;
import collections.*;

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
	private JButton logInBtn, searchBtn, borrowBtn, returnBtn;
	private JTextArea searchArea, myMediaArea;
	private Controller controller;
	private boolean loggedIn = false;

	/**
	 * Constructor that holds JFrame and the panels.
	 */
	GUI() {

		GridBagConstraints gbc = new GridBagConstraints();

		// Get the JFrames and cast it as JPanel
		master = (JPanel) frame.getContentPane();

		// Set the Layout
		master.setLayout(new BorderLayout());

		// Initialize JTabbedPane
		theTabbedPane = new JTabbedPane();

		// Initialize components for LabelOne
		logInField = new JTextField("891216-1111", 10);
		logInBtn = new JButton("Log in");

		// Initialize components for LabelTwo
		searchField = new JTextField("Search Media ID");
		searchField.setPreferredSize(new Dimension(100, 20));
		searchBtn = new JButton("Search");
		borrowField = new JTextField("834762");
		borrowField.setPreferredSize(new Dimension(100, 20));
		borrowBtn = new JButton("Borrow");
		searchArea = new JTextArea(".......", 20, 45);
		searchArea.setEditable(false);
		JScrollPane scrollp = new JScrollPane(searchArea);

		// Initialize components for Tab3
		returnField = new JTextField("Return Media ID");
		returnField.setPreferredSize(new Dimension(100, 20));
		returnBtn = new JButton("Return Media");

		myMediaArea = new JTextArea(".......", 20, 45);
		myMediaArea.setEditable(false);
		JScrollPane scrollp2 = new JScrollPane(myMediaArea);

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
		panelTwo.add(scrollp, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelTwo.add(borrowField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panelTwo.add(borrowBtn, gbc);

		// Initialize PanelThree and components
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
		panelThree.add(scrollp2, gbc);

		// Add panelOne and panelTwo to theTabbedPane and name them
		theTabbedPane.add("Log In", panelOne);
		theTabbedPane.add("Search & Borrow", panelTwo);
		theTabbedPane.add("Return", panelThree);

		// Add theTabbedPane to the masterContainer
		master.add(theTabbedPane, BorderLayout.CENTER);

		// JFrame settings
		frame.setSize(900, 750);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.theTabbedPane.setEnabledAt(1, false);
		this.theTabbedPane.setEnabledAt(2, false);

		initializeButtonListener();
	}

	/**
	 * This method adds listeners to our buttons
	 */
	private void initializeButtonListener() {
		ButtonListener listener = new ButtonListener();
		logInBtn.addActionListener(listener);
		searchBtn.addActionListener(listener);
		borrowBtn.addActionListener(listener);
		returnBtn.addActionListener(listener);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Media> loanList;
			ArrayList<Media> media;

			if (e.getSource() == logInBtn) {
				if (loggedIn) {
					theTabbedPane.setEnabledAt(1, false);
					theTabbedPane.setEnabledAt(2, false);
					logInBtn.setText("Log in");
					// logOut();
					logInField.setText("");
					loggedIn = false;

				} else if (controller.validUser(logInField.getText()) && !loggedIn) {
					theTabbedPane.setEnabledAt(1, true);
					theTabbedPane.setEnabledAt(2, true);
					logInBtn.setText("Log out");
					loggedIn = true;
					// Read library and display it
					String list = "";
					media = controller.getListOfMedia();
					for (int i = 0; i < controller.getListOfMedia().size(); i++) {
						if (controller.getListOfMedia().get(i) != null)
							list += (controller.getListOfMedia().get(i).toString() + "\n");
					}
					searchArea.setText(list);
				} else {
					JOptionPane.showMessageDialog(null, "Fel ID, prova igen");
					theTabbedPane.setEnabledAt(1, false);
					theTabbedPane.setEnabledAt(2, false);
				}
			}

			if (e.getSource() == searchBtn) {
				boolean contains = controller.containsMedia(searchField.getText());
				if (contains) {
					JOptionPane.showMessageDialog(null, "Finns i biblioteket.");
				} else {
					JOptionPane.showMessageDialog(null, "Finns inte biblioteket.");
				}
			}

			if (e.getSource() == borrowBtn) {
				String res = "";
				String input = borrowField.getText();
				if ((controller.isBorrowed(input))) {
					JOptionPane.showMessageDialog(null, "Objektet är utlånat");
				}
				if (controller.isInLibrary(input) && !(controller.isBorrowed(input))) {
					if (controller.loan(input)) {
						JOptionPane.showMessageDialog(null, "Objektet har lagts till i lånelistan");
						Iterator<Media> loanIter = controller.getLoanList().iterator();
						Media medi;
						while (loanIter.hasNext()) {
							medi = loanIter.next();
							res += medi.toString() + "\n";
						}
						myMediaArea.setText(res);
					}
				}
			}

			if (e.getSource() == returnBtn) {
				String input = returnField.getText();
				boolean found = false;
				ArrayList<Media> arrList = controller.getLoanList();
				for (int i = 0; i < arrList.size(); i++) {
					if (arrList.get(i) != null) {
						if (arrList.get(i).getId().equals(input)) {
							found = true;
							// TA BORT OBJEKT
							arrList.remove(i);
							// SÄTT BORROWED STATUS TILL FALSE
							controller.setBorrowedStatus(input, false);
							Iterator<Media> loanIter = controller.getLoanList().iterator();
							Media medi;
							String res2 = "";
							while (loanIter.hasNext()) {
								medi = loanIter.next();
								res2 += medi.toString() + "\n";
							}
							myMediaArea.setText(res2);
						}
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(null, "Du har inte lånat detta objekt.");
				}

			}
		}
	}
}
