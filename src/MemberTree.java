
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import collections.*;

/**
 * BinarySearchTree där Member objekt lagras.
 * 
 * @author Sebastian Sologuren & Benjamin Sejdic
 *
 */
public class MemberTree {
	private BinarySearchTree<String, Member> memberTree;

	public MemberTree(String filename) {
		memberTree = readMembers(filename);
	}

	/**
	 * Denna metod kontrollerar ifall användar ID:t som angetts existerar i
	 * trädet av Member objekt.
	 * 
	 * @param memberID
	 *            Användarens ID
	 * @return boolean om användarens ID fanns in systemet
	 */
	public boolean checkUser(String memberID) {
		if (memberTree.contains(memberID)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returnerar ett Member objekt som matchar Member-objekt med angivet memberID i Member-trädet.
	 * Om det ej finns returneras null.
	 * @param memberID Användar-ID
	 * @return Member Member objektet med angivet användar-ID
	 */
	public Member getUser(String memberID) {
		if (checkUser(memberID)) {
			return memberTree.get(memberID);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param filename filen vars text ska göras om till String värden.
	 * @return res ett BinarySearchTree<String,Member> objekt
	 */
	public static BinarySearchTree<String, Member> readMembers(String filename) {
		BinarySearchTree<String, Member> res = new BinarySearchTree<String, Member>();
		Member member;
		String[] name;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));

			while ((text = br.readLine()) != null) {
				name = text.split(";");
				member = new Member((name[0]), (name[1]), name[2]);
				res.put(member.getMemberID(), member);
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}

}
