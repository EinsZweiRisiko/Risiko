package valueobjects;

import java.util.ArrayList;

/**
 * Territory
 * 
 * @author Jannes
 * 
 */
public class Territory {

	private Player owner;
	private int units = 0;
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
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getAnzahlEinheiten() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Continent getKontinent() {
		return kontinent;
	}

	public void setKontinent(Continent kontinent) {
		this.kontinent = kontinent;
	}

}
