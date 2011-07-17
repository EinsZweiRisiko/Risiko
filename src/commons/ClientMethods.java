package commons;

import server.net.XRemoteObservable;
import server.net.XRemoteObserver;
import de.root1.simon.annotation.SimonRemote;

/**
 * Defines the methods that the client provides.
 *
 */
@SimonRemote
public interface ClientMethods extends XRemoteObserver {

	public void print(String msg);
	
	@Override
	public void update(XRemoteObservable o, Object a);
	
}
