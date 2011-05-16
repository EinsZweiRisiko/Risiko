package gamelogic;

public class Game {
	
	
	private Spielerverwaltung spielerverwaltung;
	private Laenderverwaltung laenderverwaltung;

	public Game() {
		// Laenderverwaltung erstellen
		// Spielerverwaltung erstellen (Spielerzahl, namen, farben)
		spielerverwaltung = new Spielerverwaltung();
		
		spielFigurenPlatzieren();
		
		
		
	}
	
	private void spielFigurenPlatzieren() {
		// TODO Auto-generated method stub
		// Entweder zufällig platzieren oder Spieler entscheiden lassen
		
	}

	public boolean ended() {
		// TODO Auto-generated method stub
		return false;
	}

	public void run() {
		// TODO Auto-generated method stub
		
		// Spielzyklus
		objects.Spieler spieler = spielerverwaltung.welcherSpielerIstDran();
		
		
		/*
		   1. Einheiten Reserve
				Länderanzahl/3 aber mindestens 3
				Besetzte Kontinente
				evtl. Karten eintauschen
			2. Einheiten verteilen
			
			3. beliebig oft Kämpfen (solange er irgendwo mehr als eine Einheit hat)
			Beim Kampf bei einem Land muss mindestens 1 Soldat auif dem Quellland bleiben und der Kampf erfolgt über Würfel.
			
				Es müssen mindestens so viele Einheiten mitgenommen werden, wie gekämpft haben
				Er kann nach einem gewonnen KAmpf alle bis auf eine Einheit mitnehmen.
			
			4. Einheiten verschieben zwischen angrenzenden Länder, 
				Es dürfen nur Einheiten verschoben werden, die nicht gekämpf
				Ansonsten können beliebig viele Einheiten verschoben werden
		 */
		
		
		// Wie viele Einheiten bekommt der Spieler durch eroberte Länder?
		
		int laenderAnzahl = spieler.getAnzahlLaender();
		// Daraus berechnen, wie viele Einheiten der Spieler bekommt
		
		
		int einheitenVerstaerkung = ; 
		
		
		// 
		if (spieler.mussKartenEintauschen()) {
			// Spieler fragen, welche er eintauschen möchte
			// Karten eintauschen
			// TODO 
			//einheitenVerstaerkung += 
		} else {
			// Hat der Spieler genug Karten zum eintauschen?
				// Spieler fragen, ob er Länderkarten einlösen möchte	
		}
 
		// Einheiten setzen lassen
		
		
		
		// Angreifen
		
	}

	public objects.Spieler getGewinner() {
		// TODO Auto-generated method stub
		
	}
	
}
