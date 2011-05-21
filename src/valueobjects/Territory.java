package valueobjects;

import java.util.ArrayList;


/**
 * Land-Klasse
 * @author Jannes
 *
 */
public class Territory {
	
	private Player besitzer;
	private int anzahlEinheiten = 0;
	private String name;
	private Continent kontinent;
	private ArrayList<Territory> nachbarn = new ArrayList<Territory>();
	
	public Territory(String name) {
		this.name = name;
		
	}
	
	public void addNachbar(Territory land) {
		nachbarn.add(land);
	}
	
	public boolean istNachbar(Territory land) {
		if (nachbarn.contains(land)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Getter und Setter
	
	public ArrayList<Territory> getNachbarn() {
		return nachbarn;
	}

	public String getName() {
		return name;
	}
	
	public Player getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(Player besitzer) {
		this.besitzer = besitzer;
	}

	public int getAnzahlEinheiten() {
		return anzahlEinheiten;
	}

	public void setAnzahlEinheiten(int anzahlEinheiten) {
		this.anzahlEinheiten = anzahlEinheiten;
	}

	public Continent getKontinent() {
		return kontinent;
	}

	public void setKontinent(Continent kontinent) {
		this.kontinent = kontinent;
	}
	
}
