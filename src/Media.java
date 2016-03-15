
// Klassen Media är abstrakt och måste därför ärvas. Böcker och dvd-filmer
// ska representeras av klasser vilka ärver Media-klassen.
public abstract class Media {
	private String mediaId;
	private String title;
	private String year;
	private boolean borrowed;

	public Media() {
		
	}
	
	public String getId() {
		return mediaId;
	}
	
	public void setId(String mediaId){
		this.mediaId = mediaId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year){
		this.year = year;
	}
	
	public void setBorrowedStatus(boolean status){
		this.borrowed = status;
	}

	public boolean equals(Object obj) {
		Media media = (Media) obj;
		return mediaId.equals(media.getId());
	}
	
	public String toString(){
		return "MediaID: " + getId() + " Titel: " + getTitle()
				+ " År: " + getYear();
	}	
}
