package server.net;

public interface RemoteObservableIf {
	
	public void addObserver(RemoteObserver observer);
	public void deleteObserver(RemoteObserver observer);
	public void notifyObservers();
	public void notifyObservers(Object arg);
	public void setChanged();
	
}
