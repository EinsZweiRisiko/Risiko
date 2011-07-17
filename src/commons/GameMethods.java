package commons;

import java.util.HashMap;
import java.util.List;

import server.GameMethodsImpl.Action;
import server.Mission;
import server.exceptions.NotEnoughPlayersException;
import server.remoteexceptions.ServerFullException;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.SimonRemoteException;

@SimonRemote
public interface GameMethods {
	
	// Setup
	public void addPlayer(String name, ClientMethods client) throws ServerFullException;
	
	public void save();
	public void load();
	
	public void start() throws NotEnoughPlayersException;
	public PlayerCollection getPlayers();
	
	public boolean isOver();
	public Player getWinner();
	public Player getActivePlayer();
	public Action getNextAction();
	public void placeStartUnitsRandomly();
	
	public Mission getMyMission(Player player);
	public List<BonusCard> getMyBonusCards(Player player);
	
	public HashMap<String, Territory> getTerritories();
	public List<Territory> getMyTerritories(Player player);
	public List<Territory> getMyTerritoriesForAttacking(Player player);
	public List<Territory> getMyTerritoriesForMoving(Player player);
	
	public List<Territory> getOpposingNeighborsOf(Territory territory);
	public List<Territory> getSimilarNeighborsOf(Territory territory);
	
	// Spielphasen
	public void redeemBonusCards(List<BonusCard> cards);
	public void placeUnits(Territory territory, int amount);

	public void attack(Territory attackingTerritory, Territory attackedTerritory, int amount);
	// Clients müssen Observer sein, die gucken, ob
	//  sie angegriffen werde
	//  oder dran sind
	public void move(Territory source, Territory target, int amount) throws SimonRemoteException;
	
	
	/* Notifications:
	 * 
	 * Nächster Spieler ist dran
	 * Einheiten platziert
	 * Einheiten verschoben
	 * Einheiten besiegt
	 * Land ändert besitzer
	 * Karten eingetauscht
	 * Du/jemand wirst angegriffen
	 * 
	 */
}
