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

	public PlayerManager() {

	}

	// Getter & Setter

	/**
	 * Gibt den aktuellen Spieler zurück
	 */
	public Player getCurrentPlayer() {
		return null;
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
