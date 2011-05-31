package valueobjects;


/**
 * The Mission interface represents a mission that a player has to accomplish
 * 
 * @author Jannes
 * 
 */
public interface Mission {
	
	/**
	 * Returns whether this mission has been accomplished or not
	 * @return True, if the mission was accomplished
	 */
	public boolean test();
	
}
