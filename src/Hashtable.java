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
		arrayMediaList = new ArrayList<ArrayList<Media>>(size);
		
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
			int counter = 0;
			while (text != null) {
				m = new ArrayList();
				parts = text.split(";");
				for (int i = 0; i < parts.length; i++) {
					m.add((parts[i]));
				}
				Media med = getMedia(m);
				//System.out.println("Media: " + med);
				
				int hashIndex = hash.hashIndex(med.getId());
				//System.out.println("hashIndex: " + hashIndex);
				System.out.println("counter: " + counter++);
				res.put(med.getId(), hashIndex);
				
				mediaList = new ArrayList<Media>();
				mediaList.add(med);
				
				if(arrayMediaList.get(hashIndex) == null){
					arrayMediaList.add(hashIndex, mediaList);
					mediaList = arrayMediaList.get(hashIndex);

					//System.out.println("mediaList.get(0).toString(): " + mediaList.get(0).toString());
					
				}
				else{
					//System.out.println("readFile: null!");
					//System.out.println("readFile: " + med.toString());
					
					mediaList = getMediaList(hashIndex);
					mediaList.add(med);
					//System.out.println();
					//System.out.println();
					arrayMediaList.add(hashIndex, mediaList);
				}
				//System.out.println("med.getId(): " +med.getId());
				//System.out.println("hash.getKey(med.getId()): " +res.containsKey(med.getId()));
				//System.out.println("hash.get(): " + res.get(med.getId()));
				//System.out.println("arrayMediaList.get(int): " +arrayMediaList.get((int)res.get((String)med.getId())));
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}

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
				int hashIndex = hash.hashIndex(med.getId());
				res.put(med.getId(), hashIndex);
				//System.out.println("counter: " + counter++);
				mediaList = new ArrayList<Media>();
				mediaList.add(med);
				
				if(arrayMediaList.get(hashIndex) == null){
					arrayMediaList.add(hashIndex, mediaList);
					mediaList = arrayMediaList.get(hashIndex);
				}
				else{	
					mediaList = getMediaList(hashIndex);
					mediaList.add(med);
					arrayMediaList.add(hashIndex, mediaList);
				}
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}

	
	
	public Media getMedia(String key){
		ArrayList<Media> mediaList = new ArrayList<Media>();
		ArrayList<Media>  temp = new ArrayList<Media>();
		temp = arrayMediaList.get(hash.get(key));
		
		System.out.println("key: " + key);
		System.out.println("hash.get(key): " + hash.get(key));
		
		if(arrayMediaList.get(hash.get(key)) == null){
			media = null;
			System.out.print("Key does not exist");
		} else{
			System.out.println("arrayMediaList: " + arrayMediaList.get(hash.get(key)));
			System.out.println("arrayMediaList: " + arrayMediaList);
			System.out.println("temp: " + temp);
			System.out.println("temp.get(0): " + temp.get(0));
			System.out.println("temp.get(0).toString(): " + temp.get(0).toString());
			System.out.println("arrayMediaList.get(hash.get(key)): " + arrayMediaList.get(hash.get(key)));
			
			System.out.println("--------------for-loop START----------------");
			for(int i = 0; i<mediaList.size()-1 && mediaList.get(i) != null; i++){
				
				System.out.println("arrayMediaList.get(hash.get(key)).toString(): " + arrayMediaList.get(hash.get(key)).toString());
				mediaList = arrayMediaList.get(hash.get(key));
				System.out.println("mediaList.size(): " + mediaList.size());
				System.out.println("i: " + i);
				System.out.println("hashIndex: " + hash.get(key));
				System.out.println("mediaList.get(i).toString(): " + mediaList.get(i).toString());
				System.out.println("hash.get(key).equals(mediaList.get(i).getId()): " + hash.get(key).equals(mediaList.get(i).getId()));
				if(hash.get(key).equals(mediaList.get(i).getId()))
						media = mediaList.get(i);
			}
			System.out.println("--------------for-loop END ----------------");
		}		
		return media;
	}
	
	private Media getMedia(ArrayList<String> contents){
		if(isDVD(contents.get(0))){
			contents.removeFirst();
			media = new DVD(contents);
		} else{
			contents.removeFirst();
			media = new Book(contents);
		}
		return media;
	}
	
	
	public ArrayList<Media> getMediaList(int hashIndex){
		return arrayMediaList.get(hashIndex);
	}
	
	public String toString() {
		String s = "";
		return s;
	}

	
	/**
	 * Checks to see if media is DVD if media is not DVD then it has to be a Book
	 * @param media
	 * @return boolean
	 */
	private boolean isDVD(String media){
		String dvd = "Dvd";
		media.getClass();
		if(dvd.equals(media))
			return true;
		else
			return false;
	}
	
	public boolean doesContain(String key){
		return hash.containsKey(key);
	}
	
	
	/*
	public boolean isBorrowed(String mediaID){
		
	}
	*/

	
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
		 *
		System.out.println("Test if hashtable contains key 277877: " + hash.containsKey("277877"));
		System.out.println("Test if hashtable contains key 000000: " + hash.containsKey("000000"));
		System.out.println();		
		
		System.out.println("Test a couple of keys and writing out their contents:");
		Integer vList = new Integer(0);
		
		//Keys that are being tested
		String[] keys = {"775534", "277877", "427769", "635492", "722293"};
				
		for(int i=0; i<keys.length; i++){
			if(hash.get(keys[i]) != null){
				vList = (Integer) hash.get(keys[i]);
				media = getMedia(keys[i]);
			}
			else
				System.out.println("Key does not exist");			
			//String s = "";
			/*if(media){
				int actors = vList.size()-4;
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
			*
			//System.out.println(s);
			System.out.println();
		}
	
		
		/*-----------------------------------------------
		 * 
		 * Testing communication /w media and subclasses
		 * 
		 *-----------------------------------------------
		 *
		System.out.println("/*-----------------------------------------------");
		System.out.println("Testing communication /w media and subclasses");
		System.out.println("/*-----------------------------------------------");
		
		//Checking if hashtable contains the specified key
		for(int i = 0; i<keys.length; i++){
			System.out.println("Contains key " + keys[i]+": " + doesContain(keys[i]));
			vList = hash.get(keys[i]);
			System.out.println("Media: " + vList);
			//sList.removeFirst();
			//Retrieving a media object from the media-class
			media = getMedia(keys[i]);
			
			//media.setBorrowedStatus(false);
			//System.out.println("toString: " + media.toString());	
			//System.out.println();
			
			/*-----------------------------------------------
			 * 
			 * Testing storing same information again in the same hashtable
			 * 
			 *-----------------------------------------------
			 */
			int sizetemp = hash.size();
			System.out.println("before: "  + sizetemp);
			hash.list();
			hash = readMedia(filename, hash);
			hash.list();
			System.out.println("before: "  + sizetemp);
			System.out.println("after: " + hash.size());
		}
	

	

	
	
	
	
	public static void main(String[] args) {
		Hashtable ht = new Hashtable("filer/Media.txt");
		System.out.println(ht.toString());
		ht.testClass();
	}
}











