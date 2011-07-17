package server.net;

import java.util.List;
import java.util.Vector;

public class RemoteObservable {

	private List<RemoteObserver> observers = new Vector<RemoteObserver>();

	public void addObserver(RemoteObserver observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	
}
