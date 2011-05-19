package ui;

//Import phase constants
import static domain.Constants.*;
import valueobjects.Land;
import valueobjects.Spieler;
import domain.Constants.Phases;


public interface UserInterface {

	Land getTargetCountry(Spieler activePlayer, Phases placeunits);

	int getAmountUnit(Spieler activePlayer, Phases placeunits);

	boolean askForAttack(Spieler activePlayer);

	Land getOriginatingCountry(Spieler activePlayer, Phases attack);

	boolean turnInCards();
	
}
