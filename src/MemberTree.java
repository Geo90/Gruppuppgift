
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

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
		try {
			if (memberTree.contains(memberID)) {
				return true;
			} else {
				throw new InvalidUserException(memberID +" finns inte i systemet.");
			}

		} catch (InvalidUserException ex) {
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
		try {
			if (checkUser(memberID)) {
				return memberTree.get(memberID);
			}
			throw new InvalidUserException(memberID +" finns inte i systemet.");
		} catch (InvalidUserException ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param filename
	 *            filen vars text ska göras om till String värden.
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

	public void test() {
		memberTree.root().showTree();
	}

	public static void main(String[] args) {
		TestBSTree t = new TestBSTree();
		// Iterator<Member> iter = t.memberTree.iterator();
		// while(iter.hasNext()){
		// Member temp = iter.next();
		// System.out.println(temp.toString());
		// }
		Member memberTest = new Member("123", "Benji", "040532668235");
		System.out.println(memberTest.getLoanList());
		Book kalleAnka = new Book("Kalle Anka", "Kalle", "a", "b");
		Book LotR = new Book("LotR", "LotR", "a", "b");
		memberTest.addLoan(kalleAnka);
		memberTest.addLoan(LotR);
		System.out.println(memberTest.getLoanList().size());
		// System.out.println(memberTest.toString());
		// memberTest.setMemberID("100101010011001");
		// memberTest.setName("Benjamin");
		// memberTest.setPhoneNumber("040");
		// System.out.println(memberTest.toString());
		//
		// Book kalleAnka = new Book("Kalle Anka","Kalle","a","b");
		// memberTest.addLoan(kalleAnka);
		// ArrayList<Media> list = memberTest.getLoanList();
		// System.out.println("före return loan "+list);
		// System.out.println("size"+list.size());
		// memberTest.returnLoan(kalleAnka);
		// System.out.println("size"+list.size());
		// System.out.println("efter "+list);
		// t.test();

	}

}
