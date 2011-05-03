public class Risiko {

	public Risiko() {

		// Länder && Spieler initialisieren
		Laenderverwaltung laenderVerwaltung = new Laenderverwaltung();
		Spielerverwaltung spielerVerwaltung = new Spielerverwaltung();

		initialisierung(laenderVerwaltung, spielerVerwaltung);

		while (!spielZuEnde()) {
			Spielzyklus spielzyklus = new Spielzyklus(
					spielerVerwaltung.getAktSpieler(), laenderVerwaltung);
			spielerVerwaltung.naechsterSpieler();
		}
	}

	public static void main(String args[]) {
		Risiko risiko = new Risiko();
	}

	private void initialisierung(Laenderverwaltung laenderVerwaltung,
			Spielerverwaltung spielerVerwaltung) {

		// Start Armeen anhand der Anzahl der Spieler verteilen
		int startArmeen = 50 - ((spielerVerwaltung.getSpielerzahl() + 1) * 5);
		Spieler[] spieler = spielerVerwaltung.getSpieler();

		for (int i = 0; i < spielerVerwaltung.getSpielerzahl(); i++) {
			spieler[i].setReserveArmeen(startArmeen);
		}

		for (int i = 0; i < startArmeen; i++) {
			for (int j = 0; j < spielerVerwaltung.getSpielerzahl(); j++) {
				int r = (int) (Math.random() * 42);
				Land land = laenderVerwaltung.getLandByNumber(r);

				if (land.getBesitzer() == null) {
					land.setAnzahlEinheiten(land.getAnzahlEinheiten() + 1);
					land.setBesitzer(spieler[j]);
					spieler[j].setReserveArmeen(spieler[j].getReserveArmeen()-1);
				} else {
				}
			}
		}
	}

	private boolean spielZuEnde() {
		// TODO Überprüfungsfunktion erstellen
		return false;
	}
}
