import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class EastPanel extends JPanel{

		EastPanel() {
		Dimension size = getPreferredSize();
		size.width = 300;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Library Information"));
	}
}


