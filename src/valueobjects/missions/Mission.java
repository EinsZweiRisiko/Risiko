package valueobjects.missions;

import valueobjects.Player;
import domain.TerritoryManager;

/**
 * The Mission class represents a mission that a player has to accomplish
 * 
 * @author Jannes
 * 
 */
public abstract class Mission {
	
	/**
	 * TerritoryManager. Not every subclass initializes this attribute.
	 */
	TerritoryManager territoryManager;

	/**
	 * The player who owns this mission
	 */
	Player player;
	
	/**
	 * Returns whether this mission has been accomplished or not
	 * @return True, if the mission was accomplished
	 */
	abstract public boolean test();
	
}
