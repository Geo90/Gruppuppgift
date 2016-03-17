import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import collections.*;

/**
 *
 * Läser in en .txt fil för att kunna bygga upp en hashtabell över innehållet
 * i .txt filen och kommunicerar innehållet i hashtabellen till media-klassen
 * och får tillbaka ett media-objekt.
 * 
 * Sänder media-objekt till Controller.java. 
 * 
 *
 */
public class Hashtable {

	private HashtableOH<String, Integer> hash;			// Stores the information of many objects of type ArrayList<String>
	private ArrayList<String> m;						// Stores the information about one object
	private int size = 0;								// The size of the hashtable
	private String filename; 							// The file
	private ArrayList<ArrayList<Media>> arrayMediaList;	// List that contains media-objects
	Media media;										// Media-object in order to retrieve media

	/**
	 * Konstruktor som skapar en hashtabell från en fil vars namn
	 * anges som argument för konstruktorn.
	 * 
	 * @param filename - namn på filen som ska läsas in i hashtabellen
	 *            
	 */
	public Hashtable(String filename) {
		this.filename = filename;
		size = getFileRowsCount(filename);
		hash = new HashtableOH(size);
		arrayMediaList = new ArrayList<ArrayList<Media>>(size);
		hash = readMedia(filename);
	}

