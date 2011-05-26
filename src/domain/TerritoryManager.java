package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * 
 * @author Jannes, Timur
 * 
 */
public class TerritoryManager {

	/**
	 * Liste mit allen Ländernamen
	 */
	private final String[] territoryNames = { "Alaska", "Nordwest-Territorium", "Grönland",
			"Alberta", "Ontario", "Quebec", "Weststaaten", "Oststaaten", "Mittelamerika",
			"Venezuela", "Peru", "Brasilien", "Argentinien", "Nordwestafrika", "Ägypten",
			"Ostafrika", "Kongo", "Südafrika", "Madagaskar", "Island", "Skandinavien", "Ukraine",
			"Großbritannien", "Mitteleuropa", "Westeuropa", "Südeuropa", "Ural", "Sibirien",
			"Jakutien", "Irkutsk", "Kamtschatka", "Mongolei", "Japan", "Afghanistan", "China",
			"Mittlerer Osten", "Indien", "Siam", "Indonesien", "Neu-Guinea", "West-Australien",
	"Ost-Australien" };

	/**
	 * Eine Liste aller Grenzen, die zwischen jeweils zwei Ländern verlaufen
	 */
	private final String[][] borders = { { "Alaska", "Nordwest-Territorium" },
			{ "Alaska", "Alberta" }, { "Alaska", "Kamtschatka" },
			{ "Nordwest-Territorium", "Alberta" }, { "Nordwest-Territorium", "Grönland" },
			{ "Nordwest-Territorium", "Ontario" }, { "Alberta", "Ontario" },
			{ "Alberta", "Weststaaten" }, { "Ontario", "Oststaaten" }, { "Ontario", "Quebec" },
			{ "Ontario", "Grönland" }, { "Grönland", "Quebec" }, { "Grönland", "Island" },
			{ "Weststaaten", "Oststaaten" }, { "Weststaaten", "Mittelamerika" },
			{ "Oststaaten", "Quebec" }, { "Oststaaten", "Mittelamerika" },
			{ "Mittelamerika", "Venezuela" }, { "Venezuela", "Peru" },
			{ "Venezuela", "Brasilien" }, { "Peru", "Brasilien" }, { "Peru", "Argentinien" },
			{ "Brasilien", "Nordwestafrika" }, { "Brasilien", "Argentinien" },
			{ "Nordwestafrika", "Westeuropa" }, { "Nordwestafrika", "Südeuropa" },
			{ "Nordwestafrika", "Ägypten" }, { "Nordwestafrika", "Ostafrika" },
			{ "Nordwestafrika", "Kongo" }, { "Ägypten", "Mitteleuropa" },
			{ "Ägypten", "Südeuropa" }, { "Ägypten", "Ostafrika" },
			{ "Ostafrika", "Mittlerer Osten" }, { "Ostafrika", "Kongo" },
			{ "Ostafrika", "Südafrika" }, { "Ostafrika", "Madagaskar" }, { "Südafrika", "Kongo" },
			{ "Südafrika", "Madagaskar" }, { "Westeuropa", "Mitteleuropa" },
			{ "Westeuropa", "Südeuropa" }, { "Westeuropa", "Großbritannien" },
			{ "Großbritannien", "Skandinavien" }, { "Großbritannien", "Island" },
			{ "Großbritannien", "Mitteleuropa" }, { "Mitteleuropa", "Ukraine" },
			{ "Mitteleuropa", "Skandinavien" }, { "Island", "Skandinavien" },
			{ "Skandinavien", "Ukraine" }, { "Südeuropa", "Mittlerer Osten" },
			{ "Mittlerer Osten", "Indien" }, { "Mittlerer Osten", "Afghanistan" },
			{ "Mittlerer Osten", "Ukraine" }, { "Ukraine", "Afghanistan" }, { "Ukraine", "Ural" },
			{ "Ural", "Sibirien" }, { "Ural", "Afghanistan" }, { "Ural", "China" },
			{ "Afghanistan", "China" }, { "Afghanistan", "Indien" }, { "Indien", "China" },
			{ "Indien", "Siam" }, { "China", "Mongolei" }, { "China", "Siam" },
			{ "China", "Sibirien" }, { "Sibirien", "Mongolei" }, { "Sibirien", "Irkutsk" },
			{ "Sibirien", "Kamtschatka" }, { "Jakutien", "Irkutsk" },
			{ "Jakutien", "Kamtschatka" }, { "Irkutsk", "Kamtschatka" },
			{ "Japan", "Kamtschatka" }, { "Japan", "Mongolei" }, { "Mongolei", "Kamtschatka" },
			{ "Siam", "Indonesien" }, { "Indonesien", "Neu-Guinea" },
			{ "Indonesien", "West-Australien" }, { "West-Australien", "Ost-Australien" },
			{ "West-Australien", "Neu-Guinea" }, { "Ost-Australien", "Neu-Guinea" } };

	/**
	 * Liste aller Kontinente
	 */
	private Continent[] continente = new Continent[6];

	/**
	 * Liste aller Länderobjekte als Hasthable
	 */
	private Hashtable<String, Territory> territories = new Hashtable<String, Territory>();

