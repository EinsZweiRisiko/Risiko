package server.net;

import java.util.List;
import java.util.Vector;

public class XRemoteObservable implements XRemoteObservableInterface {

	private boolean changed = false;
	private List<XRemoteObserver> observers = new Vector<XRemoteObserver>();

	public void addObserver(XRemoteObserver observer) {
		if (observer == null) {
            throw new NullPointerException();
		}
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public void deleteObserver(XRemoteObserver observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	public void notifyObservers(Object arg) {
		if (!changed) {
			return;
		}
		// Notify all observers
		for (XRemoteObserver observer:observers) {
			observer.update(this, arg);
		}
		clearChanged();
	}
	
	public void setChanged() {
		changed = true;
	}
	
	protected void clearChanged() {
		changed = false;
	}
	
}
