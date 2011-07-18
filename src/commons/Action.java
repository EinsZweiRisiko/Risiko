package commons;

import java.io.Serializable;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;

public abstract class Action implements Serializable {
	private static final long serialVersionUID = -300479080058405980L;
	
	/**
	 * Stores the player that initiated this action. Can be null.
	 */
	private Player player;
	private Phase phase;
	
	/**
	 * Sometimes there is no player who initiated an action.
	 */
	public Action() {
		
	}
	
	/**
	 * Save the player that initiated this action.
	 * @param player
	 */
	public Action(Player player) {
		this.player = player;
	}
	
	public Action(Player player, Phase phase) {
		this.player = player;
		this.phase = phase;
	}
	
	/**
	 * Returns the player that initiated this action. Can be null.
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}
}