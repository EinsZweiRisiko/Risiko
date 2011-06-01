package domain;

import persistence.FilePersistenceManager;
import persistence.PersistenceManager;
import ui.UserInterface;
import ui.cli.CommandLineInterface;

/**
 * Risk class
 * 
 * @author Hendrik, Jannes
 */

public class Risk {

	/**
	 * Constructor
	 */
	public Risk() {
		// TODO Spieleranzahl, Namen, Farben

		UserInterface ui = new CommandLineInterface();

		do {

			// Eine Spielrunde starten
			Game game;

			if (ui.wantToLoad()) {
				// load a game
				PersistenceManager pm = new FilePersistenceManager();
				game = pm.loadGame("risikoSave.ser");
			} else {
				// create a new Game
				game = new Game(ui);
			}

			// Spiel laufen lassen
			while (!game.ended()) {
				game.run();
			}

			// Gewinner ausgeben
			ui.announceWinner(game.getWinner());

		} while (ui.askForNextRound());
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String args[]) {
		new Risk();
	}
}
