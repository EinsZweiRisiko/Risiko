package server.missions;

import java.util.List;

import server.Mission;
import valueobjects.Player;
import valueobjects.Territory;

public class TerritoryHurdleMission implements Mission {

	/**
	 * The player who owns this mission
	 */
	protected Player player;
	private int territoryHurdle;
	private int territoryUnitHurdle;
	
	// TODO javadoc
	public TerritoryHurdleMission(Player player, int territoryHurdle, int territoryUnitHurdle) {
		this.player = player;
		this.territoryHurdle = territoryHurdle;
		this.territoryUnitHurdle = territoryUnitHurdle;
	}
	
	public TerritoryHurdleMission(Player player, int territoryHurdle) {
		this.player = player;
		this.territoryHurdle = territoryHurdle;
	}
	
	/**
	 * Returns whether this mission has been accomplished or not
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		// Get all territories that are owned by the player
		List<Territory> playerTerritories = player.getTerritories();
		
		// Check the territory hurdle
		if (playerTerritories.size() < territoryHurdle) {
			return false;
		}
		
		// Check the unit hurdle for each territory
		for (Territory territory : playerTerritories) {
			if (territory.getUnitCount() < territoryUnitHurdle) {
				return false;
			}
		}
		
		// All conditions are fulfilled
		return true;
	}

}
