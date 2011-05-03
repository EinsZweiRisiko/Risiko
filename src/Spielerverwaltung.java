public class Spielerverwaltung {

	private int spielerzahl;
	private int aktSpieler = 0;
	private Spieler[] spieler;

	public Spielerverwaltung() {
		do {
			spielerzahl = IO.readInt("Wieviele Spieler?(2-6): ");
		} while (spielerzahl <= 1 || spielerzahl > 6);

		spieler = new Spieler[spielerzahl];

		// Spielernamen setzen
		for (int i = 0; i < spielerzahl; i++) {
			String name = IO.readString("Name Spieler " + (i + 1) + ": ");
			spieler[i] = new Spieler(name);
		}

		// Spieler auflisten
		IO.println("Eine neue Runde wird gestartet es treten an :");
		for (int i = 0; i < spielerzahl; i++) {
			IO.println(spieler[i].getName());
		}
	}

	public Spieler getAktSpieler() {
		return spieler[aktSpieler];
	}

	public void naechsterSpieler() {
		if (aktSpieler < spieler.length) {
			aktSpieler++;
		}
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
