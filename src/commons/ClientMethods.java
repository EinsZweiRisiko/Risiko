package commons;

import de.root1.simon.annotation.SimonRemote;

/**
 * Defines the methods that the client provides.
 *
 */
@SimonRemote
public interface ClientMethods {

	public void update(GameMethods server, Action a);
	
}
