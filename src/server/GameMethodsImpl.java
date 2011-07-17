package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import server.exceptions.InvalidTerritoryStateException;
import server.exceptions.NotEnoughPlayersException;
import server.remoteexceptions.ServerFullException;
import ui.IO;
import valueobjects.BonusCard;
import valueobjects.BonusCardStack;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;

import commons.ClientMethods;
import commons.GameMethods;

import de.root1.simon.Registry;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.NameBindingException;
import de.root1.simon.exceptions.SimonRemoteException;

/**
 * The game class manages a complete game of Risk
 * 
 * @author Jannes, Hendrik
 * 
 */
@SimonRemote
public class GameMethodsImpl implements GameMethods, Serializable {

	private static final long serialVersionUID = -3491803188267650698L;

	private boolean started = false; 
	private PlayerCollection players = new PlayerCollection();
	private boolean changed = false;
	private List<ClientMethods> clients = new ArrayList<ClientMethods>();
	
	private TerritoryManager territoryManager = new TerritoryManager();
	private BonusCardStack bonusCardManager = new BonusCardStack();
	private BonusTracker bonusTracker = new BonusTracker();
	
	/**
	 * The current player
	 */
	private Player currentPlayer;

	/**
	 * The current phase of a player's turn
	 */
	private Action currentAction = Action.START;

	/**
	 * Phases of a player's turn
	 */
	public static enum Action {
		START, TURNINCARDS, PLACEMENT, ATTACK, MOVEMENT
	};

	public GameMethodsImpl(String name, int port) throws UnknownHostException,
			IOException, NameBindingException {
		Registry registry = Simon.createRegistry(port);
		registry.bind(name, this);
	}

	public void addPlayer(String name, ClientMethods client) throws ServerFullException {
		if (started) {
			// Game is already in progress
			throw new ServerFullException();
		} else if (players.size() >= 6) {
			// Too many players
			throw new ServerFullException();
		}

		// Add the client
		if (client == null) {
            throw new NullPointerException();
		}
		clients.add(client);
		
		// TODO: Determine that the number of players is valid
		Player player = new Player(name);
		players.add(player);
		
		IO.write("Client connected.");
		// TESTEN
		setChanged();
		notifyPlayers("TEST");
	}

	public void deletePlayer(ClientMethods client) {
		clients.remove(client);
		
	}
	private void setChanged() {
		changed = true;
	}
	
	private void clearChanged() {
		changed = false;
	}
	
	@SuppressWarnings("unused")
	private void notifyPlayers() {
		notifyPlayers(null);
	}
	
	private void notifyPlayers(Object arg) {
		if (!changed) {
			return;
		}
		// Notify all observers
		for (ClientMethods client:clients) {
			client.update(this, arg);
		}
		clearChanged();
	}

	// END OF observable
	
	public void start() throws NotEnoughPlayersException {
		int playerCount = players.size();
		
		// Check if there are enough players
		if (playerCount < 3) {
			// TODO: implement logic for 2 players
			throw new NotEnoughPlayersException(playerCount);
		}

		// Start units for every player
		int startUnits;
		// Get the total amount of start units per player
		if (playerCount > 3) {
			startUnits = 30;
		} else if (playerCount == 3) {
			startUnits = 35;
		} else {
			// TODO implement logic for 2 player games
			startUnits = 36;
		}

		// Set the start units for each player
		for (Player player : players) {
			player.addSupplies(startUnits);
		}
	
		placeStartUnitsRandomly();
		
		// Set the game status to started
		started = true;
	}

	public PlayerCollection getPlayers() {
		return players;
	}

	public TerritoryManager getTerritoryManager() {
		return territoryManager;
	}

	/**
	 * Returns whether the game is over
	 * 
	 * @return True, if somebody has won the game
	 */
	public boolean isOver() {
		// TODO Distinguish between world domination/missions
		return players.size() == 1;
	}

	/**
	 * Returns the winner of the game, if there is one.<br>
	 * If the game isn't finished yet, <code>null</code> will be returned.
	 * 
	 * @return Winner of the game
	 */
	public Player getWinner() {
		// Return the last man standing
		Player winner = null;
		if (isOver()) {
			winner = players.getNextPlayer();
		}
		return winner;
	}

	/**
	 * TODO doc
	 * 
	 * @return Player
	 */
	public Player getActivePlayer() {
		return currentPlayer;
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
				currentPlayer.addBonusCard(bonusCardManager.retrieveCard());
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
		currentPlayer = players.getNextPlayer();
		// A new turn has started so we have to compute the player's supply
		calculateSupplies();
	}

	/**
	 * Calculates supply for the current player. This is only called once
	 * in every turn.<br>
	 * <br>
	 * This doesn't include unit supplies for cards that the player may turn in
	 * later.
	 */
	private void calculateSupplies() {
		// Base unit amount for occupied territories
		int supplies = currentPlayer.getTerritoryCount() / 3;
		// At least 3
		if (supplies < 3) {
			supplies = 3;
		}

		// TODO Extra supplies for conquered continents
//		for (Continent continent : activePlayer.getContinents()) {
//			supplies += continent.getBonusSupplies();
//		}

		// Add the supplies
		currentPlayer.addSupplies(supplies);
	}

	/**
	 * TODO doc
	 */
	private void prepareTurnInAction() {
		// Can the player turn in cards?
		if (currentPlayer.canTurnInCards()) {
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
		 * Figure out which territories of the current player could be used for
		 * an attack
		 * Must be owned by the player
		 * Must have at least 2 units
		 */
		currentAction = Action.ATTACK;
	}

	/**
	 * TODO doc
	 */
	private void prepareMovementAction() {
		/*
		 * Figure out which territories have units which are eligible to be
		 * moved
		 * The territory's units must not have participated in a battle
		 * The territory needs at least 2 units
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
			currentPlayer = players.getNextPlayer();

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
		while (!players.supplyAllocated()) {
			// Cycle through all players
			currentPlayer = players.getNextPlayer();

			// Add one unit to a random territory
			currentPlayer.getRandomTerritory().addUnits(1);
			// Remove it from the player's supply
			currentPlayer.subtractSupplies(1);
		}

		// Reset the current player to player 1
		players.resetActivePlayer();
	}

	/**
	 * TODO doc
	 */
	public void redeemBonusCards(List<BonusCard> cards) {
		// TODO make this a real exception
		assert currentPlayer.getBonusCards().containsAll(cards);
		assert cards.size() == 3;
		// TODO Check if the card triple is valid

		// Redeem the cards
		currentPlayer.removeBonusCards(cards);
		currentPlayer.addSupplies(bonusTracker.getNextBonus());
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public Mission getMyMission(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BonusCard> getMyBonusCards(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Territory> getTerritories() {
		return territoryManager.getTerritoryMap();
	}

	@Override
	public List<Territory> getMyTerritories(Player player) {
		// TODO Auto-generated method stub
		return player.getTerritories();
	}

	@Override
	public List<Territory> getMyTerritoriesForAttacking(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Territory> getMyTerritoriesForMoving(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Territory> getOpposingNeighborsOf(Territory territory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Territory> getSimilarNeighborsOf(Territory territory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeUnits(Territory territory, int amount) {
		// TODO Change the value of the territory
		territory.setUnits(amount);
	}

	@Override
	public void attack(Territory attackingTerritory,
			Territory attackedTerritory, int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Territory source, Territory target, int amount)
			throws SimonRemoteException {
		// TODO Auto-generated method stub
		source.setUnits(source.getUnits() - amount);
		target.setUnits(target.getUnits() + amount);
	}

}
