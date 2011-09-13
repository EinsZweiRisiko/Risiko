package server.missions;

import java.util.List;

import server.TerritoryManager;
import server.exceptions.MissionHasNoOwnerException;
import valueobjects.Territory;

public class TerritoryHurdleMission extends Mission {

	private int territoryHurdle;

	private int territoryUnitHurdle = 1;

	/**
	 * Creates a mission where a specific number of territories has to be
	 * conquered in order to win.<br>
	 * <br>
	 * Covers missions like these:<br>
	 * Befreien Sie 24 Länder Ihrer Wahl!
	 * 
	 * @param territoryManager
	 *            Reference to the TerritoryManager
	 * @param territoryHurdle
	 *            Amount of territories that have to be conquered
	 */
	public TerritoryHurdleMission(TerritoryManager territoryManager,
			int territoryHurdle) {
		super(territoryManager);
		if (territoryHurdle < 1) {
			throw new IllegalArgumentException(
					"territoryHurdle must be greater than 0.");
		}
		this.territoryHurdle = territoryHurdle;
	}

	/**
	 * Creates a mission where a specific number of territories has to be
	 * conquered in order to win.<br>
	 * Also, a minimum amount of units on each territory has to be satisfied.<br>
	 * <br>
	 * Covers missions like these:<br>
	 * Befreien Sie 18 Länder und setzen Sie in jedes Land mindestens 2 Armeen!
	 * 
	 * @param territoryManager
	 *            Reference to the TerritoryManager
	 * @param territoryHurdle
	 *            Amount of territories that have to be conquered
	 * @param territoryUnitHurdle
	 *            Minimum amount of units that has to be placed on each
	 *            territory
	 */
	public TerritoryHurdleMission(TerritoryManager territoryManager,
			int territoryHurdle,
			int territoryUnitHurdle) {
		super(territoryManager);
		if (territoryHurdle < 1) {
			throw new IllegalArgumentException(
					"territoryHurdle must be greater than 0.");
		}
		if (territoryUnitHurdle < 1) {
			throw new IllegalArgumentException(
					"territoryUnitHurdle must be greater than 0.");
		}
		this.territoryHurdle = territoryHurdle;
		this.territoryUnitHurdle = territoryUnitHurdle;
	}

	/**
	 * Gets the description of this mission.
	 * 
	 * @return description
	 */
	@Override
	public String getDescription() {
		switch (territoryUnitHurdle) {
			case 1:
				return "Befreien Sie " + territoryHurdle
						+ " Länder Ihrer Wahl!";
			case 2:
				return "Befreien Sie 18 Länder und setzen Sie in jedes Land mindestens 2 Armeen!";
			default:
				return "Not implemented.";
		}
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

		// Get all territories that are owned by the player
		List<Territory> territories = owner.getTerritories();

		// Check the territory hurdle
		if (territories.size() < territoryHurdle) {
			return false;
		}

		// Check the unit hurdle for each territory
		for (Territory territory : territories) {
			if (territory.getUnitCount() < territoryUnitHurdle) {
				return false;
			}
		}

		// All conditions are fulfilled
		return true;
	}

}
