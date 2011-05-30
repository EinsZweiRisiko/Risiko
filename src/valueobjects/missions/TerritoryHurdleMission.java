package valueobjects.missions;

import java.util.ArrayList;

import valueobjects.Mission;
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
		// TODO 2nd constructor without territoryUnitHurdle 
		this.player = player;
		this.territoryHurdle = territoryHurdle;
		this.territoryUnitHurdle = territoryUnitHurdle;
	}
	
	/**
	 * Returns whether this mission has been accomplished or not
	 * @return True, if the mission was accomplished
	 */
	@Override
	public boolean test() {
		// Get all territories that are owned by the player
		ArrayList<Territory> playerTerritories = player.getTerritories();
		
		// Check the territory hurdle
		if (playerTerritories.size() < territoryHurdle) {
			return false;
		}
		
		// Check the unit hurdle for each territory
		for (Territory territory : playerTerritories) {
			if (territory.getUnits() < territoryUnitHurdle) {
				return false;
			}
		}
		
		// All conditions are fulfilled
		return true;
	}

}
