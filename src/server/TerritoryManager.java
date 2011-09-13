package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import server.exceptions.InvalidTerritoryStateException;
import valueobjects.Continent;
import valueobjects.Player;
import valueobjects.Territory;
import valueobjects.continents.Africa;
import valueobjects.continents.Asia;
import valueobjects.continents.Australia;
import valueobjects.continents.Europe;
import valueobjects.continents.NorthAmerica;
import valueobjects.continents.SouthAmerica;

/**
 * This class does contains all territories and performs operations on them
 * 
 * @author Jannes, Timur, Hendrik
 * 
 */
public class TerritoryManager implements Iterable<Territory>, Serializable {

	private static final long serialVersionUID = -2158296973755060220L;

	/**
	 * List of all continents
	 */
	private Map<String, Continent> continents = new HashMap<String, Continent>();

	/**
	 * List of all territories coupled with their names
	 */
	private Map<String, Territory> territories = new HashMap<String, Territory>();

	/**
	 * A list which contains all pairs of adjacent territories
	 */
	private final String[][] borders = { { "Alaska", "Nordwest-Territorium" },
			{ "Alaska", "Alberta" }, { "Alaska", "Kamtschatka" },
			{ "Nordwest-Territorium", "Alberta" },
			{ "Nordwest-Territorium", "Grönland" },
			{ "Nordwest-Territorium", "Ontario" }, { "Alberta", "Ontario" },
			{ "Alberta", "Weststaaten" }, { "Ontario", "Oststaaten" },
			{ "Ontario", "Quebec" },
			{ "Ontario", "Grönland" }, { "Grönland", "Quebec" },
			{ "Grönland", "Island" },
			{ "Weststaaten", "Oststaaten" },
			{ "Weststaaten", "Mittelamerika" }, { "Weststaaten", "Ontario" },
			{ "Oststaaten", "Quebec" }, { "Oststaaten", "Mittelamerika" },
			{ "Mittelamerika", "Venezuela" }, { "Venezuela", "Peru" },
			{ "Venezuela", "Brasilien" }, { "Peru", "Brasilien" },
			{ "Peru", "Argentinien" },
			{ "Brasilien", "Nordwestafrika" }, { "Brasilien", "Argentinien" },
			{ "Nordwestafrika", "Westeuropa" },
			{ "Nordwestafrika", "Ägypten" }, { "Nordwestafrika", "Ostafrika" },
			{ "Nordwestafrika", "Kongo" }, { "Ägypten", "Südeuropa" },
			{ "Ägypten", "Ostafrika" },
			{ "Ostafrika", "Mittlerer Osten" }, { "Ostafrika", "Kongo" },
			{ "Ostafrika", "Südafrika" }, { "Ostafrika", "Madagaskar" },
			{ "Südafrika", "Kongo" },
			{ "Südafrika", "Madagaskar" }, { "Westeuropa", "Mitteleuropa" },
			{ "Westeuropa", "Südeuropa" }, { "Westeuropa", "Großbritannien" },
			{ "Großbritannien", "Skandinavien" },
			{ "Großbritannien", "Island" },
			{ "Großbritannien", "Mitteleuropa" },
			{ "Mitteleuropa", "Ukraine" },
			{ "Mitteleuropa", "Skandinavien" }, { "Island", "Skandinavien" },
			{ "Skandinavien", "Ukraine" }, { "Südeuropa", "Mittlerer Osten" },
			{ "Ukraine", "Südeuropa" },
			{ "Mittlerer Osten", "Indien" },
			{ "Mittlerer Osten", "Afghanistan" },
			{ "Mittlerer Osten", "Ukraine" }, { "Ukraine", "Afghanistan" },
			{ "Ukraine", "Ural" },
			{ "Ural", "Sibirien" }, { "Ural", "Afghanistan" },
			{ "Ural", "China" },
			{ "Afghanistan", "China" }, { "Afghanistan", "Indien" },
			{ "Indien", "China" }, { "Südeuropa", "Mitteleuropa" },
			{ "Mongolei", "Irkutsk" },
			{ "Indien", "Siam" }, { "China", "Mongolei" }, { "China", "Siam" },
			{ "China", "Sibirien" }, { "Sibirien", "Mongolei" },
			{ "Sibirien", "Irkutsk" },
			{ "Sibirien", "Jakutien" }, { "Jakutien", "Irkutsk" },
			{ "Jakutien", "Kamtschatka" }, { "Irkutsk", "Kamtschatka" },
			{ "Japan", "Kamtschatka" }, { "Japan", "Mongolei" },
			{ "Mongolei", "Kamtschatka" },
			{ "Siam", "Indonesien" }, { "Indonesien", "Neu-Guinea" },
			{ "Indonesien", "West-Australien" },
			{ "West-Australien", "Ost-Australien" },
			{ "West-Australien", "Neu-Guinea" },
			{ "Ost-Australien", "Neu-Guinea" } };

