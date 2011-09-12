package server.missions;

import valueobjects.Player;

public abstract class Mission {

	private String description;
	protected Player owner;

	/**
	 * Creates a mission with the supplied description. The mission logic must
	 * be part of a subclass.
	 * 
	 * @param description
	 *            String
	 */
	public Mission(String description) {
		this.description = description;
	}

	/**
	 * Gets the description of the Mission as a String.
	 * 
	 * @return Description as String
	 */
	public String getDescription() {
		return description;
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
	 * Returns whether this mission has been accomplished.
	 * 
	 * @return True, if the mission was accomplished
	 */
	public abstract boolean test();

}
