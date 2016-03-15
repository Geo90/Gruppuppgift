import javax.swing.JOptionPane;

import collections.*;
/**
 * Varje member har sina egna personuppgifter och kan logga in för att lämna ett Media objekt,
 * låna ett Media objekt och se vilka Media objekt denne har lånat.
 * 
 * @author Sebastian Sologuren & benjamin Sejdic
 *
 */


public class Member {
	private String memberID, name, phoneNumber;
	private ArrayList<Media> loanList;//ändra typ till Media sen
	
	/**
	 * Konstruerar en låntagare med personuppgifter samt ett ID.
	 * @param ID Lånetagarens ID 
	 * @param name Lånetagarens namn
	 * @param pNbr Lånetagarens telefonnummer
	 *
	 */
	public Member(String ID,String name,String pNbr){
		this.memberID=ID;
		this.name=name;
		this.phoneNumber=pNbr;
	}
	public void setMemberID(String ID){
		this.memberID=ID;
	}
	public String getMemberID(){
		return this.memberID;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	public void setPhoneNumber(String nbr){
		this.phoneNumber=nbr;
	}
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	public void addLoan(Media mediaObj){//ändra till Media sen
		if(loanList==null){
			loanList = new ArrayList<Media>();//ändra typ till Media sen
			loanList.add(mediaObj);
		}
		else{
			loanList.add(mediaObj);
		}
	}
	public void returnLoan(Media mediaObj){//ändra typ till Media sen
		int indexOf =(loanList.indexOf(mediaObj));
		if(indexOf != -1){
			//loanList.get(indexOf).//mediaObj//.setBorrowedStatus(false);
			loanList.remove(indexOf);
			JOptionPane.showMessageDialog(null, "mediaObj.setBorrowedStatus(false)");
		}
		else{
			JOptionPane.showMessageDialog(null, "Det här objektet finns inte i din lånelista.");
		}
	}
	public ArrayList<Media> getLoanList(){//ändra till Media sen
		return loanList;
	}
	public String toString(){
		return this.name+" "+this.memberID+" "+this.phoneNumber;
	}
	
}
