
// Klassen Media är abstrakt och måste därför ärvas. Böcker och dvd-filmer
// ska representeras av klasser vilka ärver Media-klassen.
public abstract class Media {
	private String mediaId;
	private String title;
	private String year;
	private boolean borrowed;

	public Media(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getId() {
		return mediaId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String year() {
		return year;
	}
	
	public boolean borrowed(){
		//Hur ska man avgöra ifall en bok är utlånad eller inte?
	}

	public boolean equals(Object obj) {
		Media media = (Media) obj;
		return mediaId.equals(media.getId());
	}
}
