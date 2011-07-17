package commons;

import java.util.Observable;
import java.util.Observer;

import de.root1.simon.annotation.SimonRemote;

/**
 * Defines the methods that the client provides.
 *
 */
@SimonRemote
public interface ClientMethods extends Observer {

	public void print(String msg);
	
	@Override
	public void update(Observable o, Object a);
	
}
