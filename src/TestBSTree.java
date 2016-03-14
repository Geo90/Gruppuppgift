import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import collections.*;
public class TestBSTree {
	
	private BinarySearchTree<String,Member> memberList = readMembers("filer/Lantagare.txt");
	
	public TestBSTree(){
		
	}
	public static BinarySearchTree<String,Member> readMembers(String filename) {
		BinarySearchTree<String,Member> res = new BinarySearchTree<String,Member>();
		Member member;
		String[] name;
		String text;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename), "UTF-8"));
			
			while ( ( text = br.readLine() ) != null) {
				name = text.split(";");
				member= new Member((name[0]),(name[1]), name[2]);				
				res.put(member.getMemberID(),member);
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return res;
	}
	
	public static void main(String[] args){
		TestBSTree t = new TestBSTree();
		Iterator<Member> iter = t.memberList.iterator();
		while(iter.hasNext()){
			Member temp = iter.next();
			System.out.println(temp.toString());
		}
		Member memberTest = new Member("123","Benji","040532668235");
		System.out.println(memberTest.toString());
		memberTest.setMemberID("100101010011001");
		memberTest.setName("Benjamin");
		memberTest.setPhoneNumber("040");
		System.out.println(memberTest.toString());
		memberTest.addLoan("kalle anka");
		ArrayList<String> list = memberTest.getLoanList();
		System.out.println("före return loan "+list);
		memberTest.returnLoan("kalle anka");
		System.out.println("efter "+list);
	}


}
