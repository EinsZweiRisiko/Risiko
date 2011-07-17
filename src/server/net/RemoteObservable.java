package server.net;

import java.util.List;
import java.util.Vector;

public class RemoteObservable implements RemoteObservableIf {

	private boolean changed = false;
	private List<RemoteObserver> observers = new Vector<RemoteObserver>();

	public void addObserver(RemoteObserver observer) {
		if (observer == null) {
            throw new NullPointerException();
		}
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public void deleteObserver(RemoteObserver observer) {
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
		for (RemoteObserver observer:observers) {
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
