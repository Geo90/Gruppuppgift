
public class Book extends Media{
	private String author;

	public Book(String mediaId, String author) {
		super(mediaId);
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}

}
