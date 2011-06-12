package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import valueobjects.Player;
import valueobjects.Territory;
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
		// Determines how many players want to play and creates a Game instance
		startNewGame();

		// Places the start units on the board
		placeStartUnits();

//		while (!game.ended()) {
//			run();
//		}
	}

	/**
	 * Creates a new Game instance. This method is only useful for non-networked
	 * games.
	 */
	private void startNewGame() {
		// How many players participate in the game?
		int playerNumber = Input.readNumberInRange(
				"Wie viele Spieler wollen mitspielen?", 2, 6);
		// TODO the min and max check should be done by the Game class. It's
		// game logic.

		// Ask each player for his name
		ArrayList<String> playerNames = new ArrayList<String>(playerNumber);
		for (int i = 0; i < playerNumber; ++i) {
			playerNames.add(Input
					.read("Spieler " + (i + 1) + ", wie heißt du?"));
		}

		// Create the game instance
		game = new Game(playerNames);
	}

	/**
	 * Places the start units on the board.
	 */
	private void placeStartUnits() {
		boolean placementMethod = Input
				.readYesNo("Sollen die Einheiten zufällig gesetzt werden?");

		if (placementMethod) {
			// Randomly places the units on one territory each
			game.placeStartUnitsRandomly();
		} else {
//			// Abwechselnd Einheiten setzen
//			// holt sich alle Länder und speichert sie in eine ArrayList
//			ArrayList<Territory> emptyTerritories = new ArrayList<Territory>();
//			emptyTerritories = territoryManager.getTerritoryList();
//	
//			while (!territoryManager.allOccupied()) {
//				currentPlayer = playerManager.getNextPlayer();
//				// gibt aus welcher Spieler dran ist
//				ui.announceCurrentPlayer(currentPlayer);
//				// Auswahl des Landes als Zahl in Input
//				int input = ui.getEmptyTerritoryManualSet(emptyTerritories);
//	
//				try {
//					// besetzt das Land mit einer Einheit
//					territoryManager.changeTerritoryOwner(currentPlayer,
//							emptyTerritories.get(input), 1);
//					emptyTerritories.remove(emptyTerritories.get(input));
//	
//					// Remove the placed units from the player's supply
//					currentPlayer.subtractSupply(1);
//				} catch (InvalidTerritoryStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//			activePlayer = playerManager.getNextPlayer();
//			
//			if (territoryManager.allOccupied()) {
//				// walk trough the list of players and let every Player place the rest of the supply
//				for (Player player : playerManager.getPlayers()) {
//					ui.announceCurrentPlayer(player);
//					placeUnits(0);
//					activePlayer = playerManager.getNextPlayer();
//				}
//			}
//			
//			//Go to the first Player and announce the start of the game
//			playerManager.getNextPlayer();
//			ui.announceGameStart();
		}
	}

	private void run() {
		Player player = game.getActivePlayer();

		switch (game.getNextPhase()) {
			case TURNINCARDS:
				break;

			case PLACE:
				// Number of supply
				// Show territories owned by the player
				// Get target territory
				// How many units should be placed?
				// Place the supply
				int supply = player.getSupply();
				write("Your supply is: " + supply);
				write("You own these territories:");
				writeList(player.getTerritories());
				Input.readNumber("Where do you want to place your supply units?");
				break;

			case ATTACK:
				break;

			case MOVE:
				break;

			case DEFEND:
				break;
		}
	}

	/**
	 * Prints a message to the console
	 * 
	 * @param message
	 *            String which is going to be printed
	 */
	private static void write(String message) {
		System.out.println(message);
	}

	/**
	 * Prints every item of a List and its position in the List.<br>
	 * <br>
	 * Example output:
	 * 
	 * <pre>
	 * (1) Alaska
	 * (2) Ural
	 * </pre>
	 * 
	 * @param list
	 */
	private static void writeList(List<?> list) {
//		for (int i = 0, length = list.size(); i < length; ++i) {
		Iterator<?> iter = list.iterator();
		for (int i = 1; iter.hasNext(); ++i) {
			write("(" + i + ") " + iter.next());
		}
	}
	
	/**
	 * Main method
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		new RiskUI();
	}

}
