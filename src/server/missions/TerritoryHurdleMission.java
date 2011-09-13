package server.missions;

import java.util.List;

import valueobjects.Territory;

public class TerritoryHurdleMission extends Mission {

	private int territoryHurdle;
	private int territoryUnitHurdle;

	public TerritoryHurdleMission(String description, int territoryHurdle,
			int territoryUnitHurdle) {
		super(description);
		this.territoryHurdle = territoryHurdle;
		this.territoryUnitHurdle = territoryUnitHurdle;
	}

	public TerritoryHurdleMission(String description, int territoryHurdle) {
		super(description);
		this.territoryHurdle = territoryHurdle;
	}

	/**
	 * Returns whether this mission has been accomplished.
	 * 
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		// Get all territories that are owned by the player
		List<Territory> playerTerritories = owner.getTerritories();

		// Check the territory hurdle
		if (playerTerritories.size() < territoryHurdle) {
			return false;
		}

		// Check the unit hurdle for each territory
		for (Territory territory : playerTerritories) {
			if (territory.getUnitCount() < territoryUnitHurdle) {
				return false;
			}
		}

		// All conditions are fulfilled
		return true;
	}

}
