package valueobjects.missions;

import java.util.ArrayList;

import valueobjects.Continent;
import valueobjects.Mission;
import valueobjects.Player;
import domain.TerritoryManager;

public class ContinentHurdleMission implements Mission {

	/**
	 * TerritoryManager. Not every subclass initializes this attribute.
	 */
	protected TerritoryManager territoryManager;

	/**
	 * The player who owns this mission
	 */
	protected Player player;
	
	/**
	 * List of continents which have to be conquered
	 */
	private ArrayList<Continent> continentList = new ArrayList<Continent>();

	/**
	 * Number of other, arbitrary continents which also have to be conquered.
	 */
	private int additionalContinents = 0;
	
	/**
	 * Creates a mission where a set number of continents has to be conquered.
	 * 
	 * Example: Befreien Sie Nordamerika und Afrika!
	 * 
	 * @param player
	 *            The player whose mission this is
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param continentList
	 *            Continents which need to be conquered.
	 */
	public ContinentHurdleMission(Player player, TerritoryManager territoryManager,
			ArrayList<Continent> continentList) {
		this.player = player;
		this.territoryManager = territoryManager;
//		if (continentList.size() > 2) {
//			throw new Exception(); 
//		}
		this.continentList = continentList;
	}

	/**
	 * Creates a mission where a set number of continents has to be conquered.
	 * 
	 * Example: Befreien Sie Europa, Australien und einen dritten Kontinent Ihrer Wahl!
	 * 
	 * @param player
	 *            The player whose mission this is
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param continentList
	 *            Continents which need to be conquered.
	 * @param additionalContinents Number of other, arbitrary continents which also have to be conquered.
	 */
	public ContinentHurdleMission(Player player, TerritoryManager territoryManager,
			ArrayList<Continent> continentList, int additionalContinents) {
		this.player = player;
		this.territoryManager = territoryManager;
//		if (continentList.size() > 2) {
//			throw new Exception(); 
//		}
		this.continentList = continentList;
		// Additional continents
		this.additionalContinents = additionalContinents;
	}
	
	/**
	 * Returns whether this mission has been accomplished or not
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		ArrayList<Continent> conqueredContinents = territoryManager.getConqueredContinents(player
				.getTerritories());
		
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
