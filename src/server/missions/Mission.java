package server.missions;

import server.TerritoryManager;
import valueobjects.Player;

public abstract class Mission {

	protected TerritoryManager t;
	
	protected Player owner;

	/**
	 * Constructor
	 * @param territoryManager Reference to the territoryManager
	 */
	public Mission(TerritoryManager territoryManager) {
		this.t = territoryManager;
	}
	
	/**
	 * Sets the owner.
	 * 
	 * @param owner
	 *            The player who owns this mission.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Gets the player whose mission this is.
	 * 
	 * @return Player
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Gets the description of this mission.
	 * 
	 * @return description
	 */
	public abstract String getDescription();

	/**
	 * Returns whether this mission has been accomplished.
	 * 
	 * @return True, if the mission was accomplished
	 */
	public abstract boolean test();

}
