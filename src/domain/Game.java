package domain;

import java.util.ArrayList;
import java.util.List;

import persistence.Store;

import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.Territory;
import domain.exceptions.InvalidTerritoryStateException;
import domain.managers.BonusCardStack;
import domain.managers.PlayerManager;
import domain.managers.TerritoryManager;

/**
 * The game class manages a complete game of Risk
 * 
 * @author Jannes, Hendrik
 * 
 */
public class Game {

	private PlayerManager playerManager;
	private TerritoryManager territoryManager;
	private BonusCardStack bonusCardManager;
	private BonusTracker bonusTracker;
	
	/**
	 * The current player
	 */
	private Player activePlayer;
	
	/**
	 * Phases of a player's turn
	 */
	public static enum Action {
		START, TURNINCARDS, PLACEMENT, ATTACK, MOVEMENT
	};
	
	/**
	 * The current phase of a player's turn
	 */
	private Action currentAction = Action.START;

	/**
	 * Constructor for a new game of Risk
	 */
	public Game(ArrayList<String> playerNames) {
		// Determine that the number of players is valid
		int playerCount = playerNames.size();
		assert playerCount >= 2 && playerCount <= 6; // TODO throw an ordinary exception
		
		// Create territory manager
		territoryManager = new TerritoryManager();

		// Create player manager
		playerManager = new PlayerManager(playerNames);

		// Create bonus card manager
		bonusCardManager = new BonusCardStack();

		// Create bonus tracker
		bonusTracker = new BonusTracker();
		
		// Get the total amount of start units per player
		int startUnits;		
		if (playerCount > 3) {
			startUnits = 30;
		} else if (playerCount == 3) {
			startUnits = 35;
		} else {
			// TODO implement logic for 2 player games
			startUnits = 36;
		}

		// Set the start units for each player
		for (Player player : playerManager) {
			player.addSupplies(startUnits);
		}
		
		// save the stand
		Store store = new Store(playerManager);
		store.save();
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
	}

	public TerritoryManager getTerritoryManager() {
		return territoryManager;
	}

	/**
	 * Returns whether the game is over
	 * @return True, if somebody has won the game
	 */
	public boolean isOver() {
		// TODO Distinguish between world domination/missions
		return playerManager.getCount() == 1;
	}

	/**
	 * Returns the winner of the game, if there is one.<br>
	 * If the game isn't finished yet, <code>null</code> will be returned.
	 * @return Winner of the game
	 */
	public Player getWinner() {
		// Return the last man standing
		Player winner = null;
		if (isOver()) {
			winner = playerManager.getNextPlayer();
		}
		return winner;
	}

	/**
	 * TODO doc
	 * 
	 * @return Player
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	/**
	 * Prepares and returns the next action in the sequence. This method can
	 * change the active player, so always use this method before getting the
	 * active player.<br>
	 * <br>
	 * The sequence of actions in a turn:
	 * <ol>
	 * <li>TURNINCARDS</li>
	 * <li>PLACEMENT</li>
	 * <li>ATTACK</li>
	 * <li>MOVEMENT</li>
	 * </ol>
	 * 
	 * @return Action The next action/phase
	 */
	public Action getNextAction() {
		// Which action comes afterwards the current one?
		switch (currentAction) {
			// The first action is at the end of this switch block
			case TURNINCARDS:
				// Placing the supply units is next
				preparePlacementAction();

			case PLACEMENT:
				// Attacking other players is next
				prepareAttackAction();

			case ATTACK:
				// Moving units is next
				prepareMovementAction();

			case MOVEMENT:
				// TODO Only if the player conquered at least one territory
				activePlayer.addBonusCard(bonusCardManager.retrieveCard());
				// End of a player's turn. Start a new one.
			default:
				// Start
				nextPlayer();
				// Turning in cards is next
				prepareTurnInAction();
		}

		// Return the new action
		return currentAction;
	}

	/**
	 * TODO doc
	 */
	private void nextPlayer() {
		// Advance to the next player
		activePlayer = playerManager.getNextPlayer();
		// A new turn has started so we have to compute the player's supply
		calculateSupplies();
	}
	
	/**
	 * Calculates supply for the current player. This is only called once
	 * in every turn.<br>
	 * <br>
	 * This doesn't include unit supplies for cards that the player may turn in later.
	 */
	private void calculateSupplies() {
		// Base unit amount for occupied territories
		int supplies = activePlayer.getTerritoryCount() / 3;
		// At least 3
		if (supplies < 3) {
			supplies = 3;
		}
		
		// TODO Extra supplies for conquered continents
//		for (Continent continent : activePlayer.getContinents()) {
//			supplies += continent.getBonusSupplies();
//		}
		
		// Add the supplies
		activePlayer.addSupplies(supplies);
	}
	
	/**
	 * TODO doc
	 * @param player
	 * @return
	 */
	private boolean playerCanTurnInCards(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * TODO doc
	 */
	private void prepareTurnInAction() {
		// Can the player turn in cards?
		if (playerCanTurnInCards(activePlayer)) {
			currentAction = Action.TURNINCARDS;
		} else {
			// If the player can't turn in cards, skip to the next step
			preparePlacementAction();
			currentAction = Action.PLACEMENT;
		}
	}

	/**
	 * TODO doc
	 */
	private void preparePlacementAction() {
		currentAction = Action.PLACEMENT;
	}

	/**
	 * TODO doc
	 */
	private void prepareAttackAction() {
		/*
		 * Figure out which territories of the current player could be used for an attack
		 *   Must be owned by the player
		 *   Must have at least 2 units
		 */
		currentAction = Action.ATTACK;
	}
	
	/**
	 * TODO doc
	 */
	private void prepareMovementAction() {
		/*
		 * Figure out which territories have units which are eligible to be moved
		 *   The territory's units must not have participated in a battle
		 *   The territory needs at least 2 units
		 */
		currentAction = Action.MOVEMENT;
	}

	/**
	 * TODO doc
	 */
	public void placeStartUnitsRandomly() {
		Player currentPlayer;
		for (Territory territory : territoryManager.getRandomTerritoryList()) {
			// Cycle through all players
			currentPlayer = playerManager.getNextPlayer();

			// Place one unit on the territory
			try {
				territoryManager.changeTerritoryOwner(currentPlayer, territory,
						1);
			} catch (InvalidTerritoryStateException e) {
				e.printStackTrace();
			}

			// Remove the placed units from the player's supply
			currentPlayer.subtractSupplies(1);
		}

		// Place the remaining units randomly
		while (!playerManager.supplyAllocated()) {
			// Cycle through all players
			currentPlayer = playerManager.getNextPlayer();

			// Add one unit to a random territory
			currentPlayer.getRandomTerritory().addUnits(1);
			// Remove it from the player's supply
			currentPlayer.subtractSupplies(1);
		}

		// Reset the current player to player 1
		playerManager.resetActivePlayer();
	}

	/**
	 * TODO doc
	 */
	public void redeemBonusCards(List<BonusCard> cards) {
		// TODO make this a real exception
		assert activePlayer.getBonusCards().containsAll(cards);
		assert cards.size() == 3;
		// TODO Check if the card triple is valid
		
		// Redeem the cards
		activePlayer.removeBonusCards(cards);
		activePlayer.addSupplies(bonusTracker.getNextBonus());
	}
	
}
