package domain;
import java.util.ArrayList;
import java.io.*;

import valueobjects.Land;


/**
 * 
 * @author Timur
 * 
 */
public class oldWuerfel {

	ArrayList<Integer> ang = new ArrayList<Integer>();
	ArrayList<Integer> def = new ArrayList<Integer>();

	// Anzahl angreifender Soldaten
	int angAnzahl;
	int defAnzahl;

	// die angreifenden W�rfel mit ihren wertigkeiten absteigen sortiert
	int angEins = 0;
	int angZwei = 0;
	// die verteidigenden W�rfel mit ihren wertigkeiten absteigen sortiert
	int defEins = 0;
	int defZwei = 0;

	// Angriffz�ge
	int angriffZug;
	int maxAngriffZug;

	// Ziell und Quellland
	Land zielLand;
	Land quellLand;

	// Angriffe wieviele Angriffe werden in einem Angriff get�tigt
	int angriffe = 0;

	// Konstruktur kriegt die Anzahl der Anfreifenden bzw. Verteidigenden Einheiten
	// Kontruktur bekommt au�erdem das Ziel und Quellland als Objekte
	
	public oldWuerfel(int angAnzahl, int defAnzahl, Land zielLand, Land quellLand) {
		this.angAnzahl = angAnzahl;
		this.defAnzahl = defAnzahl;
		this.zielLand = zielLand;
		this.quellLand = quellLand;
		fight();
	}

