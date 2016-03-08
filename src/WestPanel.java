import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class WestPanel extends JPanel{

	WestPanel(){
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("User Information"));
		
		JTextField enterID = new JTextField("Enter ID");
		JLabel borrowedLbl = new JLabel("Media you have borrowed:");
		JTextArea borrowedArea = new JTextArea(".....", 20, 20);
		JButton refreshBtn = new JButton("Refresh");
		
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		/////// FIRST COLUMN
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(enterID, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(borrowedLbl, gbc);
		

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(borrowedArea, gbc);
		

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(refreshBtn, gbc);
		
		
		
	}
}
