package valueobjects;

import java.util.ArrayList;

/**
 * A class that represents a player
 * 
 * @author Jannes, Hendrik
 */
public class Player {

	/**
	 * The player's name
	 */
	private String name;

//	/**
//	 * The player's color
//	 */
//	private int color;

	/**
	 * The number of units the player still has to place on the board. This happens at the start of
	 * every round. The player can only start attacking someone else if all units are placed.
	 */
	private int supplyToAllocate;

	/**
	 * The territories that the player has conquered
	 */
	private ArrayList<Territory> territoryList = new ArrayList<Territory>();

	/**
	 * A list of territory cards that the player currently has. These can be exchanged for bonus units
	 * at the start of every round.
	 */
	private ArrayList<TerritoryCard> territoryCards = new ArrayList<TerritoryCard>();

	/**
	 * Constructor
	 * 
	 * @param name of the player.
	 */
	public Player(String name) {
		// TODO Add the player's color or assign at least a player number
		this.name = name;
	}

	/**
	 * Gets the player's name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the player's name
	 * 
	 * @return name
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Adds a territory to the list of territories which are owned by the player.
	 * 
	 * @param territory
	 * 
	 */
	public void addTerritory(Territory territory) {
		territoryList.add(territory);
	}

	/**
	 * Removes a territory from the list of territories which are owned by the player.
	 * 
	 * @param territory
	 * 
	 */
	public void removeTerritory(Territory territory) {
		territoryList.remove(territory);
	}

	/**
	 * Returns a list of territories the player owns.
	 * 
	 * The list should not be altered in any way.
	 * 
	 * @return List of territories
	 */
	public ArrayList<Territory> getTerritories() {
		// TODO protect the internal list from changes
		return territoryList;
	}

	/**
	 * Returns the number of countries the player currently owns.
	 * 
	 * @return the number of countries the player currently owns.
	 */
	public int getTerritoryCount() {
		return territoryList.size();
	}
	
	/**
	 * Returns if the player is dead
	 * @return True, if the player has no territories left
	 */
	public boolean isDead() {
		return territoryList.isEmpty();
	}
	
	/**
	 * Returns a random territory which is owned by the player
	 * 
	 * @return a random Territory
	 */
	public Territory getRandomTerritory() {
		// Generate a pseudo random number
		int random = (int) (Math.random() * territoryList.size());
		// Return the territory
		return territoryList.get(random);
	}
	
	/**
	 * Adds the specified number to the amount of units that need to be allocated.
	 * @param change Value to be added
	 */
	public void addSupply(int change) {
		this.supplyToAllocate += change;
	}
	
	/**
	 * Substracts the specified number from the amount of units that need to be allocated.
	 * @param change Value to be substract
	 */
	public void subtractSupply(int change) {
		this.supplyToAllocate -= change;
	}
	
	/**
	 * Returns the total amount of supply units that the player needs to allocate.
	 * @return
	 */
	public int getSupply() {
		return supplyToAllocate;
	}
	
}