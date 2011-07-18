package valueobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a player
 * 
 * @author Jannes, Hendrik
 */
public class Player extends BasePlayer {
	private static final long serialVersionUID = 8766228170511017486L;

	/**
	 * The territories that the player has conquered
	 */
	private ArrayList<Territory> territoryList = new ArrayList<Territory>();

	/**
	 * A list of territory cards that the player currently has. These can be
	 * exchanged for bonus units
	 * at the start of every round.
	 */
	private ArrayList<BonusCard> bonusCards = new ArrayList<BonusCard>();

	ArrayList<Continent> continents = new ArrayList<Continent>();
	
	Continent continent;
	
	/**
	 * Constructor
	 * 
	 * @param name
	 *            of the player.
	 */
	public Player(String name) {
		super(name);
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
	 * TODO doc
	 * @param player
	 * @return
	 */
	public boolean canTurnInCards() {
		// TODO Auto-generated method stub
		return false;
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
	 * Adds a card to the player's list of territory cards
	 * 
	 * @param card
	 *            The territory card to be added
	 */
	public void addBonusCard(BonusCard card) {
		bonusCards.add(card);
	}

	/**
	 * Returns the player's list of territory cards which can be exchanged for
	 * bonus units
	 * 
	 * @return List of territory cards
	 */
	public ArrayList<BonusCard> getBonusCards() {
		return bonusCards;
	}
	/**
	 * Returns the count of supply that the player must set
	 * 
	 * @return count of supplies that to be set
	 */
	public int getSuppliesToAllocate() {
		return suppliesToAllocate;
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

	// TODO this method is in the wrong class
	/**
	 * TODO doc
	 * 
	 * @return
	 */
	public ArrayList<Continent> getContinents() {
		// Will hold the list of continents this player owns	
		for (int i = 1; i <= territoryList.size(); i++) {
			if (territoryList.containsAll(continent.getTerritories())) {
				continents.add(continent);
			}
		}
		return continents;
	}
	
	public int getAllUnits() {
		int units = 0;
		
		for(Territory territory: territoryList){
			units += territory.getUnits();
		}
		
		return units;
	}

}