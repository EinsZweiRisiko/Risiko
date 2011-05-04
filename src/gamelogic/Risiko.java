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
		
		// Spiel laufen lassen
		while (!game.ended()) {
			game.run();
		}
		
		// Gewinner ausgeben
		//game.getGewinner();
	}

	/**
	 * Constructor
	 * @param args Command line arguments
	 */
	public static void main(String args[]) {
		new Risiko();
	}
}
