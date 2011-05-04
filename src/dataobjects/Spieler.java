package dataobjects;

/**
 * Spieler-Klasse
 * 
 * @author Hendrik
 */
public class Spieler {

	private String name;
	private String farbe;
	private int einheitenAnzahl;
	private int laenderAnzahl;
	private int kontinentAnzahl;
	private int reserveArmeen;
	private Mission mission;
	
	//Konstruktor
	public Spieler(String n) {
		this.name = n;
	}

	// Getter & Setter

	public String getName() {
		return name;
	}

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

	public void setReserveArmeen(int reserveArmeen) {
		this.reserveArmeen = reserveArmeen;
	}

	public int getReserveArmeen() {
		return reserveArmeen;
	}
}