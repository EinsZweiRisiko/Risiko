package domain;

import java.util.ArrayList;
import java.util.Iterator;

import ui.UserInterface;
import ui.cli.IO;
import valueobjects.Player;

/**
 * Spielerverwaltung
 * 
 * @author Jannes
 */
public class PlayerManager {

	/**
	 * Spielerliste als Array
	 */
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player currentPlayer;
	private Iterator<Player> playerIterator;
	private UserInterface userInterface;

	public PlayerManager(UserInterface userInterface) {
		
		this.userInterface = userInterface;
		
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

	public ArrayList<Player> getPlayer() {
		return players;
	}
	
	public int getNumberOfPlayers(){
		return players.size();
	}

}
