package domain;

import domain.persistence.FilePersistenceManager;
import domain.persistence.PersistenceManager;
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

		// Eine Spielrunde starten
		UserInterface ui = new CommandLineInterface();
		
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
