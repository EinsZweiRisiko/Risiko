package ui.cli;

import java.util.ArrayList;
import java.util.Iterator;

import ui.UserInterface;
import valueobjects.Player;
import valueobjects.Territory;
import valueobjects.TerritoryCard;
import domain.Game.Phases;

/**
 * 
 * @author Hendrik, Jannes, Timur
 * 
 */

public class CommandLineInterface implements UserInterface {

	/**
	 * Konstruktor
	 */
	public CommandLineInterface() {

	}

	/**
	 * Legt ein Quelland fest. Zweck wird durch Phases festgelegt.
	 * 
	 * @param currentPlayer
	 *            der aktuelle Spieler.
	 * @param phase
	 *            Phasen ATTACK und MOVE werden hier erwartet.
	 */
	@Override
	public Territory getOriginatingTerritory(Player currentPlayer, Phases phase) {

		IO.println("\n");

		int selection = 0;

		// Copy of the territores-list of the current Player
		ArrayList<Territory> territories = new ArrayList<Territory>(currentPlayer.getTerritories());

		Territory territory;

		if (phase == Phases.ATTACK) {
			for (Iterator<Territory> iter = territories.iterator(); iter.hasNext();) {
				territory = iter.next();

				boolean allYourTerritoryAreBelongToUs = true;
				for (Territory neighbor : territory.getNeighbors()) {
					if (!neighbor.getOwner().equals(currentPlayer)) {
						allYourTerritoryAreBelongToUs = false;
					}
				}

				if (allYourTerritoryAreBelongToUs) {
					iter.remove();
				}
			}
		}
		if (phase == Phases.MOVE) {
			for (Iterator<Territory> iter = territories.iterator(); iter.hasNext();) {
				territory = iter.next();

				boolean noNeighborBelongToMe = true;
				for (Territory neighbor : territory.getNeighbors()) {
					if (neighbor.getOwner().equals(currentPlayer)) {
						noNeighborBelongToMe = false;
					}
				}

				if (noNeighborBelongToMe) {
					iter.remove();
				}
			}
		}

		// Ausgabe aller Länder die der Spieler besitzt. ++ Anzahl der Einheiten
		for (int i = 0; i < territories.size(); i++) {
			IO.println("(" + (i + 1) + ")" + territories.get(i).getName() + " || Einheiten" + "("
					+ territories.get(i).getUnits() + ")");
		}

		// Abfrage welches Land gewählt werden soll
		do {
			// Für Angriffe
			if (phase == Phases.ATTACK) {
				selection = IO.readInt("\n" + "Land angreifen: " + "\n"
						+ "Geben Sie das Land an von dem Sie angreifen wollen: ") - 1;

				// Für Verschieben
			} else if (phase == Phases.MOVE) {
				selection = IO.readInt("\n" + "Einheiten verschieben: " + "\n"
						+ "Geben Sie das Land an von dem Sie Einheiten verschieben wollen: ") - 1;
			}
		} while (selection > territories.size() && selection <= 0);
		return territories.get(selection);
	}

