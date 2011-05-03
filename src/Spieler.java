
public class Spieler {
	
	private String name;
	private String farbe;
	private int einheitenAnzahl;
	private int laenderAnzahl;
	private int kontinentAnzahl;
	private Mission mission;
	
	public Spieler(String n){
		this.name = n;
	}
	
	//Getter & Setter
	
	public String getFarbe() {
		return farbe;
	}
	
	public int getLaender() {
		return laenderAnzahl;
	}
	
	public int getKontinente() {
		return kontinentAnzahl;
	}
	
	public Mission getMission() {
		return mission;
	}
}