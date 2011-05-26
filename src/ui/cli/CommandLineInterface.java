package ui.cli;

import java.util.ArrayList;

import ui.UserInterface;
import valueobjects.Player;
import valueobjects.Territory;
import domain.Game.Phases;
import domain.PlayerManager;

public class CommandLineInterface implements UserInterface {

	/**
	 * Konstruktor
	 */
	public CommandLineInterface() {

	}

	@Override
	public Territory getOriginatingCountry(Player currentPlayer, Phases phase) {
		// TODO Auto-generated method stub
		ArrayList<Territory> laender = currentPlayer.getTerritories();

		IO.println("Spieler: " + currentPlayer.getName() + " besitzt folgende Laender: ");
		for (int i = 0; i < laender.size(); i++) {
			IO.println("(" + i + 1 + ")" + laender.get(i).getName());
		}

		int auswahl = 0;
		do {
			if (phase == Phases.ATTACK) {
				auswahl = IO.readInt("Geben Sie das Land an von dem Sie angreifen wollen: ") - 1;
			} else if (phase == Phases.MOVE) {
				auswahl = IO
						.readInt("Geben Sie das Land an von dem Sie Einheiten verschieben wollen: ") - 1;
			}
		} while (auswahl > laender.size() && auswahl <= 0);
		return laender.get(auswahl);
	}

	@Override
	public Territory getTargetCountry(Player activePlayer, Phases placeunits) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean askForAttack(Player spieler) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public int getAmountUnit(Player activePlayer, Phases placeunits) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean turnInCards() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void newPlayer(PlayerManager playerMgr) {
		/*
		 * Abfrage der Mitspieler. Hierbei muss eine Zahl zwischen 2 und 6 angegeben werden. Nachdem
		 * der Anwender dies getan hat wird die Array-Liste Spieler[] mit Anzahl der Mitspielenden
		 * initialisiert.
		 */
		
		PlayerManager playerManager = playerMgr;
		
		int numberOfPlayer;
		
		do{
			numberOfPlayer = IO.readInt("Wieviele Spieler= (2-6)");
		} while (numberOfPlayer <= 1 || numberOfPlayer > 6);
		
		Player[] player = new Player[numberOfPlayer];
		
		for (int i = 0; i < numberOfPlayer; i++){
			String name = IO.readString("Name Spieler " + (i + 1) + ": ");
			player[i] = new Player(name);
		}
		
		IO.println("Eine neue Runde wird gestartet es treten an :");
		for (int i = 0; i < numberOfPlayer; i++) {
			IO.println(player[i].getName());
		}
		
		playerManager.setPlayers(player);
	}

	@Override
	public boolean getPlaceMethod() {
		// TODO Auto-generated method stub
		
		String eingabe = IO.readString("Sollen die Einheiten Zufällig gesetzt werden? (j/n)");
		if(eingabe == "j") {
			return true;
		} else if(eingabe == "n") { return false; }
		
		return false;
	}

}
