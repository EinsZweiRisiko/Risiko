/**
 * Spielzyklus-Klasse
 * 
 * @author Hendrik
 */
public class Spielzyklus {

	/*
	 * Konstruktor dem aktueller Spieler und Laenderverwaltung zum ermitteln
	 * aller Felder übergeben wird. TODO FRAGE: IST ES SINNVOLL ALLES IM
	 * KONSTRUKTOR ZU HABEN ODER SOLLTEN FUNKTIONEN WIE einheitenSetzen(),
	 * angreifen() und verteilen() genutzt werden?.
	 */
	public Spielzyklus(Spieler spieler, Laenderverwaltung laenderVerwaltung) {
		IO.println("Spielzyklus gestartet");
		IO.println(spieler.getName() + " an der Reihe");

		/*
		 * Jede Runde bekommt jeder Spieler 3 Armeen. TODO Alle zusätzlichen
		 * Armeen die durch Eroberung von Kontinenten und Ländern fehlen noch!
		 */
		spieler.setReserveArmeen(3);

		/*
		 * Wenn der Spieler noch Reserve-Armeen zur Verfügung hat so soll er
		 * diese platzieren. Versucht er mehr Armeen zu setzen als er besitzt,
		 * so wird er nochmals darauf hingewiesen und solange um Korrektur
		 * gebeten bis er eine gültige Anzahl angegeben hat. Dies geschieht
		 * solange bis alle Armeen gesetzt wurden.
		 */
		IO.println("Dir stehen " + spieler.getReserveArmeen()
				+ " Armeen zur verfügung");

		while (spieler.getReserveArmeen() > 0) {
			int Armeen;
			Armeen = IO.readInt("Anzahl der Armeen: ");

			while (Armeen > spieler.getReserveArmeen()) {
				IO.println("Armeen reichen nicht aus es sind nur noch "
						+ spieler.getReserveArmeen() + " Armeen verfügbar");
				Armeen = IO.readInt("Anzahl der Einheiten: ");
			}

			// Nachdem die Anzahl gültig war wird abgefragt auf welches Feld die
			// Armeen gesetzt werden sollen.
			String zielLand = IO.readString("Name des Landes Eingeben: ");

			Land land = laenderVerwaltung.getLandByName(zielLand);

			// Sollte das Land nicht dem Spieler gehören so wird er aufgefordert
			// seine Eingabe zu korrigieren.
			while (!land.getBesitzer().equals(spieler)) {
				zielLand = IO
						.readString("Das Land gehört dir nicht. Neue Eingabe: ");
			}

			// Nachdem auch die Eingabe des Landes gültig war wird die Anzahl
			// der Armeen in betreffendem Land um die eingegebene Anzahl erhöht.
			land.setAnzahlEinheiten(land.getAnzahlEinheiten() + Armeen);
		}

		IO.println("Alle Armeen wurden verteilt");

		/*
		 * Sind alle Armeen verteilt beginnt die Angriffsphase. Der Spieler wird
		 * gefragt ob er angreifen will. Sollte dies nicht der Fall sein erfolgt
		 * eine Sicherheitsabfrage ob er sich sicher ist.
		 */
		String angriffEntscheidung = IO
				.readString("Willst du angreifen?(ja/nein): ");
		String angriffEntscheidung2 = "";

		if (angriffEntscheidung != "ja") {
			angriffEntscheidung2 = IO
					.readString("Willst wirklich nicht angreifen?(ja/nein): ");
		}

		/*
		 * Falls der Spieler sich für den Angriff entschieden hat wird er
		 * gefragt von welchem Land er angreifen möchte. Gehört ihm dieses nicht
		 * so wird er aufgefordert seine Eingabe zu wiederholen.
		 */
		if (angriffEntscheidung == "ja" || angriffEntscheidung2 == "nein") {

			String quellLandString = IO
					.readString("Von wo willst du angreifen?: ");
			Land quellLand = laenderVerwaltung.getLandByName(quellLandString);

			while (!quellLand.getBesitzer().equals(spieler)) {
				quellLandString = IO
						.readString("Das Land gehört dir nicht. Neue Eingabe: ");
			}

			/*
			 * War die Eingabe gültig so wird gefragt welches Land er angreifen
			 * will TODO Liste möglicher Angriffsziele angeben!
			 */
			String zielLandString = IO
					.readString("Welches Land willst du angreifen?: ");
			Land zielLand = laenderVerwaltung.getLandByName(zielLandString);

			while (zielLand.getBesitzer().equals(spieler)) {
				zielLandString = IO
						.readString("Das Land gehört dir. Neue Eingabe: ");
			}

		}

		// TODO
		// Verteidigung
		// Auswertung Kampf
		// Bei Sieg einrücken
		// Einheiten verschieben
	}
}
