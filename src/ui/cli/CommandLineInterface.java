package ui.cli;

import java.util.ArrayList;

import domain.Constants.Phases;

import valueobjects.Land;
import valueobjects.Spieler;
import ui.UserInterface;

public class CommandLineInterface implements UserInterface {

	/**
	 * Konstruktor
	 */
	public CommandLineInterface() {
		
	}

	@Override
	public Land getOriginatingCountry(Spieler currentPlayer, Phases phase) {
		// TODO Auto-generated method stub
		ArrayList<Land> laender = currentPlayer.getOwnCountries();
	
		IO.println("Spieler: "+ currentPlayer.getName() +" besitzt folgende Laender: ");
		for(int i = 0; i < laender.size(); i++) {
			IO.println("("+ i+1 +")"+ laender.get(i).getName());
		}

		int auswahl;
		do {
			if(phase == Phases.ATTACK) { 
				auswahl = IO.readInt("Geben Sie das Land an von dem Sie angreifen wollen: ") - 1;
			} else if(phase == Phases.MOVE) { 
				auswahl = IO.readInt("Geben Sie das Land an von dem Sie Einheiten verschieben wollen: ") - 1;
			}
		}while(auswahl > laender.size() && auswahl <= 0);
		return laender.get(auswahl);
	}

	@Override
	public Land getTargetCountry(int phase) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean askForAttack(Spieler spieler) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
