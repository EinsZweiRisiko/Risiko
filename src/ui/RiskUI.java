package ui;

import domain.Game;

/**
 * This class contains the user interface of the game
 * @author Jannes
 *
 */
public class RiskUI {
	
	Game game;
	int phase;
	int player;
	
	/**
	 * Create a new game UI
	 */
	public RiskUI() {
		startNewGame();
	}
	
	/**
	 * Creates a new Game instance. This method is only useful for non-networked games.
	 */
	public void startNewGame() {
		int playerNumber = Integer.parseInt(I.read());
		//game = new Game();
	}
	
	public static void main(String[] args) {
		new RiskUI();
	}
	
}
