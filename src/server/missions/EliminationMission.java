package server.missions;

import server.TerritoryManager;
import server.exceptions.MissionHasNoOwnerException;
import valueobjects.Player;

public class EliminationMission extends Mission {

	private Player enemy;
	private Mission alternateMission;

	/**
	 * Creates a mission in which an opposing player has to be eliminated.<br>
	 * <br>
	 * Covers these missions:<br>
	 * Befreien Sie alle Länder von den X Armeen! Wenn Sie selbst diese Armeen
	 * besitzen, heißt Ihr Ziel: Befreien Sie 24 Länder Ihrer Wahl!
	 * 
	 * @param territoryManager
	 *            Reference to the territoryManager
	 * @param enemy
	 *            Player that needs to be eliminated in order to fulfil this
	 *            mission
	 */
	public EliminationMission(TerritoryManager territoryManager,
			Player enemy) {
		super(territoryManager);
		this.enemy = enemy;
	}

	/**
	 * Sets the owner of this mission. Also ensures that the owner doesn't have
	 * to destroy himself.
	 * 
	 * @param owner
	 */
	@Override
	public void setOwner(Player owner) {
		// Set the owner as usual
		super.setOwner(owner);

		// Create an alternate mission if necessary
		alternateMission = owner.equals(enemy) ? new TerritoryHurdleMission(t, 24) : null;
	}

	/**
	 * Gets the description of this mission.
	 * 
	 * @return description
	 */
	@Override
	public String getDescription() {
		if (alternateMission == null) {
			return "Befreien Sie alle Länder von den Armeen des Spielers \"" + enemy + "\"!";
		} else {
			return "Befreien Sie alle Länder ihren eigenen Armeen! Wenn sie das nicht wollen, heißt ihr Ziel: " + alternateMission.getDescription();
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
			throw new MissionHasNoOwnerException("The mission doesn't have an owner yet.");
		}

		// Test for success
		if (alternateMission == null) {
			return enemy.isDead();
		} else {
			return alternateMission.test();
		}
	}

}
