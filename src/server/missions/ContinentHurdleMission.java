package server.missions;

import java.util.ArrayList;
import java.util.List;

import server.TerritoryManager;
import server.exceptions.MissionHasNoOwnerException;
import valueobjects.Continent;

public class ContinentHurdleMission extends Mission {

	/**
	 * List of specific continents which have to be conquered.
	 */
	private List<Continent> continents = new ArrayList<Continent>(2);

	/**
	 * Number of other, arbitrary continents which also have to be conquered.
	 */
	private int additionalContinents = 0;

	/**
	 * Creates a mission where a set number of continents has to be conquered.<br>
	 * <br>
	 * Covers these missions:<br>
	 * Befreien Sie Nordamerika und Afrika!<br>
	 * Befreien Sie Nordamerika und Australien!<br>
	 * Befreien Sie Asien und Südamerika!<br>
	 * Befreien Sie Afrika und Asien!
	 * 
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param continent1
	 *            First continent which needs to be conquered.
	 * @param continent2
	 *            Second continent which needs to be conquered.
	 */
	public ContinentHurdleMission(TerritoryManager territoryManager,
			Continent continent1, Continent continent2) {
		super(territoryManager);
		this.continents.add(continent1);
		this.continents.add(continent2);
	}

	/**
	 * Creates a mission where a number of specific continents and a number of
	 * arbitrary continents has to be conquered.<br>
	 * <br>
	 * Covers these missions:<br>
	 * Befreien Sie Europa, Südamerika und einen dritten Kontinent Ihrer Wahl!<br>
	 * Befreien Sie Europa, Australien und einen dritten Kontinent Ihrer Wahl!
	 * 
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param continent1
	 *            First continent which needs to be conquered.
	 * @param continent2
	 *            Second continent which needs to be conquered.
	 * @param additionalContinents
	 *            Number of other, arbitrary continents which also have to be
	 *            conquered.
	 */
	public ContinentHurdleMission(TerritoryManager territoryManager,
			Continent continent1, Continent continent2, int additionalContinents) {
		super(territoryManager);
		if (additionalContinents < 0) {
			throw new IllegalArgumentException(
					"additionalContinents must be positive.");
		}
		this.continents.add(continent1);
		this.continents.add(continent2);
		this.additionalContinents = additionalContinents;
	}

	/**
	 * Gets the description of this mission.
	 * 
	 * @return description
	 */
	@Override
	public String getDescription() {
		switch (additionalContinents) {
			case 0:
				return "Befreien Sie " + continents.get(0) + " und "
						+ continents.get(1)
						+ "!";
			case 1:
				return "Befreien Sie " + continents.get(0) + " und "
						+ continents.get(1)
						+ " und einen dritten Kontinent ihrer Wahl!";
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

		List<Continent> conqueredContinents = t
				.getConqueredContinents(owner.getTerritories());

		// Check if all required continents are conquered
		if (additionalContinents == 0) {
			// Only the continentList needs to be conquered
			return conqueredContinents.containsAll(continents);
		} else {
			// The continentList and additional continents need to be conquered
			if (conqueredContinents.containsAll(continents)) {
				// Now check for additional continents
				conqueredContinents.removeAll(continents);
				if (conqueredContinents.size() >= additionalContinents) {
					return true;
				}
			}
			return false;
		}
	}

}
