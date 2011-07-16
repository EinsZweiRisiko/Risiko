package domain;

import java.rmi.Remote;
import java.util.HashMap;
import java.util.List;

import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;
import domain.Game.Action;

public interface GameInterface extends Remote {
	
	public void addPlayer(String name);
	public void save();
	public void load();
	public void start();
	public PlayerCollection getPlayers();
	
	public boolean isOver();
	public Player getWinner();
	public Player getActivePlayer();
	public Action getNextAction();
	public void placeStartUnitsRandomly();
	
	public void redeemBonusCards(List<BonusCard> cards);
	public void placeUnits(Territory territory, int amount);

	public HashMap<String,Territory> getTerritories();
	public Mission getMission(Player player);
	public List<Territory> getMyTerritories(Player player);
	public List<Territory> getMyNeighborsOf(Territory territory);
	public List<Territory> getOpposingNeighborsOf(Territory territory);
	
	public void attack(Territory attackingTerritory, Territory attackedTerritory, int amount);
	// Clients müssen Observer sein, die gucken, ob
	//  sie angegriffen werde
	//  oder dran sind
	public void move(Territory source, Territory target, int amount);
	
	
	
}
