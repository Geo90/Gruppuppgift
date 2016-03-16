
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is responsible for the Graphical User interface.
 * 
 * @author Erik Lewis �kerman, Kablai Tokhi 15/3 - 2016
 *
 */
public class GUI {
	private JFrame frame = new JFrame("Media Library");
	private JTabbedPane theTabbedPane;
	private JPanel master, panelOne, panelTwo, panelThree;
	private JTextField logInField, searchField, borrowField, returnField;
	private JButton logInBtn, searchBtn, borrowBtn, returnBtn, refreshBtn;
	private JTextArea searchArea, myMediaArea;
	private ArrayList<TestMem> members;
	private ArrayList<TestMenjeke> media;
	private ArrayList<TestMenjeke> myMedia;

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
		logInField = new JTextField("Enter ID", 10);
		logInBtn = new JButton("Log in");

		// Initialize components for LabelTwo
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

		nameandmedia();

	}

	/**
	 * This method adds listeners to our buttons
	 */
	private void addButtonListeners() {
		ButtonListener listener = new ButtonListener();

		logInBtn.addActionListener(listener);
		searchBtn.addActionListener(listener);
		borrowBtn.addActionListener(listener);
		returnBtn.addActionListener(listener);
		refreshBtn.addActionListener(listener);
	}

	/**
	 * This class implements ActionListener and handles the action performed
	 * when a button is pressed.
	 * 
	 * @author Erik Lewis �kerman, Kablai Tokhi
	 *
	 */
	public void nameandmedia() {

		// Create new testmembers ArrayList.
		members = new ArrayList<>();
		TestMem kabbe = new TestMem("Kabbe");
		TestMem erik = new TestMem("Erik");
		TestMem benji = new TestMem("Benji");
		TestMem george = new TestMem("George");
		TestMem sebbe = new TestMem("Sebbe");
		TestMem danial = new TestMem("Danial");
		// Add 6 members.
		members.add(kabbe);
		members.add(erik);
		members.add(benji);
		members.add(george);
		members.add(sebbe);
		members.add(danial);

		// Loop through members.
		// for (int i = 0; i < members.size(); i++) {
		// System.out.println("member: " + members.get(i).getId());

		// Create new testmedia ArrayList.
		media = new ArrayList<>();
		TestMenjeke herre = new TestMenjeke("Flugornas Herre");
		TestMenjeke hitch = new TestMenjeke("Liftarens guide till galaxen");
		TestMenjeke sol = new TestMenjeke("Solens storm");
		TestMenjeke pfann = new TestMenjeke("Albanernas pannkakor");
		TestMenjeke lep = new TestMenjeke("Leprechauns at large");
		TestMenjeke space = new TestMenjeke("Spaceshit");
		// Add 6 medias.
		media.add(herre);
		media.add(hitch);
		media.add(sol);
		media.add(pfann);
		media.add(lep);
		media.add(space);

		// New arrayList
		this.myMedia = new ArrayList<>();

		// Loop through media
		// for (int i = 0; i < media.size(); i++) {
		// System.out.println("media: " + media.get(i).getName());

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int flag2 = 0;
			int flag = 0;
			if (e.getSource() == logInBtn) {
				for (int i = 0; i < members.size(); i++) {

					if (logInField.getText().equals(members.get(i).getId())) {
						theTabbedPane.setEnabledAt(1, true);
						theTabbedPane.setEnabledAt(2, true);
						flag = 0;
						break;
					} else {
						flag = 1;
					}
				}
			}

			if (flag == 1) {
				JOptionPane.showMessageDialog(null, "Fel ID, pr�va igen");
				theTabbedPane.setEnabledAt(1, false);
				theTabbedPane.setEnabledAt(2, false);
			}

			if (e.getSource() == searchBtn) {
				String list = "";
				for (int i = 0; i < media.size(); i++) {
					list += '\n' + media.get(i).getName();
					searchArea.setText(list);

				}

			}

			if (e.getSource() == borrowBtn) {

				String list6 = "";
				for (int i = 0; i < media.size(); i++) {
					String getMedia = media.get(i).getName();
					boolean isInLibrary = borrowField.getText().equals(getMedia);

					if (isInLibrary) {
						myMedia.add(media.get(i));
						media.remove(i);
						flag2 = 0;
						String list = "";
						for (int k = 0; k < media.size(); k++) {
							list += '\n' + media.get(k).getName();
							searchArea.setText(list);
						}
						for (int k = 0; k < myMedia.size(); k++) {
							list6 += '\n' + myMedia.get(k).getName();
							myMediaArea.setText(list6);
						}
						break;
					} else if (isInLibrary == false && media.get(i).getName() != null) {
						flag2 = 1;
					} else {
						flag2 = 2;
					}
				}
			}
			if (flag2 == 2) {
				JOptionPane.showMessageDialog(null, "Median finns inte");
			}
			if (e.getSource() == returnBtn) {

				int flag3 = 0;

				for (int i = 0; i < myMedia.size(); i++) {

					boolean userHasLoaned = returnField.getText().equals(myMedia.get(i).getName());
					if (userHasLoaned) {

						media.add(myMedia.get(i));

						myMedia.remove(i);

						String list10 = "";
						for (int k = 0; k < myMedia.size(); k++) {
							list10 += '\n' + myMedia.get(k).getName();
							flag3 = 0;
						}

						myMediaArea.setText(list10);
					} else {
						flag3 = 1;
					}
				}
				if (flag3 == 1) {
					JOptionPane.showMessageDialog(null, "Median finns inte l�nad");
				}
			}
			if (e.getSource() == refreshBtn) {
				JOptionPane.showMessageDialog(null, "message");
				String list3 = "";
				for (int i = 0; i < myMedia.size(); i++) {
					list3 += '\n' + myMedia.get(i).getName();
					myMediaArea.setText(list3);

				}
			}

		}

	}

	public static void main(String[] args) {

		new GUI();

	}

}
