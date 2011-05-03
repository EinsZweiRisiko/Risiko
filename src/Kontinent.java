import java.util.ArrayList;


public class Kontinent {
	
	private String name;
	private ArrayList<Land> laenderListe = new ArrayList();
	
	public Kontinent(String n){
		this.setName(n);
	}
	
	public void setLaender(Land land){
		laenderListe.add(land);
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
