package ui;

import java.util.ArrayList;
import java.util.HashSet;

import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.Territory;
import domain.Game.Phases;

public interface UserInterface {

	boolean wantToLoad ();

	Territory getTargetTerritory(Player activePlayer, Phases placeunits, Territory territory);

	int getAmountUnit(Player activePlayer,Territory originatingTerritory, Territory targetTerritory, Phases placeunits);

	Territory getOriginatingTerritory(Player activePlayer, Phases attack);

	boolean turnInCards();

	boolean getPlaceMethod();

	int getNumberOfPlayers();

	String getPlayerName(int playernumber);

	void announceCurrentPlayer(Player activePlayer);

	boolean askForPhase(Player activePlayer, Phases move);
	
	void battleMsgOffense(int attacks, Territory targetTerritory, int attackOne, int attackTwo, int defenseOne, int defenseTwo);
	
	void battleMsgDefense(int attacks, Territory originatingTerritory, int attackOne, int attackTwo, int defenseOne, int defenseTwo);
	
	void battleStatusMsg(Territory targetTerritory, Territory originatingTerritory, int amountOfAttackers, int amountOfDefenders);

	void announceBonusCard(BonusCard card, Player activePlayer);

	void announceRedeeming(Player activePlayer);

	HashSet<BonusCard> askForBonusCards();

	boolean wantToSave();

	void announceSuccesfulSave();
	
	int getEmptyTerritoryManualSet(ArrayList<Territory> territory);
	
}
