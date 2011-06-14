package domain.managers;

import java.util.ArrayList;
import java.util.Iterator;

import valueobjects.Player;
import domain.exceptions.NoPlayersException;

/**
 * Spielerverwaltung
 * 
 * @author Jannes
 */
public class PlayerManager implements Iterable<Player> {

	/**
	 * Array that contains all players. If this is changed, the playerCount also
	 * needs to be updated.
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	private int activePlayer;

	/**
	 * Constructor
	 * 
	 * @param ui
	 */
	public PlayerManager(ArrayList<String> playerNames) {
		// Creates new player instances for each player
		for (String name : playerNames) {
			players.add(new Player(name));
		}

		// Set the index to determine the current player
		activePlayer = 0;
	}

	/**
	 * Returns an iterator object of all players
	 * 
	 * @return Player iterator object
	 */
	@Override
	public Iterator<Player> iterator() {
		// TODO protect this arraylist
		return players.iterator();
	}

	/**
	 * TODO doc
	 * 
	 * @param activePlayer
	 */
	public void removePlayer(Player activePlayer) {
		players.remove(activePlayer);
	}

	/**
	 * Returns the total number of players
	 * 
	 * @return Number of players
	 */
	public int getCount() {
		return players.size();
	}

	/**
	 * Switches to the next player
	 * 
	 * @return Next player
	 */
	public Player getNextPlayer() {
		// There hast to be at least one player left
		if (players.isEmpty()) {
			throw new NoPlayersException();
		}

		// Switch to the next player
		++activePlayer;

		// If we reached the end, start over
		if (activePlayer >= players.size()) {
			activePlayer = 0;
		}

		return players.get(activePlayer);
	}

	/**
	 * Resets the currently active player, so that the first player is the
	 * new active player. Use this with caution.
	 */
	public void resetActivePlayer() {
		activePlayer = 0;
	}

	/**
	 * Returns true if every player has allocated all of his supply.
	 * 
	 * @return True, if every player has allocated his supply
	 */
	public boolean supplyAllocated() {
		for (Player player : players) {
			// Check if the player still has supply that needs to be allocated
			if (player.getSupplies() != 0) {
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
		if (player.getSupplies() != 0) {
			return false;
		} else
			return true;
	}

}
