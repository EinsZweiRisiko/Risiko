package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import ui.UserInterface;
import valueobjects.Player;
import domain.exceptions.NoPlayersException;

/**
 * Spielerverwaltung
 * 
 * @author Jannes
 */
public class PlayerManager implements Iterable<Player>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7984052981309484762L;
	
	/**
	 * Spielerliste als Array
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player activePlayer;
	private Iterator<Player> playerIterator;

	/**
	 * Constructor
	 * 
	 * @param ui
	 */
	public PlayerManager(UserInterface ui) {

		int numberOfPlayers = ui.getNumberOfPlayers();

		for (int i = 0; i < numberOfPlayers; i++) {
			String name = ui.getPlayerName(i + 1);
			players.add(new Player(name));
		}

		playerIterator = players.iterator();
	}

	/**
	 * Returns an iterator object of all players
	 * 
	 * @return Player iterator object
	 */
	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}

	/**
	 * Switches to the next player
	 * 
	 * @return Next player
	 */
	public Player getNextPlayer() {
		if (players.isEmpty()) {
			throw new NoPlayersException();
		}

		// If we reached the end, start over
		if (!playerIterator.hasNext()) {
			playerIterator = players.iterator();
		}
		
		// Switch to the next player
		activePlayer = playerIterator.next();
		
		return activePlayer;
	}
	
	/**
	 * Returns the currently active player
	 * @return Currently active player
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}

	/**
	 * Returns all players
	 * 
	 * @return List of players
	 */
	public ArrayList<Player> getPlayers() {
		// TODO protect this list from changes
		return players;
	}

	/**
	 * Returns the total number of players
	 * 
	 * @return Number of players
	 */
	public int getPlayerCount() {
		return players.size();
	}

	/**
	 * Returns true if every player has allocated all of his supply.
	 * 
	 * @return True, if every player has allocated his supply
	 */
	public boolean supplyAllocated() {
		for (Player player : players) {
			// Check if the player still has supply that needs to be allocated
			if (player.getSupply() != 0) {
				return false;
			}
		}

		// No supply pending
		return true;
	}
	
	/**
	 * Returns null if the player has allocated all of his supply.
	 * 
	 * @return player, if the player has allocated his supply
	 */
	public boolean playerSupplyAllocated(Player player) {
		if(player.getSupply() != 0) {
			return false;
		}else return true;
	}
}
