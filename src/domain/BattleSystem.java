package domain;

import java.util.ArrayList;

import domain.exceptions.InvalidTerritoryStateException;

import ui.UserInterface;
import valueobjects.Territory;

/**
 * 
 * @author Timur
 * 
 */
public class BattleSystem {

	ArrayList<Integer> attack = new ArrayList<Integer>();
	ArrayList<Integer> defense = new ArrayList<Integer>();

	// Anzahl angreifender Soldaten
	int amountOfAttackers;
	int amountOfDefenders;

	// die angreifenden Wuerfel mit ihren wertigkeiten absteigen sortiert
	int attackOne = 0;
	int attackTwo = 0;
	// die verteidigenden Wuerfel mit ihren wertigkeiten absteigen sortiert
	int defenseOne = 0;
	int defenseTwo = 0;

	// Angriffzuege
	int attackRound;
	int maxAttackRounds;

	// Ziell und Quellland
	Territory targetTerritory;
	Territory originatingTerritory;
	
	//Userinterface
	UserInterface ui;
	
	// Manager
	TerritoryManager territoryManager;
	PlayerManager playerManager;
	
	// Angriffe wieviele Angriffe werden in einem Angriff getßtigt
	int attacks = 0;

	// Konstruktur kriegt die Anzahl der Anfreifenden bzw. Verteidigenden Einheiten
	// Kontruktur bekommt außerdem das Ziel und Quellland als Objekte

	public BattleSystem(int numberOfAttackers, int numberOfDefenders,
			Territory originatingTerritory, Territory targetTerritory, UserInterface ui, TerritoryManager territoryManager , PlayerManager playerManager) {
		this.amountOfAttackers = numberOfAttackers;
		this.amountOfDefenders = numberOfDefenders;
		this.targetTerritory = targetTerritory;
		this.originatingTerritory = originatingTerritory;
		this.ui = ui;
		this.territoryManager = territoryManager;
		this.playerManager = playerManager;
		fight();
	}