	/**
	 * Constructor
	 */
	public TerritoryManager() {
		// This list is only needed temporarily until we have the Map properly initialized
		List<Continent> continentList = new ArrayList<Continent>();
		// Create continents (and territories)
		continentList.add(new Africa());
		continentList.add(new Asia());
		continentList.add(new Australia());
		continentList.add(new Europe());
		continentList.add(new NorthAmerica());
		continentList.add(new SouthAmerica());

		// Get all territories and put them in a Map<String, Territory>
		// Get all continents and put them in a Map<String, Continent>
		for (Continent continent : continentList) {
			for (Territory territory : continent.getTerritories()) {
				territories.put(territory.getName(), territory);
			}
			continents.put(continent.getName(), continent);
		}

		// Initializes all neighboring territories based on the pairs in the
		// 'borders' array
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

		// TODO delete this when debugging is over
		/*
		 * //Show all freakin neighbors
		 * Iterator<String> itr = territories.keySet().iterator();
		 * String territoryname;
		 * 
		 * while (itr.hasNext()) {
		 * territoryname = itr.next();
		 * 
		 * Territory territories2 = territories.get(territoryname);
		 * 
		 * CopyOnWriteArrayList<Territory> territoriesNeigbhors =
		 * territories2.getNeighbors();
		 * System.out.println(" ");
		 * System.out.println("Territory: " + territories2.getName());
		 * System.out.println("Nachbarn: ");
		 * for (Territory territoryneigbor : territoriesNeigbhors){
		 * System.out.println(territoryneigbor.getName());
		 * }
		 * }
		 */
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
	 * Changes the owner of a territory. The target territory must be empty
	 * (i.e. its unit count must
	 * be zero).
	 * 
	 * @param newOwner
	 *            The new owner of the territory
	 * @param territory
	 *            The territory to be conquered
	 * @param units
	 *            The initial amount of units which will be placed on the
	 *            territory
	 * @throws InvalidTerritoryStateException
	 */
	public void changeTerritoryOwner(Player newOwner, Territory territory,
			int units)
			throws InvalidTerritoryStateException {

		// If the territory still holds units the owner cannot be changed
		if (territory.getUnitCount() != 0) {
			throw new InvalidTerritoryStateException(territory);
		}

		// Reflect the change in the player's lists of their territories
		if (territory.getOwner() != null) {
			territory.getOwner().removeTerritory(territory);
		}

		// Set the new owner
		territory.setOwner(newOwner);
		// Set the amount of units that the new owner has on this territory
		territory.addUnits(units);
		// add the conquered Territory to the territory list
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
	 * Creates a new list which contains all territories sorted in a random way
	 * 
	 * @return Random territory list
	 */
	public List<Territory> getRandomTerritoryList() {
		// Create a copy of the values
		List<Territory> territoryList = new ArrayList<Territory>(
				territories.values());

		// Shuffle it
		Collections.shuffle(territoryList);

		return territoryList;
	}

	/**
	 * Gets all the continents that exist. The values should not be modified in any way.
	 * 
	 * @return continents
	 */
	public Collection<Continent> getContinents() {
		return continents.values();
	}

	/**
	 * Gets a specific continent by its name
	 * 
	 * @param name
	 *            The continent's name
	 * @return continent
	 */
	public Continent getContinent(String name) {
		if (!continents.containsKey(name)) {
			throw new IllegalArgumentException("The continent " + name + " doesn't exist.");
		}
		return continents.get(name);
	}

	/**
	 * Returns a list with all continents that are completely conquered
	 * 
	 * @param territory
	 *            List of territories against which this check is performed
	 * @return
	 */
	// TODO Still needed after implementing the MissionManager?
	public List<Continent> getConqueredContinents(List<Territory> territory) {
		// Array für das Ergebnis
		List<Continent> conqueredContinents = new ArrayList<Continent>();

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

	public Map<String, Territory> getTerritoryMap() {
		return territories;
	}

	public Collection<Territory> getTerritoryList() {
		return territories.values();
	}

}
