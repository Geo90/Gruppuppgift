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
			}
		} catch (InvalidUserException exception) {
			JOptionPane.showMessageDialog(null, "Fel användarnamn");
		}
		return true;
	}

	/**
	 * Sköter låntagningen av ett mediaobjekt
	 * @param mediaID det media objekt som man vill låna 
	 * @return true om lånet utfördes annars false
	 */
	public boolean loan(String mediaID) {
		boolean containsMedia = mediaTable.containsKey(mediaID);
		boolean borrowed = true;
		if (containsMedia) {
			Media media = mediaTable.get(mediaID);
			borrowed = media.borrowed();
			if (!borrowed) {
				user.addLoan(media);
				// media.setBorrowedStatus(true);
				return true;
			}
		} else {
			if (!containsMedia) {
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
		///////////////////KOMPLETTERAS MED IF SATSER & CATCH EXCEPTIONS
		indexOfMedia = loanList.indexOf(media);
		loanList.get(indexOfMedia);//.setBorrowedStatusFalse;
		loanList.remove(indexOfMedia);
		return true;
		
	}
	/**
	 * 
	 * @param mediaID
	 * @return
	 */
	public boolean search(String mediaID){
		return false;
	}
	public boolean logOut(){
		return false;
	}
	public boolean isInLibrary(){
		return false;
		
	}
	public boolean isBorrowed(){
		return false;
	}
}
