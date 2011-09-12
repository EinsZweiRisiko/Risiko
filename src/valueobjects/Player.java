package valueobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import server.TerritoryManager;

/**
 * A class that represents a player
 * 
 * @author Jannes, Hendrik
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 8766228170511017486L;

	/**
	 * This is a static attribute that is used to assign each player a unique ID
	 */
	private static int playerCounter = 0;

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
	 *            Name of the player
	 */
	public Player(String name) {
		this.name = name;
		this.color = playerCounter++;
	}

	/**
	 * Get the player's name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the player's color.
	 * 
	 * @return int color
	 */
	public int getColor() {
		return color;
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
	public List<Territory> getTerritories() {
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
	 * calculate if there ist 3 similiar Bonus cards
	 * 
	 * @param player
	 * @return
	 */
	public boolean canTurnInCards(Player player) {
		int cntInfantry = 0;
		int cntCavalry = 0;
		int cntArtillery = 0;

		if(player.getBonusCards().size() >= 2) {
			for(int i = 0; i < player.getBonusCards().size(); i++) {
				String cardName = player.getBonusCards().get(i).getType().name();
				if(cntInfantry == 2 || cntCavalry == 2 || cntArtillery == 2) {
					cntInfantry = 0;
					cntCavalry = 0;
					cntArtillery = 0;
					return true;
				}else {
					if(cardName == "Infantry" ||  cardName == "Wildcard") {
						cntInfantry++;
					}else if(cardName == "Cavalry" ||  cardName == "Wildcard") {
						cntCavalry++;
					} else if(cardName == "Artillery" ||  cardName == "Wildcard") {
						cntArtillery++;
					}
				}
			}
		}
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
	public List<BonusCard> getBonusCards() {
		return bonusCards;
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

	// TODO implement getContinents
	public List<Continent> getContinents(Player player, TerritoryManager territoryManager) {
		List<Continent> continents = new ArrayList<Continent>();

		for(int i = 0; i < territoryManager.getContinents().size(); i++) {
			if(territoryList.containsAll((Collection<?>) territoryManager.getContinents().get(i).getTerritories())) {
				continents.add(territoryManager.getContinents().get(0));
				return continents;
			}
		}
		return null;
	}

	public int getAllUnits() {
		int units = 0;

		for (Territory territory : territoryList) {
			units += territory.getUnits();
		}

		return units;
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