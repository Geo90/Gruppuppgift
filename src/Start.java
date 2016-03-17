import javax.swing.JOptionPane;

public class Start {
	public static void main(String[] args){
		
		
		GUI gui = new GUI();
		Hashtable library = new Hashtable("filer/Media.txt");
		MemberTree memberTree = new MemberTree("filer/Lantagare.txt");
		new Controller(memberTree, library, gui);
	}

}
