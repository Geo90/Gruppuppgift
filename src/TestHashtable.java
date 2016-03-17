import collections.ArrayList;
import collections.HashtableOH;

public class TestHashtable {

	public TestHashtable(String filename, Hashtable ht){
		/*-----------------------------------------------
		 * 
		 * Testing hashtable operations and retrieval 
		 * of information
		 * 
		 *-----------------------------------------------
		 */
		HashtableOH hash = new HashtableOH(20);
		hash = ht.readMedia(filename);
		
		System.out.println("/*---------------------------------------------------------------------");
		System.out.println("Testing methods and retrival of information");
		System.out.println("/*---------------------------------------------------------------------");

		System.out.println("getFileRowsCount(filename): " + ht.getFileRowsCount(filename));
		System.out.println("hash.isEmpty(): " + hash.isEmpty());

		System.out.print("hash.list(): ");
		hash.list();

		System.out.println("/*---------------------------------------------------------------------");
		System.out.println("Testing keys...");
		
		String[] keys = { "427769", "635492", "874591", "456899", "123938", "775534", "722293", "237729" };
		for (int i = 0; i < keys.length; i++) {
			System.out.println("i: " + i);
			System.out.println("keys[" + i + "]: " + keys[i]); 
			System.out.println("containsMedia(keys[" + i + "]): " + ht.containsMedia(keys[i]));
			if(i==3){
				System.out.println("arrayMediaList.get(5).get(1).toString(): " + ht.getMediaList(5));
			}
		}
		System.out.println("Testing keys...");
		System.out.println("hash.get(498582): " + hash.get("498582"));
		System.out.println("arrayMediaList.get(hash.get(keys[0])): " + ht.getMediaList((int)hash.get("498582")));
		System.out.println(
				"arrayMediaList.get(hash.get(keys[0])).get(0): " + ht.getMediaList((int)hash.get(keys[0])).get(0));
		System.out.println("arrayMediaList.get(hash.get(keys[0])).get(0).getTitle()"
				+ ht.getMediaList((int)hash.get(keys[0])).get(0).getTitle());
		System.out.println("testing keys finished");
		System.out.println();

		ArrayList<Media> tempListOfMedia = null;
		tempListOfMedia = ht.getListOfMedia();

		System.out.println("tempListOfMedia.size(): " + tempListOfMedia.size());

		System.out.println("Getting library: ");
		System.out.println("----------------------");
		for (int i = 0; i < ht.getListOfMedia().size(); i++) {
			if(ht.getListOfMedia().get(i) != null)
				System.out.println(ht.getListOfMedia().get(i).toString());
		}
		System.out.println("----------------------------------------------------");
		System.out.println(ht.getMediaList(8).get(0).toString());
		System.out.println(ht.getMediaList(8).get(1).toString());
		System.out.println("----------------------------------------------------");
		System.out.println("getListOfMedia().get(6): "  + ht.getListOfMedia().get(6));
		System.out.println("getListOfMedia().get(7): " + ht.getListOfMedia().get(7));

		/*-----------------------------------------------
		 * 
		 * Testing communication /w media and subclasses
		 * 
		 *-----------------------------------------------
		 */
		System.out.println("/*-----------------------------------------------");
		System.out.println("Testing communication /w media and subclasses");
		System.out.println("/*-----------------------------------------------");

		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add("0000");
		tempList.add("De små igelkottarna och hönsen");
		tempList.add("2017");
		tempList.add("Igelkotten Kalle");
		tempList.add("Kråkan Borat");
		tempList.add("Sköldpaddan Blixten");
		Media testMedia = new DVD(tempList);
		ht.addMedia(testMedia, hash);

		for (int i = 0; i < ht.getMediaList((int)hash.get("0000")).size()
				&& ht.getMediaList((int)hash.get("0000")).get(i) != null; i++) {
			if (testMedia.getId() == ht.getMediaList((int)hash.get("0000")).get(0).getId()) {
				System.out.println(
						"arrayMediaList.get(hash.get(0000)).get(0): " + ht.getMediaList((int)hash.get("0000")).get(0));
			} else {
				System.out.println("NOT THIS MEDIA... searching next...");
			}
		}
		System.out.println("testMedia is of type DVD()");
		System.out.println("med.checkIfBook(med): " + testMedia.checkIfBook(testMedia));
		System.out.println("med.checkIfBook(med): " + testMedia.checkIfDVD(testMedia));
		System.out.println("testMedia.toString(): " + testMedia.toString());

		/*-----------------------------------------------
		 * 
		 * Testing storing same information again in the same hashtable
		 * 
		 *-----------------------------------------------
		 */

		int sizetemp = hash.size();
		hash.list();
		hash = ht.readMedia(filename, hash);
		hash.list();
		System.out.println("before: " + sizetemp);
		System.out.println("after: " + hash.size());
	}
	
	public static void main(String[] args){
		String filename = "filer/Media.txt";
		Hashtable ht = new Hashtable(filename);
		TestHashtable tht = new TestHashtable(filename, ht);
	}
	
}
