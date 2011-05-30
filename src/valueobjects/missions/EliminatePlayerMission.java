package valueobjects.missions;

import valueobjects.Player;

public class EliminatePlayerMission extends Mission {

//	private Player player;
	private Player playerToEliminate;
	private Mission alternateMission;

	/**
	 * Creates a mission where an opposing player has to be eliminated.
	 * 
	 * Example: Befreien Sie alle Länder von den roten Armeen! Wenn Sie selbst diese Armeen besitzen,
	 * heißt Ihr Ziel: Befreien Sie 24 Länder Ihrer Wahl!
	 * 
	 * @param player Player whose mission this is
	 * @param playerToEliminate Player that needs to be eliminated in order to fulfil this mission
	 */
	public EliminatePlayerMission(Player player, Player playerToEliminate) {
//		this.player = player;
		this.playerToEliminate = playerToEliminate;
		
		// If the player has to destroy himself, the new mission is to conquer 24 territories
		if (player.equals(playerToEliminate)) {
			alternateMission = new TerritoryHurdleMission(player, 24, 1);
		}
	}

	/**
	 * Returns whether this mission has been accomplished or not
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
