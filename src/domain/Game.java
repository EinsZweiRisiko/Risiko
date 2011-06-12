package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.Territory;
import domain.Game.Phases;
import domain.exceptions.InvalidTerritoryStateException;

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
		TURNINCARDS, PLACE, ATTACK, MOVE, DEFEND
	};

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
	public Game(ArrayList<String> playerNames) {

		// Setup the steps in which bonus units are allocated
		bonusSupplySteps = new ArrayList<Integer>(Arrays.asList(4, 6, 8, 10, 12, 15));
		bonusSupplyIter = bonusSupplySteps.iterator();

		// Create territory manager
		territoryManager = new TerritoryManager();

		// Create player manager
		playerManager = new PlayerManager(playerNames);

		// Create bonus card manager
		bonusCardManager = new BonusCardManager();

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
	}

	

	public void run() {
		
		// Herausfinden, welcher Spieler dran ist
		Player lastPlayer = activePlayer;
		activePlayer = playerManager.getNextPlayer();
		
		// test if player is kicked out of the game :: LOSE the game
		testIfPlayerLose(lastPlayer);
		
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
	
		// bonussupply by owned continents
		int contintentBonus = 0;
	
		// count up to walk through all continents
		for (int i = 0; i < territoryManager.getContinents().size(); i++) {
			/*
			 * search for full contintents in players territory list if continten is in the list get
			 * the supply
			 */
			if (activePlayer.getTerritories().containsAll(
					(Collection<?>) territoryManager.getContinents().get(i).getTerritories())) {
				contintentBonus += territoryManager.getContinents().get(i).getSupplyBonus();
			}
		}
	
		supply += contintentBonus;
	
		// Bonuseinheiten durch Karten SPäTER, weil kein interface vorhanden
		supply += redeemBonusCards();
	
		// Einheiten setzen lassen
		placeUnits(supply);
	
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

	}

	public void placeUnitsManual(Territory targetTerritory, Player currentPlayer) {
		do {
			targetTerritory = ui.getTargetTerritory(currentPlayer, Phases.PLACE,
					targetTerritory);
		} while (!targetTerritory.getOwner().equals(currentPlayer));
	}

	private void placeUnits(int supply) {

		Territory targetTerritory = null;
		Territory originatingTerritory = null;
		int amountUnitPlace;

		activePlayer.addSupply(supply);

		do {
			// Auf welches Land sollen Einheiten platziert werden?
			do {
				targetTerritory = ui.getTargetTerritory(activePlayer, Phases.PLACE,
						targetTerritory);
			} while (!targetTerritory.getOwner().equals(activePlayer));

			// Wieviele Einheiten sollen platziert werden?
			do {
				amountUnitPlace = ui.getAmountUnit(activePlayer, originatingTerritory,
						targetTerritory, Phases.PLACE);
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

//			new BattleSystem(amountUnitAttack, amountUnitDefense, originatingTerritory,
//					targetTerritory, ui, territoryManager, playerManager);
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
	}

	private int getCardBonus() {
		if (bonusSupplyIter.hasNext()) {
			currentBonusSupply = bonusSupplyIter.next();
		} else {
			currentBonusSupply += 5;
		}
		return currentBonusSupply;
	}

	private void testIfPlayerLose(Player lastPlayer) {
		if (lastPlayer.getTerritories().isEmpty()) {
			ui.announceYouLose(lastPlayer);
			playerManager.removePlayer(lastPlayer);
		}

	}

	public boolean ended() {
		if (playerManager.getPlayers().size() == 1) {
			return true;
		}
		return false;
	}

	public Player getWinner() {
		// if all players are erased the nextPlayer would be the last man standing
		return playerManager.getNextPlayer();
	}

	public PlayerManager getPlayerManager() {
		return playerManager;

	}


	//--------------- A NEW ERA BEGINS HERE -------------------

	/**
	 * TODO
	 */
	public void placeStartUnitsRandomly() {
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
		
		// TODO there has to be a better way to do this
		// Fast-forward through the players to find out whose turn it is
		for(int i = 0 ; i < playerManager.getPlayerCount(); ++i) {
			activePlayer = playerManager.getNextPlayer();
		}
	}


	/**
	 * TODO
	 * @return Player
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}


	/**
	 * TODO
	 */
	public void nextPlayer() {
		activePlayer = playerManager.getNextPlayer();
	}

	/**
	 * TODO
	 * @return Phase
	 */
	public Phases getNextPhase() {
		
		// Turn in bonus cards
//		allocateSupply();
		// Place supply
		// Attack
		// Move units

		return Phases.PLACE;
	}

}
