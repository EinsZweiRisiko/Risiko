package gamelogic;
import dataobjects.Land;
import dataobjects.Spieler;
import dataobjects.Spielerverwaltung;
import io.IO;

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

		Spieler[] laenderBesetzung = new Spieler[42];
		Land land;

		for (int i = 0; i <= 41; i++) {
			for (int j = 0; j <= (spielerVerwaltung.getSpielerzahl() - 1); j++) {
				int r = (int) (Math.random() * 42);

				if (!laenderVerwaltung.isAlleLaenderBesetzt()) {
					while (laenderBesetzung[r] != null) {
						r = (int) (Math.random() * 42);
					}

					land = laenderVerwaltung.getLandByNumber(r);
					land.setBesitzer(spieler[j]);
					land.setAnzahlEinheiten(1);
					laenderBesetzung[r] = spieler[j];
					
				} else {
					while (laenderBesetzung[r].equals(spieler[j])) {
						r = (int) (Math.random() * 42);
					}

					land = laenderVerwaltung.getLandByNumber(r);
					land.setBesitzer(spieler[j]);
					land.setAnzahlEinheiten(land.getAnzahlEinheiten()+1);
				}
				IO.println(land.getName()+"("+land.getAnzahlEinheiten()+")"+ " gehört "+spieler[j].getName());
			}
		}
	}

	private boolean spielZuEnde() {
		// TODO Überprüfungsfunktion erstellen
		return false;
	}
}