	/**
	 * Legt ein Zielland fest. Zweck wird durch Phases festgelegt.
	 * 
	 * @param currentPlayer
	 *            der aktuelle Spieler.
	 * @param phase
	 *            Phasen ATTACK,MOVE und PLACEUNITS werden hier erwartet.
	 * @param originatingTerritory
	 *            Quellland wird benötigt um Nachbarländer zu ermitteln.
	 */
	@Override
	public Territory getTargetTerritory(Player currentPlayer, Phases phase,
			Territory originatingTerritory) {

		IO.println("\n");

		int selection = 0;
		ArrayList<Territory> territories;

		// Abfrage bei Angriff
		if (phase == Phases.ATTACK) {
			territories = originatingTerritory.getNeighbors();
			for (int i = 0; i < territories.size(); i++) {
				// If a Territory in this list belongs to the Attacker it won't show up, also it won't
				// be listed if there are no enemy territories.
				if (territories.get(i).getOwner().equals(currentPlayer)) {
					// TODO alle störenden Einträge entfernen und Liste ggf. neuordnen um Lücken zu
					// schließen
				} else {
					IO.println("(" + (i + 1) + ")" + territories.get(i).getName() + " || Einheiten"
							+ "(" + territories.get(i).getUnits() + ")" + " || Im Besitz von "
							+ territories.get(i).getOwner().getName());
				}
			}
			selection = IO.readInt("\n" + "Geben Sie das Land an, dass sie angreifen wollen: ") - 1;

			// TODO Exception falls die Zurückgegebene ArrayList leer ist
			return territories.get(selection);

			// Abfrage bei Versetzen der Einheiten
		} else if (phase == Phases.MOVE) {
			territories = originatingTerritory.getNeighbors();
			for (int i = 0; i < territories.size(); i++) {
				if (!territories.get(i).getOwner().equals(currentPlayer)) {
					// TODO alle störenden Einträge entfernen
				} else {
					IO.println("(" + (i + 1) + ")" + territories.get(i).getName() + " || Einheiten"
							+ "(" + territories.get(i).getUnits() + ")" + " || Im Besitz von "
							+ territories.get(i).getOwner().getName());
				}
			}
			selection = IO.readInt("\n"
					+ "Geben Sie das Land an in welches sie Einheiten verschieben möchten: ") - 1;

			// TODO Exception falls die Zurückgegebene ArrayList leer ist
			return territories.get(selection);

			// Abfrage beim platzieren der Einheiten
		} else if (phase == Phases.PLACEUNITS) {
			territories = currentPlayer.getTerritories();
			for (int i = 0; i < territories.size(); i++) {
				IO.println("(" + (i + 1) + ")" + territories.get(i).getName() + " || Einheiten"
						+ "(" + territories.get(i).getUnits() + ")");
			}
			selection = IO.readInt("\n" + "Einheiten platzieren: " + "\n"
					+ "Geben Sie das Land ein in dem Sie Einheiten platzieren möchten: ") - 1;
			return territories.get(selection);
		}

		return originatingTerritory;
	}

	/**
	 * Der Spieler bekommt je nach Phase des Spiels eine Abfrage
	 */
	@Override
	public boolean askForPhase(Player activePlayer, Phases phase) {

		IO.println("\n");

		if (phase == Phases.ATTACK) {
			String wantToAttack = IO.readString(activePlayer.getName()
					+ " möchtest du angreifen? (j/n)");
			return wantToAttack.equals("j");
		}

		if (phase == Phases.MOVE) {
			String wantToMove = IO.readString(activePlayer.getName()
					+ " möchtest du Einheiten verschieben? (j/n)");
			return wantToMove.equals("j");
		}

		return false;
	}

	/**
	 * Fragt den aktuellen oder einen verteidigenden Spieler wieviele Armeen er einsetzen will.
	 */
	@Override
	public int getAmountUnit(Player activePlayer, Territory originatingTerritory,
			Territory targetTerritory, Phases phase) {
		int units = 0;

		// Fallunterscheidung je nach Phase anderer String
		if (phase == Phases.ATTACK) {
			int maxUnits = originatingTerritory.getUnits();
			if (maxUnits > 3) {
				maxUnits = 3;
			} else if (maxUnits == 3) {
				maxUnits = 2;
			}
			units = IO.readInt("Wieviele Einheiten sollen Angreifen? (1-" + maxUnits + ")" + ": ");
		}
		if (phase == Phases.DEFEND) {
			int maxUnits = targetTerritory.getUnits();
			if (maxUnits > 2) {
				maxUnits = 2;
				units = IO.readInt(targetTerritory.getOwner().getName()
						+ " wieviele Einheiten sollen Verteidigen? (1-2): ");
			}
			units = IO.readInt(targetTerritory.getOwner().getName()
					+ " wieviele Einheiten sollen Verteidigen? (1): ");
		}
		if (phase == Phases.MOVE) {
			units = IO.readInt("Wieviele Einheiten sollen verschoben werden?: ");
		}
		if (phase == Phases.PLACEUNITS) {
			units = IO.readInt("Wieviele Einheiten sollen gesetzt werden?" + "("
					+ activePlayer.getSupply() + " Einheiten verfügbar): ");
		}
		return units;
	}

	@Override
	public boolean turnInCards() {
		String submission = IO.readString("Möchten sie Karten eintauschen? (j/n");
		if (submission.equals("j")) {
			return true;
		} else if (submission.equals("n")) {
			return false;
		}

		return false;
	}

