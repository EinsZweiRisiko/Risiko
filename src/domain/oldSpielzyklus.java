package domain;


import java.util.ArrayList;

import ui.cli.IO;


import valueobjects.Territory;
import valueobjects.Player;


/**
 * Spielzyklus-Klasse
 * 
 * @author Hendrik
 */
public class oldSpielzyklus {

	/*
	 * Konstruktor dem aktueller Spieler und Laenderverwaltung zum ermitteln
	 * aller Felder übergeben wird. TODO FRAGE: IST ES SINNVOLL ALLES IM
	 * KONSTRUKTOR ZU HABEN ODER SOLLTEN FUNKTIONEN WIE einheitenSetzen(),
	 * angreifen() und verteilen() genutzt werden?.
	 */
	public oldSpielzyklus(Player spieler, TerritoryManager laenderVerwaltung) {
		IO.println("\n" + "---------------------------" + "\n" + "Spielzyklus gestartet" + "\n");
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

			Territory land = laenderVerwaltung.getLandByName(zielLand);

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
					+ land.getName() + "." + "\n" + "Du hast noch " + spieler.getReserveArmeen() + " Armeen zu verteilen");
		}

		IO.println("Alle Armeen wurden verteilt" + "\n");

		/*
		 * Sind alle Armeen verteilt beginnt die Angriffsphase. Der Spieler wird
		 * gefragt ob er angreifen will. Sollte dies nicht der Fall sein erfolgt
		 * eine Sicherheitsabfrage ob er sich sicher ist.
		 */
		char angriffEntscheidung = IO.readChar("Willst du angreifen?(j/n): ");

		char angriffEntscheidung2 = 'x';

		if (angriffEntscheidung == 'n') {
			angriffEntscheidung2 = IO
					.readChar("Willst wirklich nicht angreifen?(j/n): ");
		}

		/*
		 * Falls der Spieler sich für den Angriff entschieden hat wird er
		 * gefragt von welchem Land er angreifen müchte. Gehört ihm dieses nicht
		 * so wird er aufgefordert seine Eingabe zu wiederholen.
		 */
		while (angriffEntscheidung == 'j' || angriffEntscheidung2 == 'n') {
			angriff(laenderVerwaltung,spieler);
			angriffEntscheidung = IO.readChar("Willst du weiter angreifen?(j/n): ");
		}

		// Bei Sieg einrücken

		// Einheiten verschieben
		// Analog zu Angriff(siehe Oben)

		char verschiebenEntscheidung = IO
				.readChar("Willst du Einheiten verschieben?(j/n): ");

		char verschiebenEntscheidung2 = 'x';

		if (verschiebenEntscheidung == 'n') {
			verschiebenEntscheidung2 = IO
					.readChar("Willst wirklich keine Einheiten verschieben?(j/n): ");
		}

		if (verschiebenEntscheidung == 'j' || verschiebenEntscheidung2 == 'n') {

			String quellLandString = IO
					.readString("Aus welchem Land möchtest Einheiten verschieben?: ");
			Territory quellLand = laenderVerwaltung.getLandByName(quellLandString);

			while (!quellLand.getBesitzer().equals(spieler)) {
				quellLandString = IO
						.readString("Das Land gehört dir nicht. Neue Eingabe: ");
				quellLand = laenderVerwaltung.getLandByName(quellLandString);
			}

			ArrayList<Territory> nachbarn = new ArrayList<Territory>();
			nachbarn = quellLand.getNachbarn();

			IO.println("Mögliche Länder:");

			for (int i = 0; i < nachbarn.size(); i++) {
				if (nachbarn.get(i).getBesitzer().equals(spieler)) {
					IO.println(nachbarn.get(i).getName());
				}
			}

			String zielLandString = IO
					.readString("In welches dieser Länder möchtest du die Armeen schicken?: ");
			Territory zielLand = laenderVerwaltung.getLandByName(zielLandString);

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
	private void angriff(TerritoryManager laenderVerwaltung, Player spieler){

		String quellLandString = IO
				.readString("Von wo willst du angreifen?: ");
		Territory quellLand = laenderVerwaltung.getLandByName(quellLandString);

		while (!quellLand.getBesitzer().equals(spieler) || quellLand.getAnzahlEinheiten() == 1) {
			quellLandString = IO
					.readString("Das Land gehört dir nicht oder es stehen nicht genug Armeen zur Verfügung. Neue Eingabe: ");
			quellLand = laenderVerwaltung.getLandByName(quellLandString);
		}

		/*
		 * War die Eingabe gültig so wird gefragt welches Land er angreifen
		 * will Hierbei werden die Nachbarn des soeben erstellten Objekts
		 * abgefragt und gelistet. Außerdem wird überprüft ob das Land in
		 * feindlichem Besitz ist.
		 */

		ArrayList<Territory> nachbarn = new ArrayList<Territory>();
		nachbarn = quellLand.getNachbarn();

		IO.println("Mögliche Ziele:");

		/*
		 * die for-Schleife durchläuft die Array-List "nachbarn". Die
		 * if-Bedingung fragt ab ob sich das Land in feindlichem Besitz
		 * befindet. Sollte dies der Fall sein so wird dieses als Ziel
		 * vorgeschlagen sowie der Besitzer kenntlich gemacht.
		 */
		for (int i = 0; i < nachbarn.size(); i++) {
			if (!nachbarn.get(i).getBesitzer().equals(spieler)) {
				IO.println(nachbarn.get(i).getName() + " ("
						+ nachbarn.get(i).getBesitzer().getName() +  "," + nachbarn.get(i).getAnzahlEinheiten() + " Armeen" + ")");
			}
		}

		String zielLandString = IO
				.readString("Welches Land willst du angreifen?: ");
		Territory zielLand = laenderVerwaltung.getLandByName(zielLandString);

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
		BattleSystem wuerfel = new BattleSystem(angriffsArmeen, verteidigungsArmeen,
				zielLand, quellLand);
	}
	}
