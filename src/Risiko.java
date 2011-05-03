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

		// Länder && Spieler initialisieren
		Laenderverwaltung laenderVerwaltung = new Laenderverwaltung();
		Spielerverwaltung spielerVerwaltung = new Spielerverwaltung();

		// Initialisierung der Partie
		initialisierung(laenderVerwaltung, spielerVerwaltung);

		/*
		 * Spielzyklus, der je für einen Spieler durchgeführt wird solange bis
		 * ein Spieler gewonnen hat. Nachdem der Spieler seine Runde beendet
		 * hat, wird der Zyklus für den nächsten Spieler gestartet.
		 */
		while (!spielZuEnde()) {
			Spielzyklus spielzyklus = new Spielzyklus(
					spielerVerwaltung.getAktSpieler(), laenderVerwaltung);
			spielerVerwaltung.naechsterSpieler();
		}
	}

	// Main-Klasse hier wird ledglich eine Instanz erstellt Spielablauf siehe
	// Konstruktor
	public static void main(String args[]) {
		Risiko risiko = new Risiko();
	}

	/**
	 * Funktion die das Spiel einleitet. In dieser werden die Spieler
	 * aufgefordert ihre Start-Armeen zu verteilen.
	 */
	private void initialisierung(Laenderverwaltung laenderVerwaltung,
			Spielerverwaltung spielerVerwaltung) {

		/*
		 * Anhand der Anzahl der Spieler wird festgestellt wieviele Armeen
		 * gesetzt werden dürfe. Hierbei gilt: bei 2 Spieler 40 bei 3, 35 bei 2,
		 * 30 usw... Nachdem die Spielerzahl ermittelt und die Spieler-Objekte
		 * abgefragt wurden, werden diesen mittels einer for-Schleife die Armeen
		 * zugeordnet.
		 */
		int startArmeen = 50 - ((spielerVerwaltung.getSpielerzahl() + 1) * 5);
		Spieler[] spieler = spielerVerwaltung.getSpieler();

		for (int i = 0; i < spielerVerwaltung.getSpielerzahl(); i++) {
			spieler[i].setReserveArmeen(startArmeen);
		}

		/*
		 * TODO Reelle Verteilung!! Hier werden nun VORERST zu Testzwecken die
		 * Länder per Random an die Spieler verteilt, indem wie laut Regeln des
		 * Spiels Risiko zunächst je einer Armee einem Land zugeordnet wird und
		 * im nachhinein dann auch Felder mit mehr als einer Armee besetzt
		 * werden. Äußere for-Schleife zählt die Anzahl der zu vergebenen
		 * Armeen. Innere den Spieler welcher diese setzen darf. So wird reih um
		 * von den Spielern eine Armee gesetzt.
		 */
		for (int i = 0; i < startArmeen; i++) {
			for (int j = 0; j < spielerVerwaltung.getSpielerzahl(); j++) {
				int r = (int) (Math.random() * 42);
				Land land = laenderVerwaltung.getLandByNumber(r);

				/*
				 * Wenn das Land niemanden gehört platziere eine Einheit und
				 * verändere die Zugehörigkeit des Feldes. Erniedrige Außerdem
				 * deine restlichen Armeen um Ein.
				 */
				if (land.getBesitzer() == null) {
					land.setAnzahlEinheiten(land.getAnzahlEinheiten() + 1);
					land.setBesitzer(spieler[j]);
					spieler[j]
							.setReserveArmeen(spieler[j].getReserveArmeen() - 1);

				} else {
					// TODO Else-Zweig was soll geschehen wenn das Feld bereits
					// besetzt ist?
				}
			}
		}
	}
	
	private boolean spielZuEnde() {
		// TODO Überprüfungsfunktion erstellen
		return false;
	}
}
