package valueobjects.missions;

import valueobjects.Player;
import domain.TerritoryManager;

public abstract class AbstractMission implements Mission {
	/**
	 * TerritoryManager. Not every subclass initializes this attribute.
	 */
	protected TerritoryManager territoryManager;

	/**
	 * The player who owns this mission
	 */
	protected Player player;

}
