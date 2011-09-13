package server.missions;

import valueobjects.Player;

public class EliminatePlayerMission extends Mission {

	private Player playerToEliminate;
	private Mission alternateMission;

	/**
	 * Creates a mission where an opposing player has to be eliminated.
	 * 
	 * Example: Befreien Sie alle Länder von den roten Armeen! Wenn Sie selbst
	 * diese Armeen besitzen,
	 * heißt Ihr Ziel: Befreien Sie 24 Länder Ihrer Wahl!
	 * 
	 * @param description
	 *            Description
	 * @param playerToEliminate
	 *            Player that needs to be eliminated in order to fulfil this
	 *            mission
	 */
	public EliminatePlayerMission(String description, Player playerToEliminate) {
		super(description);
		this.playerToEliminate = playerToEliminate;

		// TODO owner is missing
		// TODO new description?
		// If the player has to destroy himself, the new mission is to conquer
		// 24 territories
		if (owner.equals(playerToEliminate)) {
			alternateMission = new TerritoryHurdleMission("Description?", 24, 1);
		}
	}

	/**
	 * Returns whether this mission has been accomplished.
	 * 
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		// If an alternate mission exists, use it
		if (alternateMission != null) {
			return alternateMission.test();
		}

		// If the the opposing player is dead the player has succeeded
		return playerToEliminate.isDead();
	}

}
