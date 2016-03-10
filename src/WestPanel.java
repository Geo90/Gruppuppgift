import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {

	private JButton refreshBtn;
	private JButton logInBtn;
	private JButton returnBtn;
	private JTextField enterID;
	private JLabel borrowedLbl;
	private JTextArea borrowedArea;
	private JTextField mediaToReturn;

	WestPanel() {
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("User Information"));

		enterID = new JTextField("Enter ID");
		logInBtn = new JButton("Log in");
		borrowedLbl = new JLabel("Media you have borrowed:");
		borrowedArea = new JTextArea(".....", 20, 25);
		refreshBtn = new JButton("Refresh");
		mediaToReturn = new JTextField("Enter Media ID to return");
		returnBtn = new JButton("Return");

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		// gbc.insets = new Insets(15,15,15,15);

		/////// First Column
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(enterID, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(logInBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(borrowedLbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(borrowedArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		add(refreshBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(mediaToReturn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		add(returnBtn, gbc);

		addButtonListeners();

	}

	private void addButtonListeners() {
		ButtonListener listener = new ButtonListener();

		logInBtn.addActionListener(listener);
		refreshBtn.addActionListener(listener);
		returnBtn.addActionListener(listener);

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == logInBtn) {
				borrowedArea.setText("Testing logInBtn....");
			}
			if (e.getSource() == refreshBtn) {
				borrowedArea.setText("Testing refreshBtn....");
			}
			if (e.getSource() == returnBtn) {
				borrowedArea.setText("Testing returnBtn....");

			}

		}

	}

}
