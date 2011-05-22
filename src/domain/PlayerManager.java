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
	private Player[] spieler;

	public PlayerManager() {

		/*
		 * Abfrage der Mitspieler. Hierbei muss eine Zahl zwischen 2 und 6 angegeben werden. Nachdem
		 * der Anwender dies getan hat wird die Array-Liste Spieler[] mit Anzahl der Mitspielenden
		 * initialisiert.
		 */
//		do {
//			spielerzahl = IO.readInt("Wieviele Spieler?(2-6): ");
//		} while (spielerzahl <= 1 || spielerzahl > 6);
//
//		spieler = new Spieler[spielerzahl];

		/*
		 * for-Schleife die den zuvor erstellten Spielern via Abfrage Namen zuordnet.
		 */
//		for (int i = 0; i < spielerzahl; i++) {
//			String name = IO.readString("Name Spieler " + (i + 1) + ": ");
//			spieler[i] = new Spieler(name);
//		}
//
//		// Spieler auflisten und Kenntlich machen, dass das Spiel gestartet
//		// wird.
//		IO.println("Eine neue Runde wird gestartet es treten an :");
//		for (int i = 0; i < spielerzahl; i++) {
//			IO.println(spieler[i].getName());
//		}
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

	public Player[] getSpieler() {
		return spieler;
	}

	public void setSpieler(Player[] spieler) {
		this.spieler = spieler;
	}

}
