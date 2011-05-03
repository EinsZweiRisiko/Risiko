public class Spielzyklus {

	public Spielzyklus(Spieler spieler, Laenderverwaltung laenderVerwaltung) {
		IO.println("Spielzyklus gestartet");
		IO.println(spieler.getName() + " an der Reihe");

		// Einheiten verteilen

		IO.println("Dir stehen " + spieler.getReserveArmeen()
				+ " Armeen zur verfügung");

		while (spieler.getReserveArmeen() > 0) {
			int Armeen;
			Armeen = IO.readInt("Anzahl der Armeen: ");

			while (Armeen > spieler.getReserveArmeen()) {
				IO.println("Armeen reichen nicht aus es sind nur noch "
						+ spieler.getReserveArmeen() + " Armeen verfügbar");
				int reserveArmee = IO.readInt("Anzahl der Einheiten: ");
			}

			String zielLand = IO.readString("Name des Landes Eingeben: ");

			Land land = laenderVerwaltung.getLandByName(zielLand);

			while (!land.getBesitzer().equals(spieler)) {
				zielLand = IO
						.readString("Das Land gehört dir nicht. Neue Eingabe: ");
			}

			land.setAnzahlEinheiten(land.getAnzahlEinheiten() + Armeen);
		}
		
		IO.println("Alle Armeen wurden verteilt");
		
		// Angreifen

		String angriffEntscheidung = IO.readString("Willst du angreifen?(ja/nein): ");
		String angriffEntscheidung2 = "";

		if (angriffEntscheidung != "ja") {
			angriffEntscheidung2 = IO
					.readString("Willst wirklich nicht angreifen?(ja/nein): ");
		}
		
		if(angriffEntscheidung == "ja" || angriffEntscheidung2 == "nein"){
			
			String quellLandString = IO.readString("Von wo willst du angreifen?: ");
			Land quellLand = laenderVerwaltung.getLandByName(quellLandString);
			
			while (!quellLand.getBesitzer().equals(spieler)) {
				quellLandString = IO
						.readString("Das Land gehört dir nicht. Neue Eingabe: ");
			}
			
			String zielLandString = IO.readString("Welches Land willst du angreifen?: ");
			Land zielLand = laenderVerwaltung.getLandByName(zielLandString);
			
			while (zielLand.getBesitzer().equals(spieler)) {
				zielLandString = IO
						.readString("Das Land gehört dir. Neue Eingabe: ");
			}
			
		}

		// Verteidigung
		// Auswertung Kampf
		// Bei Sieg einrücken
		// Einheiten verschieben
	}
}
