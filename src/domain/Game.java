package domain;

import valueobjects.Spieler;

public class Game {
	
	
	private Spielerverwaltung spielerverwaltung;
	private Laenderverwaltung laenderverwaltung;

	public Game() {
		// Laenderverwaltung erstellen
		laenderverwaltung = new Laenderverwaltung();
		// Spielerverwaltung erstellen (Spielerzahl, namen, farben)
		spielerverwaltung = new Spielerverwaltung();
		// Anfangsrunde
		spielFigurenPlatzieren();
		
	}
	
	private void spielFigurenPlatzieren() {
		// TODO Auto-generated method stub
		// Entweder zuf�llig platzieren oder Spieler entscheiden lassen
		
	}

	public boolean ended() {
		// TODO Auto-generated method stub
		return false;
	}

	public void run() {
		// Herausfinden, welcher Spieler dran ist
		Spieler spieler = spielerverwaltung.welcherSpielerIstDran();
		
		/*
		   1. Einheiten Reserve
				L�nderanzahl/3 aber mindestens 3
				Besetzte Kontinente
				evtl. Karten eintauschen
			2. Einheiten verteilen
			
			3. beliebig oft K�mpfen (solange er irgendwo mehr als eine Einheit hat)
			Beim Kampf bei einem Land muss mindestens 1 Soldat auif dem Quellland bleiben und der Kampf erfolgt �ber W�rfel.
			
				Es m�ssen mindestens so viele Einheiten mitgenommen werden, wie gek�mpft haben
				Er kann nach einem gewonnen KAmpf alle bis auf eine Einheit mitnehmen.
			
			4. Einheiten verschieben zwischen angrenzenden L�nder, 
				Es d�rfen nur Einheiten verschoben werden, die nicht gek�mpf
				Ansonsten k�nnen beliebig viele Einheiten verschoben werden
		 */
		
		
		// Wie viel Verst�rkung?
		int supply = 0;
		
		// Wie viele Einheiten bekommt der Spieler durch eroberte L�nder?
		supply += spieler.getAnzahlLaender()/3;
		// Der Spieler bekommt mindestens 3 Einheiten
		if (supply < 3) {
			supply = 3;
		}
		
		// Bonuseinheiten durch eroberte Kontinente
		supply += spieler.getContinentBonus();
		
		// Bonuseinheiten durch Karten SP�TER, weil kein interface vorhanden
		if (spieler.useBonusCards()) {
			supply += getCardBonus();
		}
 
		// Einheiten setzen lassen
		spieler.placeUnits(supply);
		
		// Angreifen
		spieler.attack();
		
		// Einheiten verschieben
		spieler.moveUnits();
	}

	private int getCardBonus() {
		// TODO Kartenbonus berechnen
		return 0;
	}

	public Spieler getGewinner() {
		// TODO Auto-generated method stub
		
	}
	
}
