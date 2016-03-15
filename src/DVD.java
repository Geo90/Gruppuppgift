
public class DVD extends Media{
	private String[] actors;
	
	public DVD(String mediaId, String title) {
		super(mediaId);
		title = super.getTitle();
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
	
}
