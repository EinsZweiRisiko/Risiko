package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import valueobjects.Continent;
import valueobjects.Player;
import valueobjects.Territory;
import valueobjects.continents.Africa;
import valueobjects.continents.Asia;
import valueobjects.continents.Australia;
import valueobjects.continents.Europe;
import valueobjects.continents.NorthAmerica;
import valueobjects.continents.SouthAmerica;
import domain.exceptions.InvalidTerritoryStateException;

/**
 * This class does contains all territories and performs operations on them
 * 
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
		// Create continents (and territories)
		continents.add(new Africa());
		continents.add(new Asia());
		continents.add(new Australia());
		continents.add(new Europe());
		continents.add(new NorthAmerica());
		continents.add(new SouthAmerica());

		// Get all territories and put them in a Map<String, Territory>
		for (Continent continent : continents) {
			for (Territory territory : continent.getTerritories()) {
				territories.put(territory.getName(), territory);
			}
		}

		// Initializes all neighboring territories based on the pairs in the 'borders' array
		initNeighboringTerritories();
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

//	/**
//	 * Returns the number of territories that exist
//	 * 
//	 * @return Number of territories
//	 */
//	public int getNumberOfTerritories() {
//		return territories.size();
//	}

	/**
	 * Changes the owner of a territory. The target territory must be empty (i.e. its unit count must
	 * be zero).
	 * 
	 * @param newOwner
	 *            The new owner of the territory
	 * @param territory
	 *            The territory to be conquered
	 * @param units
	 *            The initial amount of units which will be placed on the territory
	 * @throws InvalidTerritoryStateException
	 */
	public void changeTerritoryOwner(Player newOwner, Territory territory, int units)
			throws InvalidTerritoryStateException {
		// If the territory still holds units the owner cannot be changed
		if (territory.getUnits() != 0) {
			throw new InvalidTerritoryStateException(territory);
		}

		// Set the new owner
		Player oldOwner = territory.getOwner();
		territory.setOwner(newOwner);

		// Set the amount of units that the new owner has on this territory
		territory.setUnits(units);

		// Reflect the change in the player's lists of their territories
		if (oldOwner != null) {
			oldOwner.removeTerritory(territory);
		}
		newOwner.addTerritory(territory);
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

	/**
	 * Returns a list with all continents that are completely conquered
	 * 
	 * @param territory List of territories against which this check is performed
	 * @return
	 */
	public ArrayList<Continent> getConqueredContinents(ArrayList<Territory> territory) {
		// Array für das Ergebnis
		ArrayList<Continent> conqueredContinents = new ArrayList<Continent>();

		// durchläuft jeden kotninent[] und überprüft den Besitzer
		Continent currentContinent;
		for (int i = 0; i < continents.size(); i++) {
			currentContinent = continents.get(i);

			// Überprüft, ob die Länderliste den kompletten Kontinent enthält
			if (territory.containsAll(currentContinent.getTerritories())) {
				conqueredContinents.add(currentContinent);
			}
		}

		return conqueredContinents;

	}

	/**
	 * Creates a new list which contains all territories sorted in a random way
	 * 
	 * @return Random territory list
	 */
	public ArrayList<Territory> getRandomTerritoryList() {
		// Create a copy of the values
		ArrayList<Territory> territoryList = new ArrayList<Territory>(territories.values());

		// Shuffle it
		Collections.shuffle(territoryList);

		return territoryList;
	}
	
	public ArrayList<Continent> getContinents() {
		return continents;
	}
	
	
	public ArrayList<Territory> getTerritoryList() {
		ArrayList<Territory> territoryList = new ArrayList<Territory>(territories.values());
		return territoryList;
	}
}
