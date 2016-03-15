import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import collections.*;

public class Hashtable {

	private HashtableOH hash;
	private static int size = 0;

	public Hashtable(String filename) {
		hash = new HashtableOH(getFileRowsCount(filename));
		hash = readMedia(filename);
	}

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

	public static HashtableOH<String, ArrayList<String>> readMedia(String filename) {
		HashtableOH<String, ArrayList<String>> res = new HashtableOH<String, ArrayList<String>>(size);
		ArrayList<String> m;
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
				//System.out.println(m.get(m.size()-1));
			//	System.out.println(m);
				//System.out.println(m.get(1));
				System.out.println(m.get(1));
				res.put((String)m.get(1), m);
				m.clear();
				text = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		//System.out.println(res.get("768841"));
		return res;
	}

	public String toString() {
		ArrayList<String> sList = new ArrayList<String>();
		System.out.println(hash.get("277877"));
		hash.list();
		System.out.println(sList.size());
		System.out.println(sList.get(sList.size()-1));
		System.out.println(sList);
		System.out.println(sList.get(1));

		//String s = String.format("Type of media: %s\n" + "ID: %d ", sList.get(0), sList.get(1));
		String s ="";
		return s;
	}

	public static void main(String[] args) {
		Hashtable ht = new Hashtable("filer/Media.txt");
		System.out.println(ht.toString());
	}
}











