package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import valueobjects.Continent;
import valueobjects.Player;
import valueobjects.Territory;
import domain.exceptions.InvalidStateException;

/**
 * This class does contains all territories and performs operations on them
 * @author Jannes
 * 
 */
public class TerritoryManager implements Iterable<Territory> {

	/**
	 * List of all continents
	 */
	private ArrayList<Continent> continents = new ArrayList<Continent>();

	/**
	 * List of all territories coupled with their names
	 */
	private HashMap<String, Territory> territories = new HashMap<String, Territory>();
	
	/**
	 * All territory names
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
	 * A list which contains all pairs of adjacent territories
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
	 * Constructor
	 */
	public TerritoryManager() {
		// Create territory objects for every name in the list
		for (String name : territoryNames) {
			territories.put(name, new Territory(name));
		}

		// Initializes all neighboring territories based on the pairs in the 'borders' array
		initNeighboringTerritories();

		// Create all continents
		Continent continent;
		// North America
		continent = new Continent("Nordamerika", 5);
		continent.addTerritory(territories.get("Alaska"));
		continent.addTerritory(territories.get("Nordwest-Territorium"));
		continent.addTerritory(territories.get("Grönland"));
		continent.addTerritory(territories.get("Alberta"));
		continent.addTerritory(territories.get("Ontario"));
		continent.addTerritory(territories.get("Quebec"));
		continent.addTerritory(territories.get("Weststaaten"));
		continent.addTerritory(territories.get("Oststaaten"));
		continent.addTerritory(territories.get("Mittelamerika"));
		continents.add(continent);

		// South America
		continent = new Continent("Südamerika", 2);
		continent.addTerritory(territories.get("Venezuela"));
		continent.addTerritory(territories.get("Peru"));
		continent.addTerritory(territories.get("Brasilien"));
		continent.addTerritory(territories.get("Argentinien"));
		continents.add(continent);
		
		// Africa
		continent = new Continent("Afrika", 3);
		continent.addTerritory(territories.get("Nordwestafrika"));
		continent.addTerritory(territories.get("Ägypten"));
		continent.addTerritory(territories.get("Ostafrika"));
		continent.addTerritory(territories.get("Kongo"));
		continent.addTerritory(territories.get("Südafrika"));
		continent.addTerritory(territories.get("Madagaskar"));
		continents.add(continent);

		// Europe
		continent = new Continent("Europa", 5);
		continent.addTerritory(territories.get("Island"));
		continent.addTerritory(territories.get("Skandinavien"));
		continent.addTerritory(territories.get("Ukraine"));
		continent.addTerritory(territories.get("Großbritannien"));
		continent.addTerritory(territories.get("Mitteleuropa"));
		continent.addTerritory(territories.get("Westeuropa"));
		continent.addTerritory(territories.get("Südeuropa"));
		continents.add(continent);

		// Asia
		continent = new Continent("Asien", 7);
		continent.addTerritory(territories.get("Ural"));
		continent.addTerritory(territories.get("Sibirien"));
		continent.addTerritory(territories.get("Jakutien"));
		continent.addTerritory(territories.get("Irkutsk"));
		continent.addTerritory(territories.get("Kamtschatka"));
		continent.addTerritory(territories.get("Mongolei"));
		continent.addTerritory(territories.get("Japan"));
		continent.addTerritory(territories.get("Afghanistan"));
		continent.addTerritory(territories.get("China"));
		continent.addTerritory(territories.get("Mittlerer Osten"));
		continent.addTerritory(territories.get("Indien"));
		continent.addTerritory(territories.get("Siam"));
		continents.add(continent);

		// Australia
		continent = new Continent("Australien", 2);
		continent.addTerritory(territories.get("Indonesien"));
		continent.addTerritory(territories.get("Neu-Guinea"));
		continent.addTerritory(territories.get("West-Australien"));
		continent.addTerritory(territories.get("Ost-Australien"));
		continents.add(continent);
	}

	/**
	 * Initializes the neighbor list of each territory
	 */
	private void initNeighboringTerritories() {
		Territory territory1;
		Territory territory2;
		
		// Go through all pairs
		for (String[] border : borders) {
			// Get both territory objects
			territory1 = territories.get(border[0]);
			territory2 = territories.get(border[1]);
			// Set them both as neighbors of each other
			territory1.addNeighbor(territory2);
			territory2.addNeighbor(territory1);
		}
	}

	/**
	 * Returns an iterator for all territories
	 */
	@Override
	public Iterator<Territory> iterator() {
		return territories.values().iterator();
	}
	
	/**
	 * Tests if all territories hold at least one unit
	 * 
	 * @return True if every territory holds at least one unit
	 */
	public boolean allOccupied() {
		// Go through all territories
		for (Territory territory : territories.values()) {
			if (territory.getOwner() == null) {
				// This territory doesn't have an owner
				return false;
			}
		}
		// Every territory has an owner
		return true;
	}

//	/**
//	 * Returns a list with all continents that are completely conquered
//	 * @param countries
//	 * @return
//	 */
//	public ArrayList<Continent> getConqueredContinents(ArrayList<Territory> countries) {
//		// Array für das Ergebnis
//		ArrayList<Continent> conqueredContinents = new ArrayList<Continent>();
//
//		// durchläuft jeden kotninent[] und überprüft den Besitzer
//		Continent currentContinent;
//		for (int i = 0; i < continents.size(); i++) {
//			currentContinent = continents.get(i);
//
//			// Überprüft, ob die Länderliste den kompletten Kontinent enthält
//			if (countries.containsAll(currentContinent.getTerritories())) {
//				conqueredContinents.add(currentContinent);
//			}
//		}
//
//		return conqueredContinents;
//
//	}

//	/**
//	 * Returns the number of territories that exist
//	 * 
//	 * @return Number of territories
//	 */
//	public int getNumberOfTerritories() {
//		return territories.size();
//	}
	
	/**
	 * Creates a new list which contains all territories sorted in a random way
	 * @return Random territory list
	 */
	public ArrayList<Territory> getRandomTerritoryList() {
		// Creates two lists
		ArrayList<Territory> territoryListCopy = new ArrayList<Territory>(territories.values());
		ArrayList<Territory> territoryListRandom = new ArrayList<Territory>();

		while (territoryListCopy.size() != 0) {
			int rnd = (int) (Math.random() * territoryListCopy.size());
			territoryListRandom.add(territoryListCopy.get(rnd));
			territoryListCopy.remove(rnd);
		}
		
		return territoryListRandom;
	}

	/**
	 * Changes the owner of a territory
	 * 
	 * @param newOwner
	 *            The new owner of the territory
	 * @param territory
	 *            The territory to be conquered
	 * @param units
	 *            The initial amount of units which will be placed on the territory
	 * @throws InvalidStateException
	 */
	public void changeTerritoryOwner(Player newOwner, Territory territory, int units)
			throws InvalidStateException {
		// If the territory still holds units the owner cannot be changed
		if (territory.getUnits() != 0) {
			throw new InvalidStateException("The territory " + territory.toString()
					+ " still holds units.");
		}

		// Set the new owner
		Player oldOwner = territory.getOwner();
		territory.setOwner(newOwner);
		
		// Set the amount of units that the new owner has on this territory
		territory.setUnits(units);
		
		// Reflect the change in the player's lists of their territories 
		oldOwner.removeTerritory(territory);
		newOwner.addTerritory(territory);
	}

}