	/**
	 * Konstruktor
	 */
	public TerritoryManager() {
		// Länder erstellen
		for (String name : territoryNames) {
			territories.put(name, new Territory(name));
		}

		// Nachbarländer zuweisen
		initNeighboringCountries();

		/*
		 * Bonuseinheiten pro Kontinent Asien = 7 Europa = 5 Nord Amerika = 5 Süd Amerika = 2 Afrika =
		 * 3 Australien = 2
		 */

		// Nordamerika erstellen
		continente[0] = new Continent("Nordamerika", 5);
		continente[0].addTerritory(territories.get("Alaska"));
		continente[0].addTerritory(territories.get("Nordwest-Territorium"));
		continente[0].addTerritory(territories.get("Grönland"));
		continente[0].addTerritory(territories.get("Alberta"));
		continente[0].addTerritory(territories.get("Ontario"));
		continente[0].addTerritory(territories.get("Quebec"));
		continente[0].addTerritory(territories.get("Weststaaten"));
		continente[0].addTerritory(territories.get("Oststaaten"));
		continente[0].addTerritory(territories.get("Mittelamerika"));

		// Südamerika erstellen
		continente[1] = new Continent("Südamerika", 2);
		continente[1].addTerritory(territories.get("Venezuela"));
		continente[1].addTerritory(territories.get("Peru"));
		continente[1].addTerritory(territories.get("Brasilien"));
		continente[1].addTerritory(territories.get("Argentinien"));

		// Afrika erstellen
		continente[2] = new Continent("Afrika", 3);
		continente[2].addTerritory(territories.get("Nordwestafrika"));
		continente[2].addTerritory(territories.get("Ägypten"));
		continente[2].addTerritory(territories.get("Ostafrika"));
		continente[2].addTerritory(territories.get("Kongo"));
		continente[2].addTerritory(territories.get("Südafrika"));
		continente[2].addTerritory(territories.get("Madagaskar"));

		// Europa erstellen
		continente[3] = new Continent("Europa", 5);
		continente[3].addTerritory(territories.get("Island"));
		continente[3].addTerritory(territories.get("Skandinavien"));
		continente[3].addTerritory(territories.get("Ukraine"));
		continente[3].addTerritory(territories.get("Großbritannien"));
		continente[3].addTerritory(territories.get("Mitteleuropa"));
		continente[3].addTerritory(territories.get("Westeuropa"));
		continente[3].addTerritory(territories.get("Südeuropa"));

		// Asien erstellen
		continente[4] = new Continent("Asien", 7);
		continente[4].addTerritory(territories.get("Ural"));
		continente[4].addTerritory(territories.get("Sibirien"));
		continente[4].addTerritory(territories.get("Jakutien"));
		continente[4].addTerritory(territories.get("Irkutsk"));
		continente[4].addTerritory(territories.get("Kamtschatka"));
		continente[4].addTerritory(territories.get("Mongolei"));
		continente[4].addTerritory(territories.get("Japan"));
		continente[4].addTerritory(territories.get("Afghanistan"));
		continente[4].addTerritory(territories.get("China"));
		continente[4].addTerritory(territories.get("Mittlerer Osten"));
		continente[4].addTerritory(territories.get("Indien"));
		continente[4].addTerritory(territories.get("Siam"));

		// Australien erstellen
		continente[5] = new Continent("Australien", 2);
		continente[5].addTerritory(territories.get("Indonesien"));
		continente[5].addTerritory(territories.get("Neu-Guinea"));
		continente[5].addTerritory(territories.get("West-Australien"));
		continente[5].addTerritory(territories.get("Ost-Australien"));
	}

	/**
	 * Berechnet aus der Grenzliste für jedes Land alle benachbarten Länder
	 */
	private void initNeighboringCountries() {
		Territory territory1;
		Territory territory2;
		for (String[] border : borders) {
			// Land-Objekte holen
			territory1 = getTerritoryByName(border[0]);
			territory2 = getTerritoryByName(border[1]);
			// Gegenseitig als Nachbarn hinzufügen
			territory1.addNeighbor(territory2);
			territory2.addNeighbor(territory1);
		}
	}

	/**
	 * Sucht nach einem Land
	 * 
	 * @param name
	 *            Landname als String
	 * @return Landobjekt
	 */
	public Territory getTerritoryByName(String name) {
		return territories.get(name);
	}

	/**
	 * Liefert ein Land durch Angabe einer Zahl
	 * 
	 * @param number
	 * @return Land mit dieser Nummer
	 */
	public Territory getTerritoryByNumber(int number) {
		// TODO: diese Methode sollte es eigentlich nicht geben
		return getTerritoryByName(territoryNames[number]);
	}

	public boolean isAllTerritoryTaken() {

		for (int i = 0; i < 41; i++) {
			if (getTerritoryByName(territoryNames[i]).getOwner() == null) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Continent> getConqueredContinents(ArrayList<Territory> countries) {
		// Array für das Ergebnis
		ArrayList<Continent> conqueredContinents = new ArrayList<Continent>();

		// durchläuft jeden kotninent[] und überprüft den Besitzer
		Continent currentContinent;
		for (int i = 0; i < continente.length; i++) {
			currentContinent = continente[i];

			// Überprüft, ob die Länderliste den kompletten Kontinent enthält
			if (countries.containsAll(currentContinent.getTerritories())) {
				conqueredContinents.add(currentContinent);
			}
		}

		return conqueredContinents;

	}

	public int getNumberOfTerritories() {
		return territories.size();
	}

	public ArrayList<Territory> getRandomTerritoryList() {
		// erstellen einer zufälligen Länder ArrayList
	
		ArrayList<Territory> territoryListCopy = new ArrayList<Territory>(territories.values());
		ArrayList<Territory> territoryListRandom = new ArrayList<Territory>();
		
		while(territoryListCopy.size() != 0) {
			int rnd = (int) (Math.random() * territoryListCopy.size());
			territoryListRandom.add(territoryListCopy.get(rnd));
			territoryListCopy.remove(rnd);
		}
		return territoryListRandom;
	}
}
