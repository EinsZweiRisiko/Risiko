package valueobjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
	 * The number of units the player still has to place on the board. This
	 * happens at the start of
	 * every round. The player can only start attacking someone else if all
	 * units are placed.
	 */
	private int suppliesToAllocate;

	/**
	 * The territories that the player has conquered
	 */
	private ArrayList<Territory> territoryList = new ArrayList<Territory>();

	/**
	 * A list of territory cards that the player currently has. These can be
	 * exchanged for bonus units
	 * at the start of every round.
	 */
	private HashSet<BonusCard> territoryCards = new HashSet<BonusCard>();

	/**
	 * Constructor
	 * 
	 * @param name
	 *            of the player.
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
	 * Adds a territory to the list of territories which are owned by the
	 * player.
	 * 
	 * @param territory
	 * 
	 */
	public void addTerritory(Territory territory) {
		territoryList.add(territory);
	}

	/**
	 * Removes a territory from the list of territories which are owned by the
	 * player.
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
	 * 
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

	/**
	 * Adds a card to the player's list of territory cards
	 * 
	 * @param card
	 *            The territory card to be added
	 */
	public void addBonusCard(BonusCard card) {
		territoryCards.add(card);
	}

	/**
	 * Returns the player's list of territory cards which can be exchanged for
	 * bonus units
	 * 
	 * @return List of territory cards
	 */
	public HashSet<BonusCard> getBonusCards() {
		return territoryCards;
	}

	/**
	 * Removes cards from the player's bonus cards
	 * 
	 * @param cards
	 *            Cards to remove
	 */
	public void removeBonusCards(List<BonusCard> cards) {
		territoryCards.removeAll(cards);
	}

	// TODO this method is in the wrong class
	/**
	 * TODO doc
	 * 
	 * @return
	 */
	public ArrayList<Continent> getContinents() {
//		// Will hold the list of continents this player owns
//		ArrayList<Continent> continents = new ArrayList<Continent>();
//		
//		for (Continent continent : territoryManager.getContinents()) {
//			if (territoryList.containsAll(continent.getTerritories())) {
//				continents.add(continent);
//			}
//		}
//		
//		return continents;
		return null;
	}
}