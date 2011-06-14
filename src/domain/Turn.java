package domain;

import valueobjects.Player;
import domain.Game.Action;

// TODO think about how this class could help
@SuppressWarnings("unused")
public class Turn {
	private Player player;
	private int territories;
	private Action currentAction;
	
	public Turn(Player player) {
		this.player = player;
		
		// Save the number of occupied territories
		territories = player.getTerritoryCount();
		
		// First action
		currentAction = Action.START;
	}
	
	/**
	 * Getter for the player
	 * @return Active player
	 */
	public Player getPlayer() {
		return player;
	}
}
