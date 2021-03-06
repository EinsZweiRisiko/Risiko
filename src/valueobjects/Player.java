package valueobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import server.TerritoryManager;

import commons.GameMethods;

/**
 * A class that represents a player
 * 
 * @author Jannes, Hendrik, Timur
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 8766228170511017486L;

	/**
	 * This is a static attribute that is used to assign each player a unique ID
	 */
	private static int staticPlayerCounter = 0;

	/**
	 * Name of the player
	 */
	private String name;

	/**
	 * Color of the player
	 */
	private int color;

	/**
	 * The territories that the player has conquered
	 */
	private List<Territory> territoryList = new ArrayList<Territory>();

	/**
	 * A list of territory cards that the player currently has. These can be
	 * exchanged for bonus units
	 * at the start of every round.
	 */
	private List<BonusCard> bonusCards = new ArrayList<BonusCard>();

	/**
	 * The number of units the player still has to place on the board. This
	 * happens at the start of every round. The player can only start attacking
	 * someone else if all units are placed.
	 */
	private int suppliesToAllocate = 0;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            Name of the player as String.
	 */
	public Player(String name) {
		this.name = name;
		// Increase the player counter
		this.color = staticPlayerCounter++;
	}

	/**
	 * Get the player's name.
	 * 
	 * @return name String
	 */
	public String toString() {
		return name;
	}

	/**
	 * Get the player's color.
	 * Get the player's name
	 * 
	 * @return name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the player's name
	 * 
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		// TODO remove as soon as we have a working persistence
		this.name = name;
	}

	/**
	 * Get the player's color.
	 * 
	 * @return color int
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Set the player's color
	 * 
	 * @param color
	 *            int
	 */
	public void setColor(int color) {
		// TODO remove as soon as we have a working persistence
		this.color = color;
	}

	/**
	 * Returns a list of territories the player owns.
	 * 
	 * The list should not be altered in any way.
	 * 
	 * @return List of territories
	 */
	public List<Territory> getTerritories() {
		// TODO The internal list is unprotected
		return territoryList;
	}

	/**
	 * Returns the number of countries the player currently owns.
	 * 
	 * @return int Number of countries
	 */
	public int getTerritoryCount() {
		return territoryList.size();
	}

	/**
	 * Returns true, if the player is dead, i.e. he doesn't own any territories.
	 * 
	 * @return boolean
	 */
	public boolean isDead() {
		return territoryList.isEmpty();
	}

	/**
	 * Returns a random territory which is owned by the player
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
	 * Returns the player's list of territory cards which can be exchanged for
	 * bonus units
	 * 
	 * @return List of territory cards
	 */
	public List<BonusCard> getBonusCards() {
		return bonusCards;
	}

	/**
	 * Adds a card to the player's list of territory cards
	 * 
	 * @param card
	 *            The territory card to be added
	 */
	public void addBonusCard(BonusCard card) {
		bonusCards.add(card);
	}

	/**
	 * Removes cards from the player's bonus cards
	 * 
	 * @param cards
	 *            Cards to remove
	 */
	public void removeBonusCards(List<BonusCard> cards) {
		bonusCards.removeAll(cards);
	}

	/**
	 * Gets all continents that the player has conquered completely.
	 * 
	 * @param player
	 *            Player
	 * @param t
	 *            TerritoryManager
	 * @return
	 */
	public List<Continent> getContinents(TerritoryManager t) {
		Collection<Continent> allContinents = t.getContinents();
		List<Continent> myContinents = new ArrayList<Continent>();

		for (Continent continent : allContinents) {
			if (territoryList.containsAll(continent.getTerritories())) {
				myContinents.add(continent);
			}
		}

		return myContinents;
	}

	/**
	 * Gets a count of all units that the player has placed on the board.
	 * 
	 * @return Unit count
	 */
	public int getUnitCount() {
		int count = 0;
		for (Territory territory : territoryList) {
			count += territory.getUnitCount();
		}
		return count;
	}

	/**
	 * Calculate if there are 3 similiar bonus cards
	 * 
	 * @param player
	 * @return
	 */
	public boolean canTurnInCards() {
		int numBonusCards = bonusCards.size();
		int cntInfantry = 0;
		int cntCavalry = 0;
		int cntArtillery = 0;

		if (numBonusCards >= 2) {
			for (int i = 0; i < numBonusCards; i++) {
				String cardName = bonusCards.get(i).getType().name();
				if (cntInfantry == 2 || cntCavalry == 2 || cntArtillery == 2) {
					cntInfantry = 0;
					cntCavalry = 0;
					cntArtillery = 0;
					return true;
				} else {
					if (cardName == "Infantry" || cardName == "Wildcard") {
						cntInfantry++;
					} else if (cardName == "Cavalry" || cardName == "Wildcard") {
						cntCavalry++;
					} else if (cardName == "Artillery"
							|| cardName == "Wildcard") {
						cntArtillery++;
					}
				}
			}
		}
		return false;
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
	 * Returns a random territory which is owned by the player.
	 * 
	 * @see GameMethods placeStartUnitsRandomly
	 * @return A random Territory
	 */
	public Territory getRandomTerritory() {
		// Generate a pseudo random number
		int random = (int) (Math.random() * territoryList.size());
		// Return the territory
		return territoryList.get(random);
	}

	/**
	 * Check if two player objects are equal
	 */
	public boolean equals(Player player) {
//		if (!(o instanceof Player)) {
//			return false;
//		}
//		Player player = (Player) o;
		return getColor() == player.getColor();
	}

	public void setSuppliesToAllocate(int suppliesToAllocate) {
		this.suppliesToAllocate = suppliesToAllocate;
	}

}