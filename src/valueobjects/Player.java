package valueobjects;

import java.util.ArrayList;
import java.util.HashSet;

import domain.TerritoryManager;

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

	/**
	 * The player's color
	 */
	private int color;

	/**
	 * The territories that the player has conquered
	 */
	private ArrayList<Territory> territories = new ArrayList<Territory>();

	/**
	 * A list of territory cards that the player currently has. These can be exchanged for bonus units
	 * at the start of every round.
	 */
	private HashSet<TerritoryCard> territoryCards = new HashSet<TerritoryCard>();

	/**
	 * The number of units the player still has to place on the board. This happens at the start of
	 * every round. The player can only start attacking someone else if all units are placed.
	 */
	private int supplyToAllocate;

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public Player(String name) {
		// TODO the player's color or number
		this.name = name;
	}

	/**
	 * Returns the number of countries the player currently owns.
	 * @return
	 */
	public int getTerritoryNumber() {
		return territories.size();
	}

	/**
	 * Adds a territory to the list of territories which are owned by the player.
	 * 
	 * @param territory 
	 *          
	 */
	public void addTerritory(Territory territory) {
		territories.add(territory);
	}
	
	/**
	 * Removes a territory from the list of territories which are owned by the player.
	 * 
	 * @param territory 
	 *          
	 */
	public void removeTerritory(Territory territory) {
		territories.remove(territory);
	}

	/**
	 * Liefert die Länder
	 * 
	 * @return Länder, die der Spieler besitzt
	 */
	public ArrayList<Territory> getTerritories() {
		return territories;
	}

	public String getName() {
		return name;
	}

	public int getSupply() {
		return supplyToAllocate;
	}

	public void setSupply(int supply) {
		this.supplyToAllocate = supply;

	}

	public void addSupply(int change) {
		this.supplyToAllocate += change;
	}

	public void removeSupply(int change) {
		this.supplyToAllocate -= change;
	}

	/**
	 * Returns a random territory which is owned by the player
	 * 
	 * @param player
	 *            Player
	 * @return Random rerritory
	 */
	public Territory getRandomTerritory() {
		// Retrieve a random territory
		int random = (int) (Math.random() * territories.size());
		Territory randomTerritory = territories.get(random);

		return randomTerritory;
	}
}