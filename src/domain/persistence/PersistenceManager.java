package domain.persistence;

import domain.Game;

public interface PersistenceManager {
	
	
	/**
	 * Loads a saved Game
	 * @return
	 */
	public Game loadGame(String filename);
	
	/**
	 * Saves the current Game
	 * @param game which should be saved
	 * @return true if game is saved to file, false if not
	 */
	public Boolean saveGame(Game game, String filename);
}
