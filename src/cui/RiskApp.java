package cui;

import server.GameMethodsImpl;

/**
 * This class contains the user interface of the game
 * 
 * @author Jannes
 * 
 */
@Deprecated
public class RiskApp {

	/**
	 * Responsible for printing and reading from the console
	 */
	private IO io = new IO();
	
	/**
	 * SIMON connection
	 */
	private GameMethodsImpl game;

	/**
	 * Create a new game UI
	 * @param defaultSetup This parameter is meant for easier testing
	 */
	public RiskApp() {
		// Setup the game
		boolean localGame = io.readYesNo("Do you want to play locally?");
		if (localGame) {
			// Start a local game with multiple local players
//			assert playerNumber >= 2 && playerNumber <= 6; // TODO anders machen
			
			game = new GameMethodsImpl();
			Boolean morePlayers = true;
			// Ask each player for his name
			while (morePlayers) {
				String name = io.read("Wie heißt du?");
				// Adding a player returns a player object
				game.addPlayer(name);
				
				// Start client
				//PlayerClient pc	= new PlayerClient(game, player);
				//Thread t = new Thread(pc);
				//t.start();				
				
				// Another player?
				morePlayers = io.readYesNo("Soll noch ein weiterer Spieler hinzugefügt werden?"); 
			}
			game.start();
		} else {
			// Start a multiplayer game
			// TODO to be implemented
			boolean join = io.readYesNo("Do you want to join a game?");
			if (join) {
				// join
			} else {
				// create game
			}
		}

		// Start the game
		game.start();
	}

	/**
	 * Places the start units on the board.
	 */
	private void placeStartUnits() {
		boolean placementMethod = io.readYesNo("Sollen die Einheiten zufällig gesetzt werden?");

		if (placementMethod) {
			// Randomly places the units on one territory each
			game.placeStartUnitsRandomly();
		} else {
			// TODO Manually place the units
			/*
			 * Repeat until all territories are occupied
			 *   1. Get current player
			 *   2. Get list of empty territories
			 *   3. Get target territory
			 *   (Cache empty territories?)
			 * Repeat until every player has 0 supply
			 * 	 1. Get current player
			 *   2. Get list of the player's territories
			 *   3. Get target territory
			 * 
			 * Placing requires:
			 *   tm.changeTerritoryOwner(currentPlayer, targetTerritory, units);
			 *   currentPlayer.subtractSupply(units);
			 */
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		// custom port?
		new RiskApp();
	}

}
