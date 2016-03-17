import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import collections.*;

/**
 * Sköter användarens interaktion med användargränsnitten. Exempelvis styr vad
 * som händer när man klickar på "borrow" knappen eller att den kontrollerar om
 * låntagaren är en giltig användare
 * 
 * @author Benjamin Sejdic & Sebastion Sologuren
 *
 */
public class Controller {
	private MemberTree memberTree;
	private Hashtable library;
	private GUI gui;
	private Member user;
	private boolean loggedIn;
	/**
	 * En konstruktor som initierar låntagarna, biblioteket och användargränssnittet
	 * @param memberTree trädet med låntagarna
	 * @param library en hashtabell med de olika mediaobjekten
	 * @param gui användargränsnittet
	 */
	public Controller(MemberTree memberTree, Hashtable library, GUI gui) {
		this.gui = gui;
		this.library = library;
		this.memberTree = memberTree;
		initializeButtonListener();
		gui.setController(this);
	}

	/**
	 * Kontrollerar om det anginva personnummret tillhör en giltig låntagare
	 * @param memberID personnummer
	 * @return true om giltig låntagare annars false
	 */
	public boolean validUser(String memberID) {
		try {
			if (!memberTree.checkUser(memberID)) {
				throw new InvalidUserException("memberID: " + memberID + " has no user account in this system.");
			} else {
				this.user = memberTree.getUser(memberID);
				return true;
			}
		} catch (InvalidUserException exception) {
			JOptionPane.showMessageDialog(null, "Fel användarnamn");
		}
		return false;
	}

	/**
	 * Sköter låntagningen av ett mediaobjekt
	 * @param mediaID det media objekt som man vill låna
	 * @return true om lånet utfördes annars false
	 */
	public boolean loan(String mediaID) {
		if (isInLibrary(mediaID)) {
			Media media = library.getMedia(mediaID);
			if (!isBorrowed(mediaID)) {
				user.addLoan(media);
				media.setBorrowedStatus(true);
				return true;
			}
		} else {
			if (!isInLibrary(mediaID)) {
				JOptionPane.showMessageDialog(null, "Det här media objektet finns inte i biblioteket.");
			} else {
				JOptionPane.showMessageDialog(null, "Den är tyvärr utlånad.");
			}
		}
		return false;
	}

	/**
	 * Metoden anropas då ett media objekt ska återlämnas
	 * @param mediaID media objektet som skall återlämnas
	 * @return true om återlämningen utfördes annars false
	 */
	public boolean returnLoan(String mediaID) {
		int indexOfMedia;
		Media media = library.getMedia(mediaID);
		ArrayList<Media> loanList = user.getLoanList();

		if (isBorrowed(mediaID)) {
			return false;
		} else {
			indexOfMedia = loanList.indexOf(media);
			loanList.get(indexOfMedia).setBorrowedStatus(false);
			loanList.remove(indexOfMedia);
			return true;
		}
	}

//	public String[] getLibrary() {
//		ArrayList<Media> lib = library.getListOfMedia(hashIndex); 
//															
//	}

	/**
	 * Sätter controllerns användare till null.
	 * @return boolean Om controllerns user-instansvariabel sattes till null
	 */
	public boolean logOut() {
		this.user = null;
		return true;
	}

	/**
	 * Kollar om ett Media objekt finns i biblioteket. Returnerar true om den
	 * finns i biblioteket.
	 * @param key Media ID
	 * @return boolean Om Media objektet finns i biblioteket.
	 */
	public boolean isInLibrary(String key) {
		return library.containsMedia(key);
	}

	/**
	 * Kollar om ett Media objekt är utlånat. Returnerar true om den är utlånad.
	 * @param key Media ID
	 * @return boolean Media objektets lånestatus
	 */
	public boolean isBorrowed(String key) {
		return library.getBorrowedStatus(key);
	}

	/**
	 * This method adds listeners to our buttons
	 */
	private void initializeButtonListener() {
		ButtonListener listener = new ButtonListener();
		JButton[] buttons = gui.getButtons();
		buttons[0].addActionListener(listener);
		buttons[1].addActionListener(listener);
		buttons[2].addActionListener(listener);
		buttons[3].addActionListener(listener);
		buttons[4].addActionListener(listener);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Media> loanList;
			ArrayList<Media> media;
			loggedIn = false;
			JTextField[] txtField = gui.getTextFields();
			JTextArea[] txtArea = gui.getJTextArea();
			JButton[] buttons = gui.getButtons();
			JTabbedPane tabs = gui.getTabs();
			
			if (e.getSource() == buttons[0]) {
				if (loggedIn) {
					System.out.println("kommer in i if satsen");
					tabs.setEnabledAt(1, false);
					tabs.setEnabledAt(2, false);
					buttons[0].setText("Log in");
					logOut();
					txtField[0].setText("");
					loggedIn = false;
				} else if (validUser(txtField[0].getText()) && !loggedIn) {
					System.out.println("kommer in i else if satsen");
					tabs.setEnabledAt(1, true);
					tabs.setEnabledAt(2, true);
					buttons[0].setText("Log out");
					loggedIn = true;
				} else {
					JOptionPane.showMessageDialog(null, "Fel ID, prova igen");
					tabs.setEnabledAt(1, false);
					tabs.setEnabledAt(2, false);
				}
			}
				if (e.getSource() == buttons[1]) { // KAN FIXAS NÄR RÄTT METOD LAGTS IN I Hashtable-klassen
//					loanList = user.getLoanList(); // ska vara media inte lånelistan för users
					JOptionPane.showMessageDialog(null, "123");
					media = library.getListOfMedia();
					JOptionPane.showMessageDialog(null, media);
					txtArea[0].setText(media.toString()); // ska vara media inte lånelista för user
				}
			
			if (e.getSource() == buttons[2]) {
				String input = txtField[1].getText();
				if (isInLibrary(input) && isBorrowed(input)) {
					if (loan(input)) {
						JOptionPane.showMessageDialog(null, "Objektet har lagts till i din lånelista.");
					}
				}

				if (e.getSource() == buttons[3]) {
					System.out.println("Sök");
					loanList = user.getLoanList();
					for (int i = 0; i < loanList.size(); i++) {
						boolean userHasLoaned = txtField[3].getText().equals(loanList.get(i).getClass().getName());
						if (userHasLoaned) {
						    media = library.getListOfMedia();
							media.add(loanList.get(i)); // myMedia.get(i));
							loanList.remove(i);
							txtArea[1].setText(loanList.toString());
						}
					}
				}

				if (e.getSource() == buttons[4]) {
					JOptionPane.showMessageDialog(null, "message");
					ArrayList<Media> media1 = user.getLoanList();
					txtArea[1].setText(media1.toString());
				}
			}

		}

	}

}
