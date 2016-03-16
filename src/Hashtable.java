import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import collections.*;

/**
 * Reads in a .txt file in order to build up
 * a hashtable over the contents in the file
 * Communicates the contents in the hashtable
 * to the media-class and gets back an media-object
 * @author George
 *
 */
public class Hashtable {

	
	//Stores the information of many objects of type ArrayList<String>
	private HashtableOH<String, Integer> hash;
	//Stores the information about one object
	private ArrayList<String> m;
	//The size of the hashtable
	private int size = 0;
	//The file
	private String filename;
	//List that contains media-objects
	private ArrayList<ArrayList<Media>> arrayMediaList;
	private ArrayList<Media> mediaList;
	
	
	//Media-object in order to retrieve media
	Media media;
	
	
	/**
	 * Constructor that creates the hashtable from the file filename
	 * @param filename - file that contains information about the objects
	 */
	public Hashtable(String filename) {
		this.filename = filename;
		size = getFileRowsCount(filename);
		hash = new HashtableOH(size);
		mediaList = new ArrayList<Media>(size);
		
		hash = readMedia(filename);
	}

	/**
	 * Returns the number of rows in the file filename
	 * @param filename - the file that contains information about the objects
	 * @return size - int
	 */
	public int getFileRowsCount(String filename){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String
			text = "";
			while (text != null) {
				text = br.readLine();
				size++;
			}
		} catch (IOException e) {
		}
		return size;
	}
	
	/**
	 * Reads the text-file filename and creates the hashtable that stores the contents of the text-file
	 * @param filename
	 * @return HashtableOH<String, ArrayList<String>> - the hashtable that has been created from the file
	 */
	public HashtableOH<String, Integer> readMedia(String filename) {
		HashtableOH<String, Integer> res = new HashtableOH<String, Integer>(size);
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			while (text != null) {
				m = new ArrayList();
				parts = text.split(";");
				for (int i = 0; i < parts.length; i++) {
					m.add((parts[i]));
				}

				Media med = getMedia(m);				
				res.put(med.getId(), hash.hashIndex(med.getId()));
				mediaList.add(med);
				arrayMediaList.add(hash.hashIndex(med.getId()), mediaList);
				
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}
	
	public String toString() {
		//testClass();
		String s = "";
		return s;
	}
	
	/**
	 * Different tests to see if the information contained in the hashtable can be retrieved 
	 */
	
	private void testClass(){
		

		/*-----------------------------------------------
		 * 
		 * Testing hashtable operations and retrieval 
		 * of information
		 * 
		 *-----------------------------------------------
		 */
		System.out.println("Test if hashtable contains key 277877: " + hash.containsKey("277877"));
		System.out.println("Test if hashtable contains key 000000: " + hash.containsKey("000000"));
		System.out.println();		
		
		System.out.println("Test a couple of keys and writing out their contents:");
		ArrayList<String> sList = new ArrayList<String>();
		
		//Keys that are being tested
		String[] keys = {"775534", "277877", "427769", "635492", "722293"};
				
		for(int i=0; i<keys.length; i++){
			if(hash.get(keys[i]) != null)
				sList = (ArrayList<String>) hash.get(keys[i]);
			else
				System.out.println("Key does not exist");			
			String s = "";
			if(isDVD(sList.get(0))){
				int actors = sList.size()-4;
				s = String.format("Type of media: %s\n" + 
									"ID: %s\n" +
									"Title: %s\n" + 
									"Year: %s\n" +
									"Actors: ", sList.get(0), sList.get(1), sList.get(2), sList.get(3));
				for(int j=0; j<actors; j++){
					String actor = sList.get(4+j);
					if(j<actors-2)
					s+= String.format("%s, ", actor);
					else
						s+= String.format("%s", actor);
				}
			}else if(!isDVD(sList.get(0))){
				s = String.format("Type of media: %s\n" + 
								"ID: %s\n" +
								"Author: %s\n" + 
								"Title: %s\n" +
								"Year: %s", sList.get(0), sList.get(1), sList.get(2), sList.get(3), sList.get(4));
			}else
				s = "Media not recognized!";
			System.out.println(s);
			System.out.println();
		}
	}
		
		/*-----------------------------------------------
		 * 
		 * Testing communication /w media and subclasses
		 * 
		 *-----------------------------------------------
		 /
		System.out.println("/*-----------------------------------------------");
		System.out.println("Testing communication /w media and subclasses");
		System.out.println("/*-----------------------------------------------");
		
		//Checking if hashtable contains the specified key
		for(int i = 0; i<keys.length; i++){
			System.out.println("Contains key " + keys[i]+": " + doesContain(keys[i]));
			sList = (ArrayList<String>) hash.get(keys[i]);
			System.out.println("Media: " + sList.get(0));
			//sList.removeFirst();
			//Retrieving a media object from the media-class
			media = getMedia(keys[i]);
			
			media.setBorrowedStatus(false);
			System.out.println("toString: " + media.toString());	
			System.out.println();
			
			/*-----------------------------------------------
			 * 
			 * Testing storing same information again in the same hashtable
			 * 
			 *-----------------------------------------------
			 /
			hash.list();
			hash = readMedia(filename, hash);
			hash.list();
		}
	}
	*/
	
	/**
	 * Reads the text-file filename and creates the hashtable that stores the contents of the text-file
	 * @param filename
	 * @return HashtableOH<String, ArrayList<String>> - the hashtable that has been created from the file
	 */
	/*public HashtableOH<String, ArrayList<String>> readMedia(String filename) {
		HashtableOH<String, ArrayList<String>> res = new HashtableOH<String, ArrayList<String>>(size);
		String[] parts;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			text = br.readLine();
			while (text != null) {
				m = new ArrayList();
				parts = text.split(";");
				for (int i = 0; i < parts.length; i++) {
					m.add((parts[i]));
				}
				System.out.println(res.put(m.get(1), m));
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}
*/

	
	/**
	 * Checks to see if media is DVD if media is not DVD then it has to be a Book
	 * @param media
	 * @return boolean
	 */
	public boolean isDVD(String media){
		String dvd = "Dvd";
		if(dvd.equals(media))
			return true;
		else
			return false;
	}
	
	public boolean doesContain(String key){
		return hash.containsKey(key);
	}
	
	public Media getMedia(ArrayList<String> contents){
		if(isDVD(contents.get(0))){
			contents.removeFirst();
			System.out.println("getMedia year: " + contents.get(3));
			media = new DVD(contents);
		}else{
			contents.removeFirst();
			System.out.println("getMedia year: " + contents.get(3));
			media = new Book(contents);
		}
		return media;
	}
	
	/*
	public boolean isBorrowed(String mediaID){
		
	}
	*/
	public static void main(String[] args) {
		Hashtable ht = new Hashtable("filer/Media.txt");
		System.out.println(ht.toString());
		ht.testClass();
	}
}











