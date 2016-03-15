import java.util.ArrayList;


public class DVD extends Media{
	private String[] actors;
	private String name;
	
	public DVD(ArrayList<String> list) {
		super.setId(list.get(1));
		super.setTitle(list.get(2));
		super.setYear(list.get(3));
		String[] actors;
		int length = 0;
		for(int i = 4; i < list.size(); i++){
			length++;
		}
		actors = new String[length];
		for(int i = 4; i < list.size(); i++){
			actors[i] = list.get(i);
		}
		setActors(actors);
		super.setBorrowedStatus(false);
		
	}
	
	public String getActor(String name){
		String res = "";
		for(int i = 0; i < actors.length; i++){
			if(name.equals(actors[i])){
				res = actors[i];
			}
		}
		return res;
	}
	
	public void setActors(String[] names){
		actors = new String[names.length];
		for(int i = 0; i < names.length; i++){
			actors[i] = names[i];
		}
		
	}
	public int getNbrActors(){
		return actors.length;
	}
	
}
