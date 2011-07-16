package valueobjects;

import java.io.Serializable;

public class BasePlayer implements Serializable {
	private static final long serialVersionUID = -6379595885605245743L;

	/**
	 * The player's name
	 */
	private String name;

	/**
	 * The player's color
	 */
	private int color;
	
	/**
	 * The number of units the player still has to place on the board. This
	 * happens at the start of
	 * every round. The player can only start attacking someone else if all
	 * units are placed.
	 */
	protected int suppliesToAllocate = 0;
	
	public BasePlayer(String name, int color) {
		// TODO Add the player's color or assign at least a player number
		this.name = name;
		this.color = color;
	}
	
	/**
	 * Gets the player's name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	public void setColor(int color) {
		// TODO Auto-generated method stub
		this.color = color;
	}
	
	/**
	 * Adds the specified number to the amount of units that need to be
	 * allocated.
	 * 
	 * @param change
	 *            Value to be added
	 */
	public void addSupplies(int change) {
		this.suppliesToAllocate += change;
	}

	/**
	 * Substracts the specified number from the amount of units that need to be
	 * allocated.
	 * 
	 * @param change
	 *            Value to be substract
	 */
	public void subtractSupplies(int change) {
		this.suppliesToAllocate -= change;
	}

	/**
	 * Returns the total amount of supply units that the player needs to
	 * allocate.
	 * 
	 * @return
	 */
	public int getSupplies() {
		return suppliesToAllocate;
	}
}
