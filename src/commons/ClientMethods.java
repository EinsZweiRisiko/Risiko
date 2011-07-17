package commons;

import server.net.RemoteObservable;
import server.net.RemoteObserver;
import de.root1.simon.annotation.SimonRemote;

/**
 * Defines the methods that the client provides.
 *
 */
@SimonRemote
public interface ClientMethods extends RemoteObserver {

	public void print(String msg);
	
	@Override
	public void update(RemoteObservable o, Object a);
	
}
