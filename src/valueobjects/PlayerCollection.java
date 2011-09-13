package valueobjects;

import java.util.ArrayList;

import server.exceptions.NoPlayersException;

/**
 * Spielerverwaltung
 * 
 * @author Jannes
 */
public class PlayerCollection extends ArrayList<Player> {

	private static final long serialVersionUID = -1994487892680769106L;

	private int activePlayer = 0;

	/**
	 * Switches to the next player
	 * 
	 * @return Next player
	 */
	public Player getNextPlayer() {
		
		//System.out.println("ActivePlayerINT : "+ activePlayer + " NAME : " + get(activePlayer).getName());
		
		// There hast to be at least one player left
		if (isEmpty()) {
			throw new NoPlayersException();
		}

		Player nextPlayer = get(activePlayer);
		// Switch to the next player
		activePlayer++;
		
		// If we reached the end, start over
		if (activePlayer >= size()) {
			activePlayer = 0;
		}

		return nextPlayer;
	}

	public Player getNextPlayer2() {
		return get(activePlayer);
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
		for (Player player : this) {
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
	
	public Player getPlayer(int index) {
		return get(index);
	}
}
