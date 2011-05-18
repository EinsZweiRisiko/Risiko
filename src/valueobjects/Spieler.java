package valueobjects;

import java.util.ArrayList;
import java.util.HashSet;

import domain.Laenderverwaltung;

/**
 * Spieler-Klasse
 * 
 * @author Hendrik
 */
public class Spieler {
	
	private String name;	//Spielername
	private String color;	//Spielerfarbe
	private ArrayList<Land> ownCountries = new ArrayList<Land>();	//besitzendes Länder
	private Laenderverwaltung laenderverwaltung;
	private HashSet<TerritoryCard> territoryCards = new HashSet<TerritoryCard>();

	//Konstruktor
	public Spieler(String n) {
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
		ArrayList<Kontinent> kontinente = laenderverwaltung.getConqueredContinents(ownCountries);
		
		// Anzahl der Bonuseinheiten, die der Spieler für seine Kontinente bekommt
		int bonus = 0;
		
		// Alle Kontinente durchgehen, die es gibt
		for (Kontinent kontinent : kontinente) {
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
	public void addCountry(Land land) {
		ownCountries.add(land);
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public ArrayList<Land> getOwnCountries() {
		// TODO Auto-generated method stub
		return ownCountries;
	}
}