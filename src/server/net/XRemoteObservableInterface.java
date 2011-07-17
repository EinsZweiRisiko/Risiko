package server.net;

public interface XRemoteObservableInterface {
	
	public void addObserver(XRemoteObserver observer);
	public void deleteObserver(XRemoteObserver observer);
	public void notifyObservers();
	public void notifyObservers(Object arg);
	public void setChanged();
	
}
