package domain;

/**
 * The Mission interface represents a mission that a player has to accomplish
 * 
 * @author Jannes
 * 
 */
public interface Mission {

//	private Player owner;
//	private String text;

	/**
	 * Returns whether this mission has been accomplished or not
	 * 
	 * @return True, if the mission was accomplished
	 */
	public boolean test();

}
