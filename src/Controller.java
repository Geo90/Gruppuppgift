import javax.swing.JOptionPane;

import collections.*;
/**
 * Sköter användarens interaktion med användargränsnitten. Exempelvis styr vad som händer när man 
 * klickar på "borrow" knappen eller att den kontrollerar om låntagaren är en giltig användare
 * @author Benjamin Sejdic & Sebastion Sologuren 
 *
 */
public class Controller {
	private BinarySearchTree<String, Member> memberTree;
	private HashtableOH<String, Media> mediaTable;
	private GUI gui;
	private Member user;

	/**
	 * En konstruktor som initierar låntagarna, biblioteket och användargränssnittet
	 * @param memberTree trädet med låntagarna
	 * @param mediaTable en hashtabell med de olika mediaobjekten
	 * @param gui användargränsnittet
	 */
	public Controller(BinarySearchTree<String, Member> memberTree, HashtableOH<String, Media> mediaTable,
			GUI gui) {
		this.gui = gui;
		this.mediaTable = mediaTable;
		this.memberTree = memberTree;
	}

	/**
	 * Kontrollerar om det anginva personnummret tillhör en giltig låntagare
	 * @param memberID personnummer
	 * @return true om giltig låntagare annars false
	 */
	public boolean validUser(String memberID) {
		boolean containsMember = memberTree.contains(memberID);
		try {
			if (!containsMember) {
				throw new InvalidUserException("memberID: " + memberID + " has no user account in this system.");
			} else {
				this.user = memberTree.get(memberID);
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
			Media media = mediaTable.get(mediaID);
			if (!isBorrowed(mediaID)){
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
	public boolean returnLoan(String mediaID){
		int indexOfMedia;
		Media media = mediaTable.get(mediaID);
		ArrayList<Media> loanList = user.getLoanList();
		
		if(isBorrowed(mediaID)){
			return false;
		}
		else{
			indexOfMedia = loanList.indexOf(media);
			loanList.get(indexOfMedia).setBorrowedStatus(false);
			loanList.remove(indexOfMedia);
			return true;
		}
	}
	/**
	 * Söker efter om ett Media objekt finns i biblioteket och returnerar en
	 * String med titeln.
	 * @param mediaID Media ID
	 * @return String namnet på det sökta objektet om den finns annars null.
	 */
	public String search(String mediaID){
		if(isInLibrary(mediaID)){
			return mediaTable.get(mediaID).getTitle();
		}
		return null;
	}
	/**
	 * Sätter controllerns användare till null.
	 * @return boolean Om controllerns user-instansvariabel sattes till null
	 */
	public boolean logOut(){
		this.user=null;
		return true;
	}
	/**
	 * Kollar om ett Media objekt finns i biblioteket.
	 * Returnerar true om den finns i biblioteket.
	 * @param key Media ID
	 * @return boolean Om Media objektet finns i biblioteket.
	 */
	public boolean isInLibrary(String key){
		return mediaTable.containsKey(key);
	}
	/**
	 * Kollar om ett Media objekt är utlånat.
	 * Returnerar true om den är utlånad.
	 * @param key Media ID
	 * @return boolean Media objektets lånestatus
	 */
	public boolean isBorrowed(String key){
		return mediaTable.get(key).getBorrowedStatus();
	}
}
