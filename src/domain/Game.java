package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import ui.UserInterface;
import ui.cli.CommandLineInterface;
import valueobjects.Player;
import valueobjects.Territory;

/**
 * The game class manages a complete game of Risk 
 * @author Jannes
 *
 */
public class Game {

	/**
	 * Phases of a player's turn
	 */
	public static enum Phases {
		PLACEUNITS, ATTACK, MOVE, DEFEND
	};

	private PlayerManager playerManager;
	private TerritoryManager territoryManager;
	private Player activePlayer;
	private UserInterface userInterface;
	private ArrayList<Integer> bonusAmountSteps;
	private Iterator<Integer> bonusAmountIter;

	/**
	 * Constructor for a new game of Risk
	 */
	public Game() {
		// Setup the steps in which bonus units are allocated
		bonusAmountSteps = new ArrayList<Integer>(Arrays.asList(4, 6, 8, 10, 15, 20, 25, 30, 35,
				40, 45, 50, 55, 60));
		bonusAmountIter = bonusAmountSteps.iterator();

		// Create territory manager
		territoryManager = new TerritoryManager();
		
		
		userInterface = new CommandLineInterface();
		
		// Create player manager
		playerManager = new PlayerManager(userInterface);
		

		// Anfangsrunde
		placeStartUnits();
	}

	private void placeStartUnits() {
		// TODO Auto-generated method stub
		// Entweder zufällig platzieren oder Spieler entscheiden lassen
		// Anfang start einheiten bei 2 - 4 Spielern
		// 2 Spieler: 36
		// 3 Spieler: 35
		// 4 Spieler: 30

		// RANDOM Play Funct()
		if(userInterface.getPlaceMethod()) {
			//RADNOM PLACE UNITS Algorithmus

//			// ermittelt die Starteinheiten, aber ist voll unnütz!
//			int startUnits;
//			if(playerManager.getNumberOfPlayers() == 2) {
//				startUnits = 36;
//			}else if(playerManager.getNumberOfPlayers() == 3) {
//				startUnits = 35;
//			}else if(playerManager.getNumberOfPlayers() == 4) {
//				startUnits = 30;
//			}

			// besetzt alle freien Länder
			for(Territory territory : territoryManager.getRandomTerritoryList()) {
				
				Player player = playerManager.getCurrentPlayer();
				player.addTerritory(territory);
				territory.setUnits(2);
				
				playerManager.nextPlayer();
			}
			
		} else {
			// abwechselnd setzten algorithmus
		}
	}

	public boolean ended() {
		// TODO Auto-generated method stub
		return false;
	}

	public void run() {
		// Herausfinden, welcher Spieler dran ist
		activePlayer = playerManager.getCurrentPlayer();
		
		userInterface.announceCurrentPlayer(activePlayer);

		// Wie viel Verstärkung?
		int supply = 0;

		// Wie viele Einheiten bekommt der Spieler durch eroberte Länder?
		supply += activePlayer.getTerritoryNumber() / 3;
		// Der Spieler bekommt mindestens 3 Einheiten
		if (supply < 3) {
			supply = 3;
		}

		// Bonuseinheiten durch eroberte Kontinente
//		supply += activePlayer.getContinentBonus();

		// Bonuseinheiten durch Karten SPäTER, weil kein interface vorhanden
		supply += useBonusCards();

		// Einheiten setzen lassen
		placeUnits(supply);

		// Angreifen
		attack();

		// Einheiten verschieben
		moveUnits();
		
		playerManager.nextPlayer();
	}

	private void placeUnits(int supply) {
		Territory targetCountry = null;
		int amountUnitPlace;

		while (supply < 0) {

			// Auf welches Land sollen Einheiten platziert werden?
			do {
				targetCountry = userInterface.getTargetTerritroy(activePlayer, Phases.PLACEUNITS, targetCountry);
			} while (!targetCountry.getOwner().equals(activePlayer));

			// Wieviele Einheiten sollen platziert werden?
			do {
				amountUnitPlace = userInterface.getAmountUnit(activePlayer, Phases.PLACEUNITS);
			} while (amountUnitPlace > supply);

			// supply Aktualisieren
			supply -= amountUnitPlace;
		}

	}

	private void attack() {
		
		// Schleife die den aktuellen Spieler Fragt ob er angreifen möchte.
		while (userInterface.askForAttack(activePlayer)) {
			
			Territory originatingTerritory;
			Territory targetTerritory;
			int amountUnitAttack;
			int amountUnitDefense;

			// Abfrage durch die CLI von welchem Land welches Angegriffen werden
			// soll. Gehört es dem Spieler nicht erneute Abfrage. Auch neue
			// Abfrage insofern zu wenig Einheiten zum angreifen vorhanden sind.
			do {
				originatingTerritory = userInterface.getOriginatingTerritory(activePlayer,
						Phases.ATTACK);
			} while (!originatingTerritory.getOwner().equals(activePlayer)
					|| originatingTerritory.getAmountOfUnits() == 1);

			// Abfrage durch die CLI welches Land welches Angegriffen werden
			// soll. Gehört es dem Spieler erneute Abfrage.
			do {
				targetTerritory = userInterface.getTargetTerritroy(activePlayer, Phases.ATTACK, originatingTerritory);
			} while (targetTerritory.getOwner().equals(activePlayer));

			// Abfrage durch die CLI mit wievielen Einheiten angegriffen werden
			// soll. Es können zwischen 1 und 3 Einheiten gewählt werden bei
			// Falscheingabe wiederholung.
			do {
				amountUnitAttack = userInterface.getAmountUnit(activePlayer, Phases.ATTACK);
			} while (((originatingTerritory.getAmountOfUnits() -1) < amountUnitAttack)
					|| (amountUnitAttack < 1 || amountUnitAttack > 3));

			// Besitzer des angegriffenden Landes ermitteln
			Player attackedPlayer = targetTerritory.getOwner();

			// Abfrage durch die CLI mit wievielen Einheiten verteidigt werden
			// soll. Es können zwischen 1 und 2 Einheiten gewählt werden.
			do {
				amountUnitDefense = userInterface.getAmountUnit(attackedPlayer, Phases.DEFEND);
			} while (((targetTerritory.getAmountOfUnits()-1) < amountUnitDefense)
					|| (amountUnitDefense < 1 || amountUnitDefense > 2));

			BattleSystem battleSystem = new BattleSystem(amountUnitAttack, amountUnitDefense,
					originatingTerritory, targetTerritory);
		}
	}

	private void moveUnits() {
		// TODO Auto-generated method stub

	}

	private int useBonusCards() {
		// TODO Auto-generated method stub

		int bonus = 0;
		if (userInterface.turnInCards()) {
			bonus = getCardBonus();
		}
		return bonus;
	}

	private int getCardBonus() {
		// TODO Kartenbonus berechnen (Reiter)
		return 0;
	}

	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

}
