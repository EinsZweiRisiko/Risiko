package ui;

import valueobjects.Player;
import valueobjects.Territory;
import domain.Game.Phases;

public interface UserInterface {

	Territory getTargetCountry(Player activePlayer, Phases placeunits);

	int getAmountUnit(Player activePlayer, Phases placeunits);

	boolean askForAttack(Player activePlayer);

	Territory getOriginatingCountry(Player activePlayer, Phases attack);

	boolean turnInCards();

}
