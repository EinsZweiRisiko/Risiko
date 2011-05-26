package valueobjects;

import java.util.ArrayList;
import java.util.HashSet;

import domain.TerritoryManager;

/**
 * Spieler-Klasse
 * 
 * @author Jannes, Hendrik
 */
public class Player {

	private String name; // Spielername
	private String color; // Spielerfarbe
	private ArrayList<Territory> territories = new ArrayList<Territory>(); // besitzendes Länder
	private TerritoryManager territoryManager;
	private HashSet<TerritoryCard> territoryCards = new HashSet<TerritoryCard>();
	private int supply;

	public int getSupply() {
		return supply;
	}

	// Konstruktor
	public Player(String n) {
		this.name = n;
	}

	public int getTerritoryNumber() {
		// Länder zühlen
		return territories.size();
	}

	/**
	 * Gibt die Anzahl der Bonuseinheiten, die der Spieler für seine Kontinente bekommt
	 * 
	 * @return
	 */
//	public int getContinentBonus() {
//		// Alle Kontinente, die wir besitzen, herausfinden
//		ArrayList<Continent> kontinente = territoryManager.getConqueredContinents(territories);
//
//		// Anzahl der Bonuseinheiten, die der Spieler für seine Kontinente bekommt
//		int bonus = 0;
//
//		// Alle Kontinente durchgehen, die es gibt
//		for (Continent kontinent : kontinente) {
//			bonus += kontinent.getBonusSupply();
//		}
//
//		return bonus;
//	}

	/**
	 * Fügt Länder hinzu
	 * 
	 * @param territory
	 *            Das Land, das hinzugefügt wird
	 */
	public void addTerritory(Territory territory) {
		territories.add(territory);
		territory.setOwner(this);
	}

	/**
	 * Liefert die Länder
	 * @return Länder, die der Spieler besitzt
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	public String getName() {
		return name;
	}

	public void setSupply(int supply) {
		this.supply = supply;
		
	}
}