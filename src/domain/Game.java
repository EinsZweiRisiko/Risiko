package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import ui.UserInterface;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.Territory;
import domain.exceptions.InvalidTerritoryStateException;
import domain.persistence.FilePersistenceManager;
import domain.persistence.PersistenceManager;

/**
 * The game class manages a complete game of Risk
 * 
 * @author Jannes, Hendrik
 * 
 */
public class Game {

	/**
	 * Phases of a player's turn
	 */
	public static enum Phases {
		PLACEUNITS, ATTACK, MOVE, DEFEND
	};

	private UserInterface ui;
	private PlayerManager playerManager;
	private TerritoryManager territoryManager;
	private BonusCardManager bonusCardManager;
	private Player activePlayer;
	private ArrayList<Integer> bonusSupplySteps;
	private Iterator<Integer> bonusSupplyIter;
	private int currentBonusSupply;

	/**
	 * Constructor for a new game of Risk
	 */
	public Game(UserInterface ui) {
		this.ui = ui;

		// Setup the steps in which bonus units are allocated
		bonusSupplySteps = new ArrayList<Integer>(Arrays.asList(4, 6, 8, 10, 15, 20, 25, 30, 35,
				40, 45, 50, 55, 60));
		bonusSupplyIter = bonusSupplySteps.iterator();

		// Create territory manager
		territoryManager = new TerritoryManager();

		// Create player manager
		playerManager = new PlayerManager(ui);
		
		// Create bonus card manager
		bonusCardManager = new BonusCardManager();

		// Anfangsrunde
		placeStartUnits();
	}

	private void placeStartUnits() {
		// Entweder zufällig platzieren oder Spieler entscheiden lassen
		// Anfangsstarteinheiten bei 2 - 4 Spielern
		// 2 Spieler: 36
		// 3 Spieler: 35
		// 4 Spieler: 30

		// Place starting units in a random fashion


		if (ui.getPlaceMethod()) {
			// Gets the total amount of start units per player
			int startUnits;
			switch (playerManager.getPlayerCount()) {
			case 2:
				startUnits = 36;
				break;
			case 3:
				startUnits = 35;
				break;
			default:
				startUnits = 30;
			}

			// Set the start units for each player
			for (Player player : playerManager) {
				player.addSupply(startUnits);
			}

			// Randomly places a unit on one territory each
			Player currentPlayer;
			for (Territory territory : territoryManager.getRandomTerritoryList()) {
				// Cycle through all players
				currentPlayer = playerManager.getNextPlayer();

				// Place one unit on the territory
				try {
					territoryManager.changeTerritoryOwner(currentPlayer, territory, 1);
				} catch (InvalidTerritoryStateException e) {
					e.printStackTrace();
				}

				// Remove the placed units from the player's supply
				currentPlayer.subtractSupply(1);
			}

			// Place the remaining units randomly
			while (!playerManager.supplyAllocated()) {
				// Cycle through all players
				currentPlayer = playerManager.getNextPlayer();

				// Add one unit to a random territory
				currentPlayer.getRandomTerritory().addUnits(1);
				// Remove it from the player's supply
				currentPlayer.subtractSupply(1);
			}
		} else {
			// abwechselnd setzten algorithmus
		}
	}

	public boolean ended() {
		// TODO Auto-generated method stub
		// Wäre es nicht einfach folgendes zu machen:
		// if(spieler.size() == 1){return true;}
		// man kann ja einfach jeden Spieler der aus dem Spiel ausscheidet aus dem PlMngr nehmen
		return false;
	}

	public void run() {		
		// Herausfinden, welcher Spieler dran ist
		activePlayer = playerManager.getNextPlayer();

		// gibt den aktiven Spieler aus
		ui.announceCurrentPlayer(activePlayer);


		// save number of current territories
		int occupiedTerritories = activePlayer.getTerritoryCount();

		// Wie viel Verstärkung?
		int supply = 0;

		// Wie viele Einheiten bekommt der Spieler durch eroberte Länder?
		supply += activePlayer.getTerritoryCount() / 3;
		// Der Spieler bekommt mindestens 3 Einheiten
		if (supply < 3) {
			supply = 3;
		}

		// Bonuseinheiten durch eroberte Kontinente
//		supply += activePlayer.getContinentBonus();

		// Bonuseinheiten durch Karten SPäTER, weil kein interface vorhanden
		supply += redeemBonusCards();

		// Einheiten setzen lassen
		placeUnits(supply);

		// TODO useMissionCard();

		// Angreifen
		attack();

		// Einheiten verschieben
		moveUnits();

		// If the player conquered at least one territory
		if (occupiedTerritories < activePlayer.getTerritoryCount()) {
			// Give the player a bonus card
			BonusCard card = bonusCardManager.retrieveRandomCard();
			activePlayer.addBonusCard(card);
			// Let the user know which card he got
			ui.announceBonusCard(card, activePlayer);
		}
		
		if(ui.wantToSave()) {
			PersistenceManager pm = new FilePersistenceManager();
			if(pm.saveGame(this, "risikoSave.ser")){
				ui.announceSuccesfulSave();
			}
		}
	}

