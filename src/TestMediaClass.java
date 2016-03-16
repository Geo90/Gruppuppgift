import collections.*;

public class TestMediaClass {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		/*
		 * Test av Book-klassens metoder:
		 * list.add("045747"); 
         * list.add("Danial Mahmoud");
		 * list.add("Sagan om Ringen"); 
		 * list.add("1995"); 
		 * Book book = new Book(list); 
		 * System.out.println(book.getId());
		 * System.out.println(book.getAuthor());
		 * System.out.println(book.getTitle());
		 * System.out.println(book.getYear());
		 */
         
		/*
		 * Test av DVD-klassens metoder:
		 */
		list.add("045747");
		list.add("Sagan om Ringen");
		list.add("1985");
		list.add("Gandalf");
		list.add("Aragorn");
		list.add("Legolas");
		list.add("Gimli");
		DVD dvd = new DVD(list);
		System.out.println(dvd.getId());
		System.out.println(dvd.getTitle());
		System.out.println(dvd.getYear());
		System.out.println(dvd.getActors());
		System.out.println(dvd.containsActor("Sauron"));
		System.out.println(dvd.containsActor("Gandalf"));
		String[] arr = dvd.getActors();
		for(String elem:arr)
			System.out.println(elem);
		System.out.println(dvd.getActorsAsString());
		
		
	}

}
