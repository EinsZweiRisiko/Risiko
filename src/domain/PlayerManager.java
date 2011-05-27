package domain;

import java.util.ArrayList;
import java.util.Iterator;
import ui.UserInterface;
import valueobjects.Player;

/**
 * Spielerverwaltung
 * 
 * @author Jannes
 */
public class PlayerManager implements Iterable<Player> {

	/**
	 * Spielerliste als Array
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private Iterator<Player> playerIterator;

	public PlayerManager(UserInterface userInterface) {
		
		int numberOfPlayers = userInterface.getNumberOfPlayers();
		
		for (int i = 0; i < numberOfPlayers; i++){
			String name = userInterface.getPlayerName(i+1);
			players.add(new Player(name));
		}
		
		playerIterator = players.iterator();
		nextPlayer();

	}

	// Getter & Setter
	

	/**
	 * Gibt den aktuellen Spieler zurück
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Gibt den nächsten Spieler zurück
	 */
	public void nextPlayer() {
		if (!playerIterator.hasNext()) {
			playerIterator = players.iterator();
		}
		// Wenn players leer ist, passiert was ganz schlimmes
		currentPlayer = playerIterator.next();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public int getNumberOfPlayers(){
		return players.size();
	}

	/**
	 * Returns true if every player has allocated all of his supply
	 * @return True if every player has allocated all of his supply
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
	 * Returns an iterator object of all players
	 * @return Player iterator object
	 */
	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
	
}
