package domain;

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
		Game game = new Game();

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
