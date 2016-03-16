

/**
 * Klassen Media är abstrakt och måste därför ärvas. Böcker och dvd-filmer
 * ska representeras av klasser vilka ärver Media-klassen.
 * @author Danial Mahmoud
 *
 */
public abstract class Media {
	private String mediaId;
	private String title;
	private String year;
	private boolean borrowed;
	
	/**
	 * Tom konstruktor för att instansiera ett Media objekt som sedan ska få en 
	 * referens till ett komplett Media objekt.
	 */
	public Media(){	
	}
	/**
	 * Konstruktorn skapar ett Media-objekt med ett id mediaId.
	 * @param mediaId
	 */
	public Media(String mediaId) {
		this.mediaId = mediaId;
	}
	
	/**
	 * Skapar ett Media-objekt med id:t mediaId, titeln title osv.
	 * @param mediaId
	 * @param title
	 * @param year
	 * @param borrowed
	 */
	public Media(String mediaId, String title, String year, boolean borrowed) {
		this.mediaId = mediaId;
		this.title = title;
		this.year = year;
		this.borrowed = borrowed;
	}
	
	/**
	 * Returnerar id:t för en instans av klassen. 
	 * @return mediId
	 */
	public String getId() {
		return mediaId;
	}
	
	/**
	 * Sätter id till mediaId för ett objekt av typen Media.
	 * @param mediaId
	 */
	public void setId(String mediaId){
		this.mediaId = mediaId;
	}
	
	/**
	 * Returnerar titeln 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sätter titeln till title
	 * @param title
	 */
	public void setTitle(String title){
		this.title = title;
	}
	
	/**
	 * Returnerar året för ett media-objekt
	 * @return year
	 */
	public String getYear() {
		return year;
	}
	
	/**
	 * Sätter året för objektet till year
	 * @param year
	 */
	public void setYear(String year){
		this.year = year;
	}
	
	/**
	 * Returnerar variabelen borrowed som avgör ifall ett Media-objekt är utlånat eller inte.
	 * @return borrowed
	 */
	public boolean getBorrowedStatus(){
		return borrowed;
	}
	
	/**
	 * Sätter objektets lånestatus till true (utlånad) eller false (ej utlånad).
	 * @param status
	 */
	public void setBorrowedStatus(boolean status){
		this.borrowed = status;
	}
    
	/**
	 * Avgör ifall ett objekt av typen Media har samma instansvärden som objektet obj.
	 */
	public boolean equals(Object obj) {
		Media media = (Media) obj;
		return mediaId.equals(media.getId());
	}
	
	/**
	 * Skriver ut en sträng som visar media-objektets egenskaper såsom titel, år etc.
	 */
	public String toString(){
		return "MediaID: " + getId() + " Titel: " + getTitle()
				+ " År: " + getYear();
	}	
}