	/**
	 * Returnerar antalet rader i filen som läses in
	 * 
	 * @param filename - namn på filen som ska läsas in i hashtabellen
	 * @return size - int
	 */
	public int getFileRowsCount(String filename) {
		size = -1;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String text = "";
			while (text != null) {
				text = br.readLine();
				size++;
			}
		} catch (IOException e) {
		}
		return size;
	}

	/**
	 * Läser .txt-filen filename och skapar en hashtable som lagrar innehållet
	 * av .txt-filen
	 * 
	 * @param filename - namn på filen som ska läsas in i hashtabellen
	 * @return HashtableOH<String, Integer> - hashtabellen som skapades utav filen
	 */
	public HashtableOH<String, Integer> readMedia(String filename) {
		HashtableOH<String, Integer> res = new HashtableOH<String, Integer>(size);
		return readMedia(filename, res);
	}
	
	/**
	 * Läser .txt-filen filename och skapar en hashtable som lagrar innehållet
	 * av .txt-filen
	 *  
	 * @param filename - namn på filen som ska läsas in i hashtabellen
	 * @param hash
	 * @return HashtableOH<String, Integer> - hashtabellen som skapades utav filen
	 */
	public HashtableOH<String, Integer> readMedia(String filename, HashtableOH<String, Integer> hash) {
		HashtableOH<String, Integer> res = new HashtableOH<String, Integer>(size);
		res = hash;
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			int counter = 0;
			while (text != null) {
				m = new ArrayList();
				parts = text.split(";");
				for (int i = 0; i < parts.length; i++) {
					m.add((parts[i]));
				}
				Media med = getMedia(m);
				res = addMedia(med, res);
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}

	/**
	 * Returnerar ett Media-objekt som motsvarar argumentet key
	 * @param key - nyckel som används i hashtabellen och motsvarar mediaID
	 * @return Media
	 */
	public Media getMedia(String key) {
		ArrayList<Media> mediaList = new ArrayList<Media>();
		mediaList = getMediaList(hash.get(key));
		if (mediaList == null) {
			media = null;
			System.out.print("Key does not exist");
		} else {
			for (int i = 0; i < mediaList.size() - 1 && mediaList.get(i) != null; i++) {
				if (key.equals(mediaList.get(i).getId()))
					media = mediaList.get(i);
			}
		}
		return media;
	}

	/**
	 * Returnerar en ArrayList av typen Media som innehåller all media som
	 * finns lagrat
	 * @return ArrayList<Media>
	 */
	public ArrayList<Media> getListOfMedia() {
		ArrayList<Media> tempMediaList = new ArrayList<Media>();
		for (int i = 0; i < arrayMediaList.size(); i++) {
			if (arrayMediaList.get(i) != null) {
				for (int j = 0; j < arrayMediaList.get(i).size() && arrayMediaList.get(i).get(j) != null; j++) {
					tempMediaList.add(arrayMediaList.get(i).get(j));
				}
			}
		}
		return tempMediaList;
	}

	/**
	 * Returnerar ifall nykeln finns i hashtabellen
	 * @param key - av typen String som motsvarar mediaID 
	 * @return boolean
	 */
	public boolean containsMedia(String key) {
		boolean containsMedia = false;
		if (hash.containsKey(key)) {
			if (getMedia(key) != null){
				containsMedia = true;
			}
		}
		return containsMedia;
	}

	/**
	 * Returnerar en boolean som anger ifall Media-objektet med mediaID key 
	 * är lånad eller inte
	 * @param key - mediaID
	 * @return boolean
	 */
	public boolean getBorrowedStatus(String key) {
		boolean isBorrowed = true;
		Media med = null;
		med = getMedia(key);
		isBorrowed = med.getBorrowedStatus();
		return isBorrowed;
	}
	
	/**
	 * Anger om Media-objektet med mediaID key är utlånat
	 * eller inte
	 * @param key - av typen String
	 * @param status - av typen boolean
	 */
	public void setBorrowedStatus(String key, boolean status){
		Media med = null;
		med = getMedia(key);
		med.setBorrowedStatus(status);
	}
	
	
	/**
	 * Lägger till ett Media-objekt i hashtabellen 
	 * @param media - Media objekt som ska läggas till
	 * @param res - HashtableOH<String, Integer>
	 * @return HashtableOH<String, Integer>
	 */
	public HashtableOH<String, Integer> addMedia(Media media, HashtableOH<String, Integer> res) {
		int hashIndex = res.hashIndex(media.getId());
		System.out.println("hashIndex: " + hashIndex);
		System.out.println("med.getId(): " + media.getId());
		res.put(media.getId(), hashIndex);
		ArrayList<Media> mediaList = new ArrayList<Media>();
		if (getMediaList(hashIndex) == null) {
			System.out.println("List is null");
			mediaList.add(media);
			arrayMediaList.set(hashIndex, mediaList);
		} else {
			mediaList = getMediaList(hashIndex);
			mediaList.add(media);
			arrayMediaList.set(hashIndex, mediaList);
		}
		return res;
	}
	
	/**
	 * Returnerar ArrayListan som motsvarar argumentet hashIndex 
	 * och innehåller Media-objekt
	 * @param hashIndex - av typen String som motvarar mediaID
	 * @return ArrayList<Media>
	 */
	public ArrayList<Media> getMediaList(int hashIndex){
		return arrayMediaList.get(hashIndex);
	}

	/**
	 * Kräver metod i en annan klass för att kolla om argumentet av typen Media-objekt 
	 * är av typen DVD eller Bok. Klonar argumentet så att inga referenser finns till
	 * originalet och returnerar ett helt nytt media objekt som har identiskt innehåll
	 * som originalet
	 * 
	 * @param original - av typen Media
	 * @return Media
	 */
	private Media cloneMedia(Media original) {
		Media clone = original;
		System.out.println("Can't clone me!");
		return clone;
	}

	/**
	 * Kollar ifall objektet Media är av typen DVD eller Bok och returnerar en
	 * boolean
	 * @param media - av typen String
	 * @return boolean
	 */
	private boolean isDVD(String media) {
		String dvd = "Dvd";
		media.getClass();
		if (dvd.equals(media))
			return true;
		else
			return false;
	}

	/**
	 * Skapar Media-objektet och returnerar ett nytt Media-objekt 
	 * och anger att Media-objektet inte är utlånat
	 * @param contents - av typen ArrayList<String>
	 * @return Media
	 */
	private Media getMedia(ArrayList<String> contents) {
		if (isDVD(contents.get(0))) {
			contents.removeFirst();
			media = new DVD(contents);
		} else {
			contents.removeFirst();
			media = new Book(contents);
		}
		media.setBorrowedStatus(false);
		return media;
	}
}