	private void placeUnits(int supply) {
		// gibt aus welcher Spieler dran ist
		ui.announceCurrentPlayer(activePlayer);

		Territory targetTerritory = null;
		Territory originatingTerritory = null;
		int amountUnitPlace;

		activePlayer.addSupply(supply);

		do {

			// Auf welches Land sollen Einheiten platziert werden?
			do {
				targetTerritory = ui.getTargetTerritory(activePlayer, Phases.PLACEUNITS,
						targetTerritory);
			} while (!targetTerritory.getOwner().equals(activePlayer));

			// Wieviele Einheiten sollen platziert werden?
			do {
				amountUnitPlace = ui.getAmountUnit(activePlayer, originatingTerritory,
						targetTerritory, Phases.PLACEUNITS);
			} while (amountUnitPlace > activePlayer.getSupply());

			// supply Aktualisieren
			activePlayer.subtractSupply(amountUnitPlace);
			targetTerritory.setUnits(targetTerritory.getUnits() + amountUnitPlace);

		} while (activePlayer.getSupply() > 0);
	}

	private void attack() {

		// Schleife die den aktuellen Spieler Fragt ob er angreifen möchte.
		while (ui.askForPhase(activePlayer, Phases.ATTACK)) {

			Territory originatingTerritory;
			Territory targetTerritory;
			int amountUnitAttack;
			int amountUnitDefense;

			// Abfrage durch die CLI von welchem Land welches Angegriffen werden
			// soll. Gehört es dem Spieler nicht erneute Abfrage. Auch neue
			// Abfrage insofern zu wenig Einheiten zum angreifen vorhanden sind.
			do {
				originatingTerritory = ui.getOriginatingTerritory(activePlayer, Phases.ATTACK);
			} while (!originatingTerritory.getOwner().equals(activePlayer)
					|| originatingTerritory.getUnits() == 1);

			// Abfrage durch die CLI welches Land welches Angegriffen werden
			// soll. Gehört es dem Spieler erneute Abfrage.
			do {
				targetTerritory = ui.getTargetTerritory(activePlayer, Phases.ATTACK,
						originatingTerritory);
			} while (targetTerritory.getOwner().equals(activePlayer));

			// Abfrage durch die CLI mit wievielen Einheiten angegriffen werden
			// soll. Es können zwischen 1 und 3 Einheiten gewählt werden bei
			// Falscheingabe wiederholung.
			do {
				amountUnitAttack = ui.getAmountUnit(activePlayer, originatingTerritory,
						targetTerritory, Phases.ATTACK);
			} while (((originatingTerritory.getUnits() - 1) < amountUnitAttack)
					|| (amountUnitAttack < 1 || amountUnitAttack > 3));

			// Besitzer des angegriffenden Landes ermitteln
			Player attackedPlayer = targetTerritory.getOwner();

			// Abfrage durch die CLI mit wievielen Einheiten verteidigt werden
			// soll. Es können zwischen 1 und 2 Einheiten gewählt werden.
			do {
				amountUnitDefense = ui.getAmountUnit(attackedPlayer, originatingTerritory,
						targetTerritory, Phases.DEFEND);
			} while ((targetTerritory.getUnits() < amountUnitDefense)
					|| (amountUnitDefense < 0 || amountUnitDefense > 2));

			BattleSystem battleSystem = new BattleSystem(amountUnitAttack, amountUnitDefense,
					originatingTerritory, targetTerritory, ui, territoryManager, playerManager);
		}

	}

	private void moveUnits() {

		Territory originatingTerritory;
		Territory targetTerritory;
		int amountUnitMove;

		if (ui.askForPhase(activePlayer, Phases.MOVE)) {
			do {
				originatingTerritory = ui.getOriginatingTerritory(activePlayer, Phases.MOVE);
			} while (originatingTerritory.getOwner().equals(activePlayer)
					&& originatingTerritory.getUnits() < 1);

			do {
				targetTerritory = ui.getTargetTerritory(activePlayer, Phases.MOVE,
						originatingTerritory);
			} while (originatingTerritory.getOwner().equals(activePlayer)
					&& originatingTerritory.getUnits() < 1);

			do {
				amountUnitMove = ui.getAmountUnit(activePlayer, originatingTerritory,
						targetTerritory, Phases.MOVE);
			} while ((originatingTerritory.getUnits() - 1) < amountUnitMove);

			// Einheiten entsprechend der Eingabe verschieben
			originatingTerritory.setUnits(originatingTerritory.getUnits() - amountUnitMove);
			targetTerritory.setUnits(targetTerritory.getUnits() + amountUnitMove);
		}

	}

	private int redeemBonusCards() {
		HashSet<BonusCard> cards = activePlayer.getBonusCards();
		// TODO remove this return statement
		return 0;
		// TODO check if a triple of cards is availabe

//		if (cards.size() >= 5) {
//			// Redeeming is mandatory
//			ui.announceRedeeming(activePlayer);
//			HashSet<BonusCard> redeemCards = ui.askForBonusCards();
//			activePlayer.removeBonusCards(redeemCards);
//			return getCardBonus();
//			
//		} else if (ui.turnInCards()) {
//			// The player wants to redeem cards
//			// TODO check for validity
//			HashSet<BonusCard> redeemCards = ui.askForBonusCards();
//			activePlayer.removeBonusCards(redeemCards);
//			return getCardBonus();
//		}
//		
//		return 0;
	}

	private int getCardBonus() {
		if (bonusSupplyIter.hasNext()) {
			currentBonusSupply = bonusSupplyIter.next();
		}
		return currentBonusSupply;
	}

	public Player getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayerManager getPlayerManager() {
		return playerManager;
		
	}

}
