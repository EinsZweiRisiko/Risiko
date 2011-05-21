package valueobjects;

import java.util.ArrayList;
import java.util.HashSet;

import domain.TerritoryManager;

/**
 * Spieler-Klasse
 * 
 * @author Hendrik
 */
public class Player {
	
	private String name;	//Spielername
	private String color;	//Spielerfarbe
	private ArrayList<Territory> ownCountries = new ArrayList<Territory>();	//besitzendes Länder
	private TerritoryManager laenderverwaltung;
	private HashSet<TerritoryCard> territoryCards = new HashSet<TerritoryCard>();

	//Konstruktor
	public Player(String n) {
		this.name = n;
	}

	public int getAnzahlLaender() {
		// Lünder zühlen
		return ownCountries.size();
	}
	
	/**
	 * Gibt die Anzahl der Bonuseinheiten, die der Spieler für seine Kontinente bekommt
	 * 
	 * @return
	 */
	public int getContinentBonus() {
		// Alle Kontinente, die wir besitzen, herausfinden
		ArrayList<Continent> kontinente = laenderverwaltung.getConqueredContinents(ownCountries);
		
		// Anzahl der Bonuseinheiten, die der Spieler für seine Kontinente bekommt
		int bonus = 0;
		
		// Alle Kontinente durchgehen, die es gibt
		for (Continent kontinent : kontinente) {
			bonus += kontinent.getBonusSupply();
		}
		
		return bonus;
	}

	/**
	 * Fragt den Spieler, welche Karten er eintauschen müchte und gibt die Anzahl der Bonuseinheiten zurück
	 * @return
	 */
	public int useBonusCards() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * fügt Lünder hinzu
	 * @param land das Land was hinzugefügt wird
	 */
	public void addCountry(Territory land) {
		ownCountries.add(land);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public ArrayList<Territory> getOwnCountries() {
		// TODO Auto-generated method stub
		return ownCountries;
	}
}