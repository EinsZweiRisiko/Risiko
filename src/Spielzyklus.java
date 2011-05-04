import java.util.ArrayList;

/**
 * Spielzyklus-Klasse
 * 
 * @author Hendrik
 */
public class Spielzyklus {

	/**
	 * Konstruktor dem aktueller Spieler und Laenderverwaltung zum ermitteln
	 * aller Felder übergeben wird.
	 */
	public Spielzyklus(Spieler spieler, Laenderverwaltung laenderVerwaltung) {
		IO.println("\n" + "---------------------------" + "\n"
				+ "Spielzyklus gestartet" + "\n");
		IO.println(spieler.getName() + " an der Reihe");

		// Verteilloop starten
		verteileArmeen(spieler, laenderVerwaltung);

		// Angriffsloop starten
		angriff(spieler, laenderVerwaltung);

		// Einheiten verschieben
		einheitenVerschieben(spieler, laenderVerwaltung);

	}

	/**
	 * 
	 * @param spieler
	 *            aktueller Spieler
	 * @param laenderVerwaltung
	 *            wird benötigt um Länder bereit zu stellen
	 */
	private void verteileArmeen(Spieler spieler,
			Laenderverwaltung laenderVerwaltung) {

		spieler.setReserveArmeen(3);
		IO.println("\n" + "Dir stehen " + spieler.getReserveArmeen()
				+ " Armeen zur verfügung." + " Verteile deine Armeen.");

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
				land = laenderVerwaltung.getLandByName(zielLand);
			}

			// Nachdem auch die Eingabe des Landes gültig war wird die Anzahl
			// der Armeen in betreffendem Land um die eingegebene Anzahl erhöht.
			land.setAnzahlEinheiten(land.getAnzahlEinheiten() + Armeen);
			spieler.setReserveArmeen(spieler.getReserveArmeen() - Armeen);
			IO.println("Es wurden " + Armeen + " Armeen in " + land.getName()
					+ " platziert." + "\n" + "Es befinden sich nun "
					+ land.getAnzahlEinheiten() + " Armeen in "
					+ land.getName() + "." + "\n" + "Du hast noch "
					+ spieler.getReserveArmeen() + " Armeen zu verteilen");
		}
		IO.println("Alle Armeen wurden verteilt" + "\n");
	}

	/**
	 * 
	 * @param spieler
	 *            aktueller Spieler
	 * @param laenderVerwaltung
	 *            wird benötigt um Länder bereit zu stellen
	 */
	private void einheitenVerschieben(Spieler spieler,
			Laenderverwaltung laenderVerwaltung) {
		char verschiebenEntscheidung = IO.readChar("\n"
				+ "---------------------------" + "\n"
				+ "Willst du Einheiten verschieben?(j/n): ");

		if (verschiebenEntscheidung == 'n') {
			verschiebenEntscheidung = IO
					.readChar("Bist du dir sicher, dass du keine Einheiten verschieben willst?"
							+ "\n" + "Willst du Einheiten verschieben?(j/n): ");
		}

		if (verschiebenEntscheidung == 'j') {

			String quellLandString = IO
					.readString("Aus welchem Land möchtest Einheiten verschieben?: ");
			Land quellLand = laenderVerwaltung.getLandByName(quellLandString);

			while (!quellLand.getBesitzer().equals(spieler)) {
				quellLandString = IO
						.readString("Das Land gehört dir nicht. Neue Eingabe: ");
				quellLand = laenderVerwaltung.getLandByName(quellLandString);
			}

			ArrayList<Land> nachbarn = new ArrayList<Land>();
			nachbarn = quellLand.getNachbarn();

			IO.println("Mögliche Länder:");

			for (int i = 0; i < nachbarn.size(); i++) {
				if (nachbarn.get(i).getBesitzer().equals(spieler)) {
					IO.println(nachbarn.get(i).getName());
				}
			}

			String zielLandString = IO
					.readString("In welches dieser Länder möchtest du die Armeen schicken?: ");
			Land zielLand = laenderVerwaltung.getLandByName(zielLandString);

			while (!zielLand.getBesitzer().equals(spieler)
					|| !zielLand.istNachbar(quellLand)) {
				zielLandString = IO
						.readString("Das Land gehört dir nicht oder ist nicht Benachbart. Neue Eingabe: ");
				zielLand = laenderVerwaltung.getLandByName(zielLandString);
			}

			IO.println("Dir stehen " + (quellLand.getAnzahlEinheiten() - 1)
					+ " Armeen zur verfügung");

			int verschArmeen = IO.readInt("Wieviele Armeen?: ");

			while (verschArmeen < (quellLand.getAnzahlEinheiten() - 1)) {
				IO.println("Armeen reichen nicht aus es sind nur noch "
						+ (quellLand.getAnzahlEinheiten() - 1)
						+ " Armeen verfügbar");
				verschArmeen = IO.readInt("Anzahl der Armeen: ");
			}

			quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten()
					- verschArmeen);
			zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten()
					+ verschArmeen);

			IO.println("Es wurden " + verschArmeen + " Armeen von "
					+ quellLand.getName() + " nach " + zielLand.getName()
					+ " verschoben." + "\n" + "Es befinden sich nun "
					+ zielLand.getAnzahlEinheiten() + " Armeen in "
					+ zielLand.getName() + " und "
					+ quellLand.getAnzahlEinheiten() + " Armeen in "
					+ quellLand.getName() + ".");
		}
	}

	/**
	 * 
	 * @param spieler
	 *            aktueller Spieler
	 * @param laenderVerwaltung
	 *            wird benötigt um Länder bereit zu stellen
	 */
	private void angriff(Spieler spieler, Laenderverwaltung laenderVerwaltung) {

		char angriffEntscheidung = IO.readChar("Willst du angreifen?(j/n): ");

		if (angriffEntscheidung == 'n') {
			angriffEntscheidung = IO
					.readChar("Bist du dir sicher das du nicht anreifen willst?"
							+ "\n" + "Willst du angreifen?(j/n): ");
		}

		// Angriffsloop
		while (angriffEntscheidung == 'j') {

			String quellLandString = IO
					.readString("Von wo willst du angreifen?: ");
			Land quellLand = laenderVerwaltung.getLandByName(quellLandString);

			while (!quellLand.getBesitzer().equals(spieler)
					|| quellLand.getAnzahlEinheiten() == 1) {
				quellLandString = IO
						.readString("Das Land gehört dir nicht oder es stehen nicht genug Armeen zur Verfügung. Neue Eingabe: ");
				quellLand = laenderVerwaltung.getLandByName(quellLandString);
			}

			/*
			 * War die Eingabe gültig so wird gefragt welches Land er angreifen
			 * will Hierbei werden die Nachbarn des soeben erstellten Objekts
			 * abgefragt und gelistet. Außerdem wird überprüft ob das Land in
			 * feindlichem Besitz ist. die for-Schleife durchläuft die
			 * Array-List "nachbarn". Die if-Bedingung fragt ab ob sich das Land
			 * in feindlichem Besitz befindet. Sollte dies der Fall sein so wird
			 * dieses als Ziel vorgeschlagen sowie der Besitzer kenntlich
			 * gemacht.
			 */

			IO.println("Mögliche Ziele:");
			ArrayList<Land> nachbarn = new ArrayList<Land>();
			nachbarn = quellLand.getNachbarn();

			for (int i = 0; i < nachbarn.size(); i++) {
				if (!nachbarn.get(i).getBesitzer().equals(spieler)) {
					IO.println(nachbarn.get(i).getName() + " ("
							+ nachbarn.get(i).getBesitzer().getName() + ","
							+ nachbarn.get(i).getAnzahlEinheiten() + " Armeen"
							+ ")");
				}
			}

			String zielLandString = IO
					.readString("Welches Land willst du angreifen?: ");
			Land zielLand = laenderVerwaltung.getLandByName(zielLandString);

			// Wenn das Land dem aktuellen Spieler gehört oder die Länder nicht
			// benachbart sind ist die Eingabe ungültig und die Eingabe muss
			// wiederholt werden.
			while (zielLand.getBesitzer().equals(spieler)
					|| !zielLand.istNachbar(quellLand)) {
				zielLandString = IO
						.readString("Das Land gehört dir. Neue Eingabe: ");
				zielLand = laenderVerwaltung.getLandByName(zielLandString);
			}

			// Abfrage der Anzahl der Armeen die Angreifen sollen
			int angriffsArmeen = IO.readInt("Wieviele Armeen sollen "
					+ zielLand.getName() + "(" + zielLand.getAnzahlEinheiten()
					+ " Armeen)" + " angreifen?(1-3)");

			/*
			 * Sollte diese nicht gültig sein wird der Anwender aufgefordert die
			 * Eingabe zu wiederholen. Die Eingabe muss zwischen 1 udn 3 liegen
			 * und es müssen genug EInhetien im Land Verfügbar sein. dabei ist
			 * zu beachten, dass mindestens eine Einheit im Land bleiben muss.
			 */
			while (angriffsArmeen < 1 || angriffsArmeen > 3) {
				angriffsArmeen = IO
						.readInt("Du kannst nur mit 1-3 Armeen angreifen wiederhole die Eingabe!:");
				if (angriffsArmeen > (quellLand.getAnzahlEinheiten() - 1)) {
					angriffsArmeen = IO
							.readInt("Du hast nicht genug Einheiten. Es sind "
									+ quellLand.getAnzahlEinheiten()
									+ "verfügbar" + "\n" + "Neue Eingabe:");
				}
			}

			// Abfrage der Armeen die Verteidigen können Maximale Anzahl == 2
			int verteidigungsArmeen = zielLand.getAnzahlEinheiten();

			if (verteidigungsArmeen >= 2) {
				verteidigungsArmeen = 2;
			} else {
				verteidigungsArmeen = 1;
			}

			/*
			 * erstellen eines Wuerfels. Dieser entscheidet den Kampf und
			 * verändert die Anzahl der Einheiten in den jeweiligen Ländern.
			 */

			int armeenVorher = quellLand.getAnzahlEinheiten();

			Wuerfel wuerfel = new Wuerfel(angriffsArmeen, verteidigungsArmeen,
					zielLand, quellLand);

			if (zielLand.getAnzahlEinheiten() == 0) {
				int armeenNachher = angriffsArmeen
						- (armeenVorher - quellLand.getAnzahlEinheiten());
				zielLand.setAnzahlEinheiten(armeenNachher);
				quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten()
						- armeenNachher);
				zielLand.setBesitzer(spieler);
				IO.println(spieler.getName() + " rückt in "
						+ zielLand.getName() + " ein und besetzt es mit "
						+ armeenNachher + " Armeen.");
			}

			angriffEntscheidung = IO.readChar("\n"
					+ "Willst du weiter angreifen?(j/n): ");
		}
	}

}
