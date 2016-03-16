import javax.swing.JOptionPane;

public class Start {
	public static void main(String[] args){
		
		
		GUI gui = new GUI();
		JOptionPane.showMessageDialog(null, "new gui");
		Hashtable library = new Hashtable("filer/Media.txt");
		JOptionPane.showMessageDialog(null, "library");
		MemberTree memberTree = new MemberTree("filer/Lantagare.txt");
		JOptionPane.showMessageDialog(null, "new membertree");
		Controller controller = new Controller(memberTree, library, gui);
		JOptionPane.showMessageDialog(null, "new controller (sista)");
	}

}
