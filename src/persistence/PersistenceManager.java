package persistence;

import server.GameMethodsImpl;

public interface PersistenceManager {

	/**
	 * Loads a saved Game
	 * 
	 * @return
	 */
	public GameMethodsImpl loadGame(String filename);

	/**
	 * Saves the current Game
	 * 
	 * @param game
	 *            which should be saved
	 * @return true if game is saved to file, false if not
	 */
	public Boolean saveGame(GameMethodsImpl game, String filename);
}
