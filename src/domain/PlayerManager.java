package domain;

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

	public PlayerManager() {

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
