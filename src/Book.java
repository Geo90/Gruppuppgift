import collections.ArrayList;

/**
 * Denna klass beskriver ett bok-objekt. Man kan sätta titel, år, författare och lånstatus, 
 * samt returnera dessa värden för en instans av klassen. Klassen är en subklass till Media 
 * och har därför alla dess metoder.
 * @author Danial Mahmoud
 *
 */
public class Book extends Media{
	private String author;
	
	public Book(){
		
	}
	/**
	 * Skapar ett Bok-objekt
	 * @param author Författare
	 * @param mediaId Media ID
	 * @param title Titel
	 * @param year Utgivnings år
	 * @param borrowed status för om den är lånad
	 */
	public Book(String author, String mediaId, String title, String year){
		super(mediaId,title,year,false);
		this.author = author;
	}
	
	/**
	 * Denna konstruktor tar emot en ArrayList av typen String. De olika positionerna i listan
	 * innehåller strängar som representerar instansvariablerna i Media-klassen. På position 0 
	 * i listan finns strängen mediaId, på position 1 finns författare osv. (se super-klassens
	 * konstruktorn). På så vis instansieras ett objekt av klassen med startvärden som läses in
	 * från en ArrayList.
	 * @param list
	 */
	public Book(ArrayList<String> list) {
		super(list.get(0), list.get(2), list.get(3), false);
		setAuthor(list.get(1));
	}
	
	/**
	 * Returnerar författaren för en instans av klassen.
	 * @return author
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * Sätter en författare för ett Book-objekt till author (input-parametern).
	 * @param author
	 */
	public void setAuthor(String author){
		this.author = author;
	}
	
	/**
	 * Returnerar en sträng-representation av objektet. 
	 */
	public String toString(){
		return super.toString() + " Författare: " + getAuthor();
	}
}