package domain;

// Import phase constants
import static domain.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import ui.UserInterface;
import ui.cli.CommandLineInterface;
import valueobjects.Spieler;

public class Game {

	private Spielerverwaltung spielerverwaltung;
	private Laenderverwaltung laenderverwaltung;
	private Spieler activePlayer;
	private UserInterface ui;
	private ArrayList<Integer> bonusAmountSteps;
	private Iterator<Integer> bonusAmountIter;
	
	
	public Game() {
		// Goldener Reiter
		bonusAmountSteps = new ArrayList<Integer>(Arrays.asList(4, 6, 8, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60));
		bonusAmountIter = bonusAmountSteps.iterator();
		
		// Laenderverwaltung erstellen
		laenderverwaltung = new Laenderverwaltung();
		
		// Spielerverwaltung erstellen (Spielerzahl, namen, farben)
		spielerverwaltung = new Spielerverwaltung();
		
		CommandLineInterface ui = new CommandLineInterface();
		
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
		activePlayer = spielerverwaltung.welcherSpielerIstDran();
		
		/*
		   1. Einheiten Reserve
				Länderanzahl/3 aber mindestens 3
				Besetzte Kontinente
				evtl. Karten eintauschen
			2. Einheiten verteilen
			
			3. beliebig oft Kämpfen (solange er irgendwo mehr als eine Einheit hat)
			Beim Kampf bei einem Land muss mindestens 1 Soldat auif dem Quellland bleiben und der Kampf erfolgt äber Wärfel.
			
				Es mässen mindestens so viele Einheiten mitgenommen werden, wie gekämpft haben
				Er kann nach einem gewonnen KAmpf alle bis auf eine Einheit mitnehmen.
			
			4. Einheiten verschieben zwischen angrenzenden Länder, 
				Es därfen nur Einheiten verschoben werden, die nicht gekämpf
				Ansonsten kännen beliebig viele Einheiten verschoben werden
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
		
		// Bonuseinheiten durch Karten SPäTER, weil kein interface vorhanden
		supply += useBonusCards();
 
		// Einheiten setzen lassen
		placeUnits(supply);
		
		// Angreifen
		attack();
		
		// Einheiten verschieben
		moveUnits();
	}

	private void placeUnits(int supply) {
		// TODO
		ui.getOriginatingCountry();
	}

	private void attack() {
		// TODO Auto-generated method stub
		// Nach land
	}
	
	private void moveUnits() {
		// TODO Auto-generated method stub
		
	}
	
	private int useBonusCards() {
		// TODO Auto-generated method stub
		ui.turnInCards();
		return false;
	}
	
	private int getCardBonus() {
		// TODO Kartenbonus berechnen (Reiter)
		return 0;
	}

	public Spieler getGewinner() {
		// TODO Auto-generated method stub
	}
	
}
