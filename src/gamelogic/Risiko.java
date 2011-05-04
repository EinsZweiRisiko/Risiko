package gamelogic;

/**
 * Risiko-Klasse
 * 
 * @author Hendrik
 */

public class Risiko {

	/**
	 * Konstruktor
	 */
	public Risiko() {
		// Eine Spielrunde starten
		Game game = new Game();
		while (!game.ended()) {
			game.run();
		}
	}

	/**
	 * Constructor
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		new Risiko();
	}
}
