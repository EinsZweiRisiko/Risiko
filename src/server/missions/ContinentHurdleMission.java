package server.missions;

import java.util.ArrayList;
import java.util.List;

import server.TerritoryManager;
import valueobjects.Continent;

public class ContinentHurdleMission extends Mission {

	private TerritoryManager territoryManager;
	
	/**
	 * List of specific continents which have to be conquered.
	 */
	private List<Continent> continentList = new ArrayList<Continent>();

	/**
	 * Number of other, arbitrary continents which also have to be conquered.
	 */
	private int additionalContinents = 0;
	
	/**
	 * Creates a mission where a set number of continents has to be conquered.
	 * 
	 * Example: Befreien Sie Nordamerika und Afrika!
	 * 
	 * @param description
	 *            Description
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param continentList
	 *            Continents which need to be conquered.
	 */
	public ContinentHurdleMission(String description, TerritoryManager territoryManager,
			List<Continent> continentList) {
		super(description);
		this.territoryManager = territoryManager;
		this.continentList = continentList;
		// TODO continentList length over 2 should be impossible
	}

	/**
	 * Creates a mission where a set number of continents has to be conquered.
	 * 
	 * Example: Befreien Sie Europa, Australien und einen dritten Kontinent Ihrer Wahl!
	 * 
	 * @param description
	 *            Description
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param continentList
	 *            Continents which need to be conquered.
	 * @param additionalContinents Number of other, arbitrary continents which also have to be conquered.
	 */
	public ContinentHurdleMission(String description, TerritoryManager territoryManager,
			List<Continent> continentList, int additionalContinents) {
		super(description);
		this.territoryManager = territoryManager;
		this.continentList = continentList;
		this.additionalContinents = additionalContinents;
		// TODO continentList length over 2 should be impossible
	}
	
	/**
	 * Returns whether this mission has been accomplished.
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		List<Continent> conqueredContinents = territoryManager.getConqueredContinents(owner.getTerritories());
		
		// Check if all required continents are conquered
		if (additionalContinents == 0) {
			// Only the continentList needs to be conquered 
			return conqueredContinents.containsAll(continentList);
		} else {
			// The continentList and additional continents need to be conquered
			if (conqueredContinents.containsAll(continentList)) {
				// Now check for additional continents
				conqueredContinents.removeAll(continentList);
				if (conqueredContinents.size() >= additionalContinents) {
					return true;
				}
			}
			return false;
		}
	}

}
