package commons;

import java.util.List;
import java.util.Map;

import server.GameMethodsImpl.Phase;
import server.TerritoryManager;
import server.exceptions.NotEnoughPlayersException;
import server.missions.Mission;
import server.remoteexceptions.ServerFullException;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.SimonRemoteException;

@SimonRemote
public interface GameMethods {

	// Setup/Observer
	public Player addPlayer(String name, ClientMethods client) throws ServerFullException;
	public void deletePlayer(ClientMethods clients);

	// Load and saving
	public void save();
	public void load();

	public void start() throws NotEnoughPlayersException;
	public PlayerCollection getPlayers();

	public boolean isOver();
	public Player getWinner();
	public Player getActivePlayer();
	public void nextPhase();
	public void placeStartUnitsRandomly();

	public Mission getMyMission(Player player);
	public List<BonusCard> getMyBonusCards(Player player);

	public Map<String, Territory> getTerritories();
	public List<Territory> getMyTerritories(Player player);
	public List<Territory> getMyTerritoriesForAttacking(Player player);
	public List<Territory> getMyTerritoriesForMoving(Player player);

	public List<Territory> getOpposingNeighborsOf(Territory territory);
	public List<Territory> getSimilarNeighborsOf(Territory territory);

	// Spielphasen
	public void redeemBonusCards(List<BonusCard> cards);
	public void placeUnits(String territory, int amount);

	public void attack(Territory sourceTerritory, Territory targetTerritory, int amount);
	public void move(Territory sourceTerritory, Territory targetTerritory, int amount) throws SimonRemoteException;
	public void defend(Territory sourceTerritory, Territory targetTerritory, int amount);
	
	public Phase getPhase();
	public void endAttackPhase();
	public void nextPlayer();
	public TerritoryManager getTerritoryManager();
	public void endMovementPhase();
	public void supplyChanged(Player player);

	/* Notifications:
	 * 
	 * xSpieler hinzugefügt
	 * xSpiel fängt an
	 * xNächster Spieler ist dran
	 * Einheiten platziert
	 * Einheiten verschoben
	 * Einheiten besiegt
	 * Land ändert besitzer
	 * Karten eingetauscht
	 * Du/jemand wirst angegriffen
	 * 
	 */
}