	@Override
	public boolean getPlaceMethod() {
		// TODO Auto-generated method stub

		String submission = IO.readString("Sollen die Einheiten Zufällig gesetzt werden? (j/n)");
		if (submission.equals("j")) {
			return true;
		} else if (submission.equals("n")) {
			return false;
		}

		return false;
	}

	@Override
	public int getNumberOfPlayers() {
		int numberOfPlayers;
		do {
			numberOfPlayers = IO.readInt("Wieviele Mitspieler?(2-6)");
		} while (numberOfPlayers <= 1 || numberOfPlayers > 6);
		return numberOfPlayers;
	}

	@Override
	public String getPlayerName(int playernumber) {
		String name = IO.readString("Name für Spieler " + playernumber + ":");
		return name;
	}

	@Override
	public void announceCurrentPlayer(Player activePlayer) {
		System.out.println("\n" + "--------------------------------");
		System.out.println(activePlayer.getName() + " ist an der Reihe.");

	}

	public void battleMsgOffense(int attacks, Territory targetTerritory, int attackOne,
			int attackTwo, int defenseOne, int defenseTwo) {
		if (attacks == 1) {
			System.out.println("Angriff1: " + attackOne + " schlaegt Defensive1: " + defenseOne);

			System.out.println(targetTerritory.getName() + "("
					+ targetTerritory.getOwner().getName() + ") verliert 1 Einheit und "
					+ targetTerritory.getName() + " (" + targetTerritory.getOwner().getName()
					+ ") hat noch: " + targetTerritory.getUnits() + " Einheiten uebrig");
		} else {
			System.out.println("Angriff2: " + attackTwo + " schlaegt Defensive2: " + defenseTwo);

			System.out.println(targetTerritory.getName() + "("
					+ targetTerritory.getOwner().getName() + ") verliert 1 Einheit und "
					+ targetTerritory.getName() + " (" + targetTerritory.getOwner().getName()
					+ ") hat noch: " + targetTerritory.getUnits() + " Einheiten uebrig");
		}
	}

	public void battleMsgDefense(int attacks, Territory originatingTerritory, int attackOne,
			int attackTwo, int defenseOne, int defenseTwo) {
		if (attacks == 1) {
			System.out.println("Defensive1: " + defenseOne + " schlaegt Offennsive1: " + attackOne);

			System.out.println(originatingTerritory.getName() + "("
					+ originatingTerritory.getOwner().getName() + ") verliert 1 Einheit und "
					+ originatingTerritory.getName() + " ("
					+ originatingTerritory.getOwner().getName() + ") hat noch: "
					+ originatingTerritory.getUnits() + " Einheiten uebrig");
		} else {
			System.out.println("Defensive2: " + defenseTwo + " schlaegt Offensive2: " + attackTwo);

			System.out.println(originatingTerritory.getName() + "("

			+ originatingTerritory.getOwner().getName() + ") verliert 1 Einheit und "
					+ originatingTerritory.getName() + " ("
					+ originatingTerritory.getOwner().getName() + ") hat noch: "
					+ originatingTerritory.getUnits() + " Einheiten uebrig");
		}
	}

	public void battleStatusMsg(Territory targetTerritory, Territory originatingTerritory,
			int amountOfAttackers, int amountOfDefenders) {
		System.out.println("\n" + "-----" + originatingTerritory.getName() + "("
				+ originatingTerritory.getOwner().getName() + ") " + amountOfAttackers + " Armee"
				+ "(" + "n" + ")" + " " + " vs. " + amountOfDefenders + " Armee" + "(" + "n" + ")"
				+ " " + targetTerritory.getName() + "(" + targetTerritory.getOwner().getName()
				+ ")" + "-----" + "\n");
	}

	@Override
	public void announceTerritoryCard(TerritoryCard card, Player activePlayer) {
		System.out.println("\n" + activePlayer.getName()
				+ ", Sie haben mindstens ein Land erobert und erhalten eine " + card
				+ " Karte");
	}

	@Override
	public boolean wantToLoad() {
		String wantToLoad = IO.readString("Möchten sie ein Spiel laden? (j/n)");
		return wantToLoad.equals("j");
	}

	@Override
	public boolean wantToSave() {
		String wantToLoad = IO.readString("Möchten sie das Spiel speichern (j/n)");
		return wantToLoad.equals("j");
	}
}
