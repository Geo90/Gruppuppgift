 

//package components;

/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class TabbedPaneDemo extends JPanel {
	private JPanel map = new JPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();
	public TabbedPaneDemo() {
       
    	super(new BorderLayout(2, 1));
        JPanel pan1 = new JPanel(new BorderLayout(3,1));
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("33");
        b1.setPreferredSize(new Dimension(20,20));
        b2.setPreferredSize(new Dimension(20,20));
        b3.setPreferredSize(new Dimension(20,20));
        JButton btn2 = new JButton("323");
        JPanel panel = new JPanel(new GridLayout(1,2));
       // panel.setPreferredSize(new Dimension(400,200));
        pan1.add(b1);
        pan1.add(b2);
        pan1.add(b3);
        panel.add(pan1,BorderLayout.WEST);
        panel.add(btn2,BorderLayout.EAST);
        this.add(panel,BorderLayout.SOUTH);
        ImageIcon icon = new ImageIcon("middle.jpg");
       
        JComponent panel1 = makeTextPanel("MAP");
        tabbedPane.add("Map",map);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_F1);
        
        JComponent panel2 = makeTextPanel("Text");
        tabbedPane.addTab("Text", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_F2);
        
        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Tab 3", icon, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_F3);
        
        JComponent panel4 = makeTextPanel(
                "Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", icon, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_F4);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add content to the window.
        TabbedPaneDemo tab = new TabbedPaneDemo();
        frame.add(tab, BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        tab.addTab();
        tab.removeTab();
        frame.repaint();
    }
    public void addTab(){
    	 JPanel panel5 = new JPanel();
         tabbedPane.add("Test",panel5);
         tabbedPane.setMnemonicAt(0, KeyEvent.VK_F1);
    }
    public void removeTab(){
   	 JPanel panel5 = new JPanel();
        tabbedPane.remove(4);//Fungerar med index
        //tabbedPane.removeAll();
        tabbedPane.repaint();
   }
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }
}
