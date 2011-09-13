package server.missions;

import server.TerritoryManager;
import server.exceptions.MissionHasNoOwnerException;

public class WorldDominationMission extends Mission {

	/**
	 * Creates a mission where the player has to conquer the whole world to win.
	 * 
	 * @param territoryManager
	 *            Reference to the territoryManager
	 */
	public WorldDominationMission(TerritoryManager territoryManager) {
		super(territoryManager);
	}

	/**
	 * Gets the description of this mission.
	 * 
	 * @return description
	 */
	@Override
	public String getDescription() {
		return "Erringen Sie die Weltherrschaft!";
	}

	/**
	 * Returns whether this mission has been accomplished.
	 * 
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		if (owner == null) {
			throw new MissionHasNoOwnerException(
					"The mission doesn't have an owner yet.");
		}

		return owner.getTerritories().containsAll(t.getTerritoryList());
	}

}
