/**
 * Spieleverwaltung-Klasse 
 * Initialisiert Alle (Mit-)Spieler-Objekte und
 * kontrolliert welcher Spieler an der Reihe ist.
 * 
 * @author Hendrik
 */
public class Spielerverwaltung {

	private int spielerzahl; // Alle Spieler als int
	private int aktSpieler = 0; // Spieler der am Zug ist
	private Spieler[] spieler; // Array-Liste der Spieler

	// Konstruktor
	public Spielerverwaltung() {

		/*
		 * Abfrage der Mitspieler. Hierbei muss eine Zahl zwischen 2 und 6
		 * angegeben werden. Nachdem der Anwender dies getan hat wird die
		 * Array-Liste Spieler[] mit Anzahl der Mitspielenden initialisiert.
		 */
		do {
			spielerzahl = IO.readInt("Wieviele Spieler?(2-6): ");
		} while (spielerzahl <= 1 || spielerzahl > 6);

		spieler = new Spieler[spielerzahl];

		/*
		 * for-Schleife die den zuvor erstellten Spielern via Abfrage Namen
		 * zuordnet.
		 */
		for (int i = 0; i < spielerzahl; i++) {
			String name = IO.readString("Name Spieler " + (i + 1) + ": ");
			spieler[i] = new Spieler(name);
		}

		// Spieler auflisten und Kenntlich machen, dass das Spiel gestartet
		// wird.
		IO.println("Eine neue Runde wird gestartet es treten an :");
		for (int i = 0; i < spielerzahl; i++) {
			IO.println(spieler[i].getName());
		}
	}

	// Getter & Setter

	public Spieler getAktSpieler() {
		return spieler[aktSpieler];
	}

	public void naechsterSpieler() {
		if (aktSpieler < spieler.length) {
			aktSpieler++;
		}
		/*
		 * Sollte der aktuelle Spieler der letzte in der Reihe gewesen sein
		 * sowird so sichergestellt, dass der erste Spieler wieder dran ist.
		 * TODO um beim Ausscheiden eines Spielers eine zuverlässige Methode zu
		 * erhalten mit ArrayList arbeiten!
		 */

		if (aktSpieler >= spieler.length) {
			aktSpieler = 0;
		}
	}

	public void setSpielerzahl(int spielerzahl) {
		this.spielerzahl = spielerzahl;
	}

	public int getSpielerzahl() {
		return spielerzahl;
	}

	public Spieler[] getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler[] spieler) {
		this.spieler = spieler;
	}

}