	/**
	 * Zuf�llige berechnung der W�rfel wertigkeiten
	 * 
	 * @param anzahl Anzahl der zu w�rfelnden W�rfel
	 *
	 * @return ArrayList als Integer der gew�rfelten zahlen
	 */
	public static ArrayList getDice(int anzahl) {
		// varriert
		ArrayList<Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i <= anzahl - 1; i++) {
			dice.add(i, (int) ((Math.random()) * 6 + 1));
		}
		return dice;
	}

	/**
	 * Berechnet die gr��te Zahl in einer Liste
	 * 
	 * @param w�rfel
	 *            Eine Liste aus gew�rfelten Zahlen
	 * @return Gr��te Zahl aus den W�rfelergebnissen eines Spielers
	 */
	public int getGroessteZahl(ArrayList<Integer> w�rfel) {
		int maximum = w�rfel.get(0);
		boolean in = false;
		int i = 1;
		while (i < w�rfel.size()) {
			if (w�rfel.get(i) > maximum) {
				maximum = w�rfel.get(i);
				w�rfel.remove(i);
				in = true;
			}
			i++;
		}
		if (in == false) {
			w�rfel.remove(0);
		}
		return maximum;
	}

	public void fight() {
		/*
		 * Benutzereingabe muss �berpr�ft werden mit Einheiten Anzahl (angGesamt
		 * und defGesamt) auf dem Angriff bzw Zielland angAnzahl darf nur 2 oder
		 * 3 sein wenn eine Einheit auf dem Quellland bleibt bei Defense land
		 * muss min. eine Einheite vorhanden sein heisst die Zahl darf nur 1
		 * oder 2 sein.
		 */

		// hier werden die w�rfel Anzahl festgelegt je nach anzahl der Einheiten
		// auf dem Land
		ang = getDice(angAnzahl);
		def = getDice(defAnzahl);

		/*
		 * generiert die gr��ten Zahlen im Integer f�r den Angriff es gilt: f�r
		 * den Angriff: angAnzahl = 2 dann nur eine Zahl zum k�mpfen angAnzahl =
		 * 3 dann zwei Zahlen zum k�mpfen
		 * 
		 * f�r die Defensive: defAnzahl = 1 dann eine Zahl zum verteidigen
		 * defAnzahl = 2 dann zwei Zahlen zum verteidigen
		 */

		// Kampfgeschehen ausrechnen der Verluste
		if (angAnzahl == 1) {
			angEins = getGroessteZahl(ang);
			angriffZug++;
			System.out.println("------ RUNDE (" + angriffZug + ") -------- \r\n");
			
			// 1 gegen 1
			if (defAnzahl == 1) {
				defEins = getGroessteZahl(def);

				if (angEins > defEins) {
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}
			}

			// 1 gegen 2
			if (defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);

				if (angEins > defEins) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}
			}
		}

		if (angAnzahl == 2) { // mit Abfrage wegen einem Soldat auf dem
			// Quellland
			angEins = getGroessteZahl(ang);
			angZwei = getGroessteZahl(ang);
			angriffZug++;
			
			System.out.println("------ RUNDE (" + angriffZug + ") -------- \r\n");
			// 2 gegen 1
			if (defAnzahl == 1) {
				defEins = getGroessteZahl(def);

				if (angEins > defEins) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}
			}
			// 2 gegen 2
			if (defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);

				// ersten W�rfel treten gegeneinander an
				if (angEins > defEins) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}

				// zweiten W�rfel treten gegen einander an
				if (angZwei > defZwei) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}
			}
		} else {
			// System.out.println("Sei m�ssen min. 1 Soldaten auf dem Angreifenden Land lassen. Geben sie eine neue Anzahl an!");
			// TODO : neu eingabe der anzahl der Soldaten FUNKTION
		}

		// Angriff 3
		if (angAnzahl == 3) {
			angEins = getGroessteZahl(ang);
			angZwei = getGroessteZahl(ang);
			angriffZug++;
			
			System.out.println("------ RUNDE (" + angriffZug + ") -------- \r\n");

			// 3 gegen 1
			if (defAnzahl == 1) {
				defEins = getGroessteZahl(def);
				if (angEins > defEins) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}
			}

			// 3 gegen 2
			if (defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);
				// ersten W�rfel treten gegeneinander an
				if (angEins > defEins) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}

				// zweiten W�rfel treten gegen einander an
				if (angZwei > defZwei) {
					// Zielland verliert Einheit
					eventMsgOffensive();
				} else {
					// Quellland verliert Einheit
					eventMsgDefensive();
				}
			}
		}
	}

	// Kampfgeschehen wird ausgegeben und die Einheiten werden auf den
	// jeweiligen L�ndern reduziert um 1
	public void eventMsgOffensive() {
		angriffe++;
		// Runter setzten der Einheiten
		zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() - 1);

		if (angriffe == 1) {
			System.out.println("Angriff1: " + angEins + " schl�gt Defensive1: "
											+ defEins);

			System.out.println(zielLand.getName() + "("
					+ zielLand.getBesitzer().getName()
					+ ") verliert 1 Einheit und " + zielLand.getName() + " ("
					+ zielLand.getBesitzer().getName() + ") hat noch: "
					+ zielLand.getAnzahlEinheiten() + " Einheiten �brig");
		} else {
			System.out.println("Angriff2: " + angZwei + " schl�gt Defensive2: "
					+ defZwei);

			System.out.println(zielLand.getName() + "("
					+ zielLand.getBesitzer().getName()
					+ ") verliert 1 Einheit und " + zielLand.getName() + " ("
					+ zielLand.getBesitzer().getName() + ") hat noch: "
					+ zielLand.getAnzahlEinheiten() + " Einheiten �brig");
		}

	}

	public void eventMsgDefensive() {
		// Anzahl der Angriffe die get�tigt werden
		angriffe++;
		// Runter setzten der Einheiten
		quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() - 1);

		if (angriffe == 1) {
			System.out.println("Defensive1: " + defEins + " schl�gt Offennsive1: "
					+ angEins);

			System.out.println(quellLand.getName() + "("
					+ quellLand.getBesitzer().getName()
					+ ") verliert 1 Einheit und " + quellLand.getName() + " ("
					+ quellLand.getBesitzer().getName() + ") hat noch: "
					+ quellLand.getAnzahlEinheiten() + " Einheiten �brig");
		} else {
			System.out.println("Defensive2: " + defZwei + " schl�gt Offensive2: "
					+ angZwei);

			System.out.println(quellLand.getName() + "("

					+ quellLand.getBesitzer().getName()
					+ ") verliert 1 Einheit und " + quellLand.getName() + " ("
					+ quellLand.getBesitzer().getName() + ") hat noch: "
					+ quellLand.getAnzahlEinheiten() + " Einheiten �brig");
		}
	}
}
