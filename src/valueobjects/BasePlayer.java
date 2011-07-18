package valueobjects;

import java.io.Serializable;

public class BasePlayer implements Serializable {
	
	private static final long serialVersionUID = -6379595885605245743L;

	/**
	 * This is a static attribute that is used to assign each player a unique ID
	 */
	private static int playerCounter = 0;
	
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
	
	public BasePlayer(String name) {
		this.name = name;
		this.color = playerCounter++;
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
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Player)) {
			return false;
		}
		
		Player p = (Player) o;
		
		if (p.getColor() == getColor()) {
			return true;
		} else {
			return false;
		}
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
