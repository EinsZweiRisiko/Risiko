import java.util.ArrayList;
import java.io.*;
/**
 * 
 * @author Timur
 *
 */
public class Wuerfel {

	ArrayList <Integer> ang = new ArrayList<Integer>();
	ArrayList <Integer> def = new ArrayList<Integer>(); 

	//Anzahl angreifender Soldaten
	int angAnzahl;
	int defAnzahl;

	// die angreifenden Würfel mit ihren wertigen absteigen sortiert
	int angEins = 0;
	int angZwei = 0;
	
	int defEins = 0;
	int defZwei = 0;

	// Gesamten Einheiten auf der Quell bzw. Zielländern
	int angGesamt = 10;
	int defGesamt = 10;

	//Angriffzüge
	int angriffZug;
	int maxAngriffZug;

	//Ziell und Quellland
	Land zielLand;
	Land quellLand;

	//Konstruktur kriegt die Anzahl der Anfreifenden bzw. Verteidigenden Einheiten rüber
	public Wuerfel(int angAnzahl, int defAnzahl, Land zielLand, Land quellLand ) {
		this.angAnzahl = angAnzahl;
		this.defAnzahl = defAnzahl;
		this.zielLand = zielLand;
		this.quellLand = quellLand;
		fight();
	}

	// würfelt die Zahlen aus.
	public static ArrayList getDice(int anzahl) { // anzahl der angreifenden varriert
		ArrayList <Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i <= anzahl - 1; i++) {
			dice.add(i, (int)((Math.random()) * 6 + 1));
		}
		return dice;
	}

	/**b
	 * Berechnet die größte Zahl in einer Liste
	 * 
	 * @param würfel Eine Liste aus gewürfelten Zahlen
	 * @return Größte Zahl aus den Würfelergebnissen eines Spielers
	 */
	public int getGroessteZahl(ArrayList <Integer> würfel) {
		int maximum = würfel.get(0);
		boolean in = false;
		int i = 1;
		while(i < würfel.size()){
			if (würfel.get(i) > maximum) {
				maximum = würfel.get(i);
				würfel.remove(i);
				in = true;
			}
			i++;
		}
		if(in == false) { würfel.remove(0); }
		return maximum;
	}

	public void fight() {
		/*Benutzereingabe muss überprüft werden mit Einheiten Anzahl (angGesamt und defGesamt) auf dem Angriff bzw Zielland
		 *  angAnzahl darf nur 2 oder 3 sein wenn eine Einheit auf dem Quellland bleibt
		 *  bei Defense land muss min. eine Einheite vorhanden sein heisst die Zahl darf nur 1 oder 2 sein.
		 */

		//hier werden die würfel Anzahl festgelegt je nach anzahl der Einheiten auf dem Land
		ang = getDice(angAnzahl);
		def = getDice(defAnzahl);

		/* generiert die größten Zahlen im Integer für den Angriff es gilt:
   für den Angriff:
   angAnzahl = 2 dann nur eine Zahl zum kämpfen
   angAnzahl = 3 dann zwei Zahlen zum kämpfen

   für die Defensive:
   defAnzahl = 1 dann eine Zahl zum verteidigen
   defAnzahl = 2 dann zwei Zahlen zum verteidigen
		 */

		// Kampfgeschehen ausrechnen der Verluste
		if(angAnzahl == 1) {
			angEins = getGroessteZahl(ang);
			angriffZug++;
			System.out.println("------ RUNDE ("+ angriffZug +") -------- \r\n");

			// 1 gegen 1
			if(defAnzahl == 1) {
				defEins = getGroessteZahl(def);

				if(angEins > defEins) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff:" + angEins +" schlägt Defensive: "+ defEins);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				} else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten()-1);
					System.out.println("Defensive:" +defEins+" schlägt Angriff: "+angEins);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}
			}

			// 1 gegen 2
			if(defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);

				if(angEins > defEins) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angEins+" schlägt Defensive: "+defEins);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				}else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Defensive: "+defEins+" schlägt Angriff: "+angEins);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}
			}
		}

		if(angAnzahl == 2 ) { // mit Abfrage wegen einem Soldat auf dem Quellland
			angEins = getGroessteZahl(ang);
			angZwei = getGroessteZahl(ang);
			angriffZug++;
			System.out.println("------ RUNDE ("+angriffZug+") -------- \r\n");
			// 2 gegen 1
			if(defAnzahl == 1) {
				defEins = getGroessteZahl(def);

				if(angEins > defEins) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angEins+" schlägt Defensive: "+defEins);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				}else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angEins+" schlägt Defensive: "+defEins);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				}
			}
			// 2 gegen 2
			if(defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);

				// ersten Würfel treten gegeneinander an
				if(angEins > defEins) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: " +angEins+" schlägt Defensive: "+defEins);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				} else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Defensive: "+defEins+" schlägt Angriff: "+angEins);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}

				// zweiten Würfel treten gegen einander an
				if(angZwei > defZwei) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angZwei+" schlägt Defensive: "+defZwei);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				}else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Defensive:" +defZwei+" schlägt Angriff: "+angZwei);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}
			}
		} else {
			//System.out.println("Sei müssen min. 1 Soldaten auf dem Angreifenden Land lassen. Geben sie eine neue Anzahl an!");
			//TODO : neu eingabe der anzahl der Soldaten FUNKTION
		}

		if(angAnzahl == 3) {
			angEins = getGroessteZahl(ang);
			angZwei = getGroessteZahl(ang);
			angriffZug++;
			System.out.println("------ RUNDE ("+angriffZug+") -------- \r\n");

			// 2 gegen 1
			if(defAnzahl == 1) {
				defEins = getGroessteZahl(def);

				if(angEins > defEins) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angEins+" schlägt Defensive: "+defEins);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				}else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Defensive: "+defEins+" schlägt Angriff: "+angEins);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}
			}

			// 2 gegen 2
			if(defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);
				// ersten Würfel treten gegeneinander an
				if(angEins > defEins) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angEins+" schlägt Defensive: "+defEins);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				} else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Defensive: "+defEins+" schlägt Angriff: "+angEins);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}

				// zweiten Würfel treten gegen einander an
				if(angZwei > defZwei) {
					//Zielland verliert Einheit
					zielLand.setAnzahlEinheiten(zielLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angZwei+" schlägt Defensive: "+defZwei);
					System.out.println(zielLand.getName() + " verliert 1 Einheit und hat noch: "+ zielLand.getAnzahlEinheiten() +"Einheiten übrig");
				}else {
					//Quellland verliert Einheit
					quellLand.setAnzahlEinheiten(quellLand.getAnzahlEinheiten() -1);
					System.out.println("Angriff: "+angZwei+" schlägt Defensive: "+defZwei);
					System.out.println(quellLand.getName() + " verliert 1 Einheit und hat noch: "+ quellLand.getAnzahlEinheiten() +"Einheiten übrig");
				}
			}
		}
	}
}
