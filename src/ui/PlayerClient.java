package ui;

import server.GameMethodsImpl;
import valueobjects.Player;

@Deprecated
public class PlayerClient implements Runnable {

	private GameMethodsImpl game;
	private Player player;
	private IO io = new IO();
	
	// Abbild der Länder:
	// laender
	// 	besitzer
	//  anzahl der einheiten

	public PlayerClient(GameMethodsImpl game, Player player) {
		this.game = game;
		this.player = player;
	}
	
	/**
	 * TODO doc
	 */
	public void run() {
		// Will hold the current player
		Player player;
		
		switch (game.getNextAction()) {
			case TURNINCARDS:
				player = game.getActivePlayer();
				/*
				 * Ask the player if he wants to turn in cards
				 * If yes, redeem cards (adds to the player's supply)
				 */
//				HashSet<BonusCard> cards = player.getBonusCards();
				break;

			case PLACEMENT:
				player = game.getActivePlayer();
				/*
				 * Get supply
				 * If supply > 0
				 *   Ask the player where he wants to place his supply armies
				 *     Place the armies
				 */
				
				// Number of supply
				// Show territories owned by the player
				// Get target territory
				// How many units should be placed?
				// Place the supply
				int supply = player.getSupplies();
				io.write("Your supply is: " + supply);
				io.write("You own these territories:");
				io.writeList(player.getTerritories());
				io.readNumber("Where do you want to place your supply units?");
				
//				do {
//					// Auf welches Land sollen Einheiten platziert werden?
//					do {
//						targetTerritory = ui.getTargetTerritory(activePlayer,
//								Action.PLACEMENT,
//								targetTerritory);
//					} while (!targetTerritory.getOwner().equals(activePlayer));
//
//					// Wieviele Einheiten sollen platziert werden?
//					do {
//						amountUnitPlace = ui.getAmountUnit(activePlayer,
//								originatingTerritory,
//								targetTerritory, Action.PLACEMENT);
//					} while (amountUnitPlace > activePlayer.getSupplies());
//
//					// supply Aktualisieren
//					activePlayer.subtractSupplies(amountUnitPlace);
//					targetTerritory.setUnits(targetTerritory.getUnits()
//							+ amountUnitPlace);
//
//				} while (activePlayer.getSupplies() > 0);
				
				
				break;

			case ATTACK:
				player = game.getActivePlayer();
				// Check if one player has no more units left and is therefore dead
				
				/*
				 * Figure out which territories of the current player could be used for an attack
				 * 
				 * Ask the player if he wants to attack a territory
				 *   1. Get originating territory (who supplies the list?)
				 *   2. Get amount of units to attack with (1-3)
				 *   3. Get the target territory
				 *   4. Roll the dice
				 *   5. Opposing player: Get amount of units to defend with (1-2)
				 *   6. Roll the dice
				 *   7. New attack?
				 */
				
				
//				// Schleife die den aktuellen Spieler Fragt ob er angreifen möchte.
//				while (ui.askForPhase(activePlayer, Action.ATTACK)) {
//
//					Territory originatingTerritory;
//					Territory targetTerritory;
//					int amountUnitAttack;
//					int amountUnitDefense;
//
//
//
//					// Abfrage durch die CLI mit wievielen Einheiten angegriffen werden
//					// soll. Es können zwischen 1 und 3 Einheiten gewählt werden bei
//					// Falscheingabe wiederholung.
//					do {
//						amountUnitAttack = ui.getAmountUnit(activePlayer,
//								originatingTerritory,
//								targetTerritory, Action.ATTACK);
//					} while (((originatingTerritory.getUnits() - 1) < amountUnitAttack)
//							|| (amountUnitAttack < 1 || amountUnitAttack > 3));
//
//					// Besitzer des angegriffenden Landes ermitteln
//					Player attackedPlayer = targetTerritory.getOwner();
//
//					// Abfrage durch die CLI mit wievielen Einheiten verteidigt werden
//					// soll. Es können zwischen 1 und 2 Einheiten gewählt werden.
//					do {
//						amountUnitDefense = ui.getAmountUnit(attackedPlayer,
//								originatingTerritory,
//								targetTerritory, Action.DEFENSE);
//					} while ((targetTerritory.getUnits() < amountUnitDefense)
//							|| (amountUnitDefense < 0 || amountUnitDefense > 2));
//
////					new BattleSystem(amountUnitAttack, amountUnitDefense, originatingTerritory,
////							targetTerritory, ui, territoryManager, playerManager);
//				}
				
				break;

			case MOVEMENT:
				player = game.getActivePlayer();
				
				/*
				 * Figure out which units are eligible to be moved
				 * 
				 * Ask the player if he wants to move units
				 *   1. Get originating territory (who supplies the list?)
				 *   2. Get amount of units to be moved
				 *   3. Get target territory
				 */
				
//				Territory originatingTerritory;
//				Territory targetTerritory;
//				int amountUnitMove;
//
//				if (ui.askForPhase(activePlayer, Action.MOVEMENT)) {
//					do {
//						originatingTerritory = ui.getOriginatingTerritory(activePlayer,
//								Action.MOVEMENT);
//					} while (originatingTerritory.getOwner().equals(activePlayer)
//							&& originatingTerritory.getUnits() < 1);
//
//					do {
//						targetTerritory = ui.getTargetTerritory(activePlayer,
//								Action.MOVEMENT,
//								originatingTerritory);
//					} while (originatingTerritory.getOwner().equals(activePlayer)
//							&& originatingTerritory.getUnits() < 1);
//
//					do {
//						amountUnitMove = ui.getAmountUnit(activePlayer,
//								originatingTerritory,
//								targetTerritory, Action.MOVEMENT);
//					} while ((originatingTerritory.getUnits() - 1) < amountUnitMove);
//
//					// Einheiten entsprechend der Eingabe verschieben
//					originatingTerritory.setUnits(originatingTerritory.getUnits()
//							- amountUnitMove);
//					targetTerritory.setUnits(targetTerritory.getUnits()
//							+ amountUnitMove);
//				}
				
				break;
		}
	}
	
}
