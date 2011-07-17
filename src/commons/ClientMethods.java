package commons;

import de.root1.simon.annotation.SimonRemote;

/**
 * Defines the methods that the client provides.
 *
 */
@SimonRemote
public interface ClientMethods {

	public void print(String msg);
	
	public void update(GameMethods o, Object a);
	
}
