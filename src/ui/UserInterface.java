package ui;

import valueobjects.Player;
import valueobjects.Territory;
import domain.Game.Phases;
import domain.PlayerManager;

public interface UserInterface {

	Territory getTargetTerritory(Player activePlayer, Phases placeunits, Territory territory);

	int getAmountUnit(Player activePlayer, Phases placeunits);

	Territory getOriginatingTerritory(Player activePlayer, Phases attack);

	boolean turnInCards();

	boolean getPlaceMethod();

	int getNumberOfPlayers();

	String getPlayerName(int playernumber);

	void announceCurrentPlayer(Player activePlayer);

	boolean askForPhase(Player activePlayer, Phases move);

}
