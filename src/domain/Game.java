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
		placeStartUnits();
	}
	
	private void placeStartUnits() {
		// TODO Auto-generated method stub
		// Entweder zufällig platzieren oder Spieler entscheiden lassen
		// Anfang start einheiten  bei 2 - 4 Spielern
		// 2 Spieler: 36
		// 3 Spieler: 35
		// 4 Spieler: 30
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
		
		
		// Wie viel Verstärkung?
		int supply = 0;
		
		// Wie viele Einheiten bekommt der Spieler durch eroberte Länder?
		supply += spieler.getAnzahlLaender()/3;
		// Der Spieler bekommt mindestens 3 Einheiten
		if (supply < 3) {
			supply = 3;
		}
		
		// Bonuseinheiten durch eroberte Kontinente
		supply += spieler.getContinentBonus();
		
		// Bonuseinheiten durch Karten SPÄTER, weil kein interface vorhanden
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
