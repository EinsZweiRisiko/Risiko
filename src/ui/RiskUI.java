package ui;

import java.util.ArrayList;

import domain.Game;

/**
 * This class contains the user interface of the game
 * 
 * @author Jannes
 * 
 */
public class RiskUI {

	private Game game;

	/**
	 * Create a new game UI
	 */
	public RiskUI() {
		startNewGame();
		
		while (!game.ended()) {
			turn();
		}
	}

	/**
	 * Creates a new Game instance. This method is only useful for non-networked
	 * games.
	 */
	private void startNewGame() {
		// How many players participate in the game?
		int playerNumber = Input.readInt("Wie viele Mitspieler wollen mitspielen?");
		ArrayList<String> playerNames = new ArrayList<String>(playerNumber);

		// Ask each player for his name
		for (int i = 0; i < playerNumber; ++i) {
			playerNames.add(Input.read("Spieler " + i + ", wie heißt du?"));
		}
		
		// Create the game instance
		game = new Game(playerNames);
	}

	/**
	 * TODO
	 */
	private void turn() {
		
	}
	
	/**
	 * Main method
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		new RiskUI();
	}

}
