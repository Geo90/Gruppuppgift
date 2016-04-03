import javax.swing.*;
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
		gui.setController(this);
	}

	/**
	 * Kontrollerar om det anginva personnummret tillhör en giltig låntagare
	 * @param memberID personnummer
	 * @return true om giltig låntagare annars false
	 */
	public boolean validUser(String memberID) {
		if (!memberTree.checkUser(memberID)) {
			return false;
		} else {
			this.user = memberTree.getUser(memberID);
			return true;
		}
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

	public void setBorrowedStatus(String key, boolean status){
		library.setBorrowedStatus(key, status);
	}

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
		if(isInLibrary(key)){
			return library.getBorrowedStatus(key);
		}
		JOptionPane.showMessageDialog(null, "Objektet finns inte i biblioteket.");
		return false;
		
	}
	
	public ArrayList<Media> getListOfMedia(){
		return library.getListOfMedia();
	}
	
	/**
	 * Returnerar en lista över utlånade media objekt
	 * @return lista med utlånade media objekt
	 */
	public ArrayList<Media> getLoanList(){
		return user.getLoanList();
	}
	
	public boolean containsMedia(String key){
		return library. containsMedia(key);
	}
}
