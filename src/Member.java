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
	private ArrayList<Media> loanList= new ArrayList<Media>(10);
	
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
	
	/**
	 * Sätter en ID till en medlem
	 * @param ID id man vill sätta
	 */
	public void setMemberID(String ID){
		this.memberID=ID;
	}
	
	/**
	 * Hämtar ID från en medlem
	 * @return medlemens ID 
	 */
	public String getMemberID(){
		return this.memberID;
	}
	
	/**
	 * Sätter medlemens namn
	 * @param name namnet som man vill sätta 
	 */
	public void setName(String name){
		this.name=name;
	}
	
	/**
	 * Hämtar namnet på medlemen
	 * @return medlemens namn
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sätter medlemens telefonnummer 
	 * @param nbr telefonnummret som man vill sätta
	 */
	public void setPhoneNumber(String nbr){
		this.phoneNumber=nbr;
	}
	
	/**
	 * Hämtar telefonnummret på medlemen
	 * @return medlemens telefonnummer 
	 */
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	/**
	 * Lägger till ett media objekt i en utlånings lista
	 * @param mediaObj media objektet som ska läggas till i listan
	 */
	public void addLoan(Media mediaObj){
			loanList.add(mediaObj);
	}
	/**
	 * Sköter återlämningen av ett media objekt
	 * @param mediaObj det media objekt som ska lämnas tillbaka
	 */
	public void returnLoan(Media mediaObj){
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
	
	/**
	 * Returnerar en lista över utlånade media objekt
	 * @return lista med utlånade media objekt
	 */
	public ArrayList<Media> getLoanList(){
		return loanList;
	}
	
	/**
	 * Skriver ut medlemens namn + ID + telefonnummer 
	 */
	public String toString(){
		return this.name+" "+this.memberID+" "+this.phoneNumber;
	}
	
}