	/**
	 * Zufßllige berechnung der Wßrfel wertigkeiten
	 * 
	 * @param amount
	 *            Anzahl der zu wuerfelnden Wuerfel
	 * 
	 * @return ArrayList als Integer der gewuerfelten zahlen
	 */
	// TODO static methods are not allowed
	public static ArrayList<Integer> getDice(int amount) {
		// varriert
		ArrayList<Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i <= amount - 1; i++) {
			dice.add(i, (int) ((Math.random()) * 6 + 1));
		}
		return dice;
	}

	/**
	 * Berechnet die grueßte Zahl in einer Liste
	 * 
	 * @param dice
	 *            Eine Liste aus gewuerfelten Zahlen
	 * @return Groeßte Zahl aus den Wuerfelergebnissen eines Spielers
	 */
	public int getBiggestNumber(ArrayList<Integer> dice) {
		int maximum = dice.get(0);
		boolean in = false;
		int i = 1;
		while (i < dice.size()) {
			if (dice.get(i) > maximum) {
				maximum = dice.get(i);
				dice.remove(i);
				in = true;
			}
			i++;
		}
		if (in == false) {
			dice.remove(0);
		}
		return maximum;
	}

	public void fight() {
		/*
		 * Benutzereingabe muss ueberprueft werden mit Einheiten Anzahl (angGesamt und defGesamt) auf
		 * dem Angriff bzw Zielland angAnzahl darf nur 2 oder 3 sein wenn eine Einheit auf dem
		 * Quellland bleibt bei Defense land muss min. eine Einheite vorhanden sein heisst die Zahl
		 * darf nur 1 oder 2 sein.
		 */

		// hier werden die wuerfel Anzahl festgelegt je nach anzahl der Einheiten
		// auf dem Land
		attack = getDice(amountOfAttackers);
		defense = getDice(amountOfDefenders);

		/*
		 * generiert die groeßten Zahlen im Integer fuer den Angriff es gilt: fuer den Angriff:
		 * angAnzahl = 2 dann nur eine Zahl zum kaempfen angAnzahl = 3 dann zwei Zahlen zum kaempfen
		 * 
		 * fuer die Defensive: defAnzahl = 1 dann eine Zahl zum verteidigen defAnzahl = 2 dann zwei
		 * Zahlen zum verteidigen
		 */

		// Kampfgeschehen ausrechnen der Verluste
		if (amountOfAttackers == 1) {
			attackOne = getBiggestNumber(attack);
			attackRound++;
			
			System.out.println("\n" + "-----" + originatingTerritory.getName() + "("
					+ originatingTerritory.getOwner().getName() + ") " + amountOfAttackers
					+ " Armee"+"("+"n"+")"+" " + " vs. " + amountOfDefenders + " Armee"+"("+"n"+")"+" " + targetTerritory.getName()
					+ "(" + targetTerritory.getOwner().getName() + ")" + "-----" + "\n");

			// 1 gegen 1
			if (amountOfDefenders == 1) {
				defenseOne = getBiggestNumber(defense);

				if (attackOne > defenseOne) {
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}
			}

			// 1 gegen 2
			if (amountOfDefenders == 2) {
				defenseOne = getBiggestNumber(defense);
				defenseTwo = getBiggestNumber(defense);

				if (attackOne > defenseOne) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}
			}
		}

		if (amountOfAttackers == 2) { // mit Abfrage wegen einem Soldat auf dem
			// Quellland
			attackOne = getBiggestNumber(attack);
			attackTwo = getBiggestNumber(attack);
			attackRound++;

			System.out.println("\n" + "-----" + originatingTerritory.getName() + "("
					+ originatingTerritory.getOwner().getName() + ") " + amountOfAttackers
					+ " Armee"+"("+"n"+")"+" " + " vs. " + amountOfDefenders + " Armee"+"("+"n"+")"+" " + targetTerritory.getName()
					+ "(" + targetTerritory.getOwner().getName() + ")" + "-----" + "\n");
			// 2 gegen 1
			if (amountOfDefenders == 1) {
				defenseOne = getBiggestNumber(defense);

				if (attackOne > defenseOne) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}
			}
			// 2 gegen 2
			if (amountOfDefenders == 2) {
				defenseOne = getBiggestNumber(defense);
				defenseTwo = getBiggestNumber(defense);

				// ersten Wuerfel treten gegeneinander an
				if (attackOne > defenseOne) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}

				// zweiten Wuerfel treten gegen einander an
				if (attackTwo > defenseTwo) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}
			}
		}

		// Angriff 3
		if (amountOfAttackers == 3) {
			attackOne = getBiggestNumber(attack);
			attackTwo = getBiggestNumber(attack);
			attackRound++;
			
			System.out.println("\n" + "-----" + originatingTerritory.getName() + "("
					+ originatingTerritory.getOwner().getName() + ") " + amountOfAttackers
					+ " Armee"+"("+"n"+")"+" " + " vs. " + amountOfDefenders + " Armee"+"("+"n"+")"+" " + targetTerritory.getName()
					+ "(" + targetTerritory.getOwner().getName() + ")" + "-----" + "\n");

			// 3 gegen 1
			if (amountOfDefenders == 1) {
				defenseOne = getBiggestNumber(defense);
				if (attackOne > defenseOne) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}
			}

			// 3 gegen 2
			if (amountOfDefenders == 2) {
				defenseOne = getBiggestNumber(defense);
				defenseTwo = getBiggestNumber(defense);
				// ersten Wuerfel treten gegeneinander an
				if (attackOne > defenseOne) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}

				// zweiten Wuerfel treten gegen einander an
				if (attackTwo > defenseTwo) {
					// Zielland verliert Einheit
					eventMsgOffense();
				} else {
					// Quellland verliert Einheit
					eventMsgDefense();
				}
			}
		}
	}

	// Kampfgeschehen wird ausgegeben und die Einheiten werden auf den
	// jeweiligen Laendern reduziert um 1
	// gewinnen der OFFENSE
	public void eventMsgOffense() {
		attacks++;
		amountOfDefenders--;
		// Runter setzten der Einheiten
		targetTerritory.setUnits(targetTerritory.getUnits() - 1);

		ui.battleMsgOffense(attacks, targetTerritory, attackOne, attackTwo, defenseOne, defenseTwo);
		
		// überprüfung ob das Land 0 Einheiten hat, wenn ja dann:
		// - die Einheiten des Angreifenden werden gezählt
		// - targetTerritory wird mit den angreifenden Owner gesetzt
		// - und das Land wird mit diesen Einheiten besetzt
		
		// falls keine Verteidiger mehr vorhanden
		if(targetTerritory.getUnits() == 0) {
			try {
				originatingTerritory.setUnits(originatingTerritory.getUnits() - amountOfAttackers);
				territoryManager.changeTerritoryOwner(playerManager.getActivePlayer() , targetTerritory, amountOfAttackers);
			} catch (InvalidTerritoryStateException e) {
				e.printStackTrace();
			}
		}
	}
	
	// beim gewinnen der DEFENSE
	public void eventMsgDefense() {
		// Anzahl der Angriffe die getaetigt werden
		attacks++;
		amountOfAttackers--;
		// Runter setzten der Einheiten
		originatingTerritory.setUnits(originatingTerritory.getUnits() - 1);
		ui.battleMsgDefense(attacks, originatingTerritory, attackOne, attackTwo, defenseOne, defenseTwo);
		
	}
}
