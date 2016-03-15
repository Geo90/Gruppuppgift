import java.util.ArrayList;

public class Book extends Media{
	private String author;
    
	public Book(String author){
		this.author = author;
	}
	
	public Book(String title, String author){
		super.setTitle(title);
		this.author = author;
	}
	
	public Book(ArrayList<String> list) {
		super.setId(list.get(0));
		setAuthor(list.get(1));
		super.setTitle(list.get(2));
		super.setYear(list.get(3));
		super.setBorrowedStatus(false);
	}
	
	public String getAuthor(){
		return author;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String toString(){
		return super.toString() + " Författare: " + getAuthor();
	}

}
