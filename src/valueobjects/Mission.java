package valueobjects;

/**
 * The Mission class represents a mission that a player has to accomplish
 * 
 * @author Jannes
 * 
 */
public abstract class Mission {
	
	/**
	 * Constructor
	 */
	public Mission(TerritoryManager territoryManager, Player player) {
		
	}
	
	/**
	 * Returns whether this mission has been accomplished
	 * @return
	 */
	public boolean test() {
		return false;
	}
	
}
