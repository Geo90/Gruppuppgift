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
public class EastPanel extends JPanel {

	private JButton searchBtn;
	private JButton refreshSearchBtn;
	private JButton borrowBtn;
	private JTextField searchID;
	private JLabel searchLbl;
	private JTextArea searchArea;
	private JTextField mediaToBorrow;

	EastPanel() {
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);

		setBorder(BorderFactory.createTitledBorder("Library Information"));

		searchID = new JTextField("Search ID");
		searchBtn = new JButton("Search");
		searchLbl = new JLabel("Search result:");
		searchArea = new JTextArea(".....", 20, 25);
		refreshSearchBtn = new JButton("Refresh");
		mediaToBorrow = new JTextField("Enter Media ID to borrow");
		borrowBtn = new JButton("Borrow");

		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(searchID, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(searchBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(searchLbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(searchArea, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		add(refreshSearchBtn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(mediaToBorrow, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		add(borrowBtn, gbc);

		addButtonListeners();

	}

	private void addButtonListeners() {
		ButtonListener listener = new ButtonListener();

		searchBtn.addActionListener(listener);
		refreshSearchBtn.addActionListener(listener);
		borrowBtn.addActionListener(listener);

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == searchBtn) {
				searchArea.setText("Testing searchBtn....");
			}
			if (e.getSource() == refreshSearchBtn) {
				searchArea.setText("Testing refreshSearchBtn....");
			}
			if (e.getSource() == borrowBtn) {
				searchArea.setText("Testing borrowBtn....");
			}
		}
	}
}
