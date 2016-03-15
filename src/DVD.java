import java.util.ArrayList;

public class DVD extends Media{
	private String[] actors;
	private String name;
	
	public DVD(String title){
		super.setTitle(title);
	}
	
	public DVD(String[] actors){
		this.actors = new String[actors.length];
		for(int i = 0; i < actors.length; i++){
			this.actors[i] = actors[i];
		}
	}
	
	public DVD(ArrayList<String> list) {
		super.setId(list.get(0));
		super.setTitle(list.get(1));
		super.setYear(list.get(2));
		String[] actors;
		int nbrOfActors = 0;
		for(int i = 3; i < list.size(); i++){
			nbrOfActors++;
		}
		int k = 0;
		actors = new String[nbrOfActors];
		for(int i = 3; i < list.size(); i++){
			actors[k] = list.get(i);
			k++;
		}
		setActors(actors);
		super.setBorrowedStatus(false);
		
	}
	
	public String getActor(String name){
		String res = "";
		int pos = 0;
		for(int i = 0; i < actors.length; i++){
			if(name.equals(actors[i])){
				res = actors[i];
				pos++;
			}
			pos++;
		}
		if(res.equals("")){
			return "Skådespelaren finns inte";
		}
		return "Du letade efter: " + res + " som finns på position " + pos + " i vektorn actors.";
	}
	
	public void setActors(String[] names){
		actors = new String[names.length];
		for(int i = 0; i < names.length; i++){
			actors[i] = names[i];
		}
	}
	
	public String getActors(){
		String res = "";
		for(int i = 0; i < actors.length; i++){
			if(i < actors.length-1){
				res += actors[i] + "\n";
			}else{
				res += actors[i];
			}
		}
		return res;
	}
	
	public String toString(){
		String res = "";
		for(int i = 0; i < actors.length; i++){
			res += actors[i] + " ";
		}
		return super.toString() + " Skådespelare: " + res;

	}	
}
