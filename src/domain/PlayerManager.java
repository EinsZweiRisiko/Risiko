package domain;

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
	private Player[] players;
	private Player currentPlayer;
	private UserInterface userInterface;

	public PlayerManager(UserInterface userInterface) {
		
		this.userInterface = userInterface;
		
		int numberOfPlayers = userInterface.getNumberOfPlayers();
		
		players = new Player[numberOfPlayers];
		
		for (int i = 0; i < numberOfPlayers; i++){
			String name = userInterface.getPlayerName(i+1);
			players[i] = new Player(name);
		}

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

	}

	public Player[] getPlayer() {
		return players;
	}
	
	public int getNumberOfPlayers(){
		return players.length;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
		currentPlayer = players[0];
	}

}
