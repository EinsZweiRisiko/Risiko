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
	private Player[] player;
	private Player currentPlayer;

	public PlayerManager() {

	}

	// Getter & Setter
	
	/**
	 * Setzt den aktuellen Spieler
	 * bei der Initialisierung benötigt!
	 * 
	 */
	public void setCurrentPlayer(int cPlayer) {
		this.currentPlayer = player[cPlayer];
	}

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
		return player;
	}

	public void setPlayer(Player[] player) {
		this.player = player;
	}

}
