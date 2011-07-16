package ausprobieren.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
	
	public void method() throws RemoteException;
	public int calc(int a, int b) throws RemoteException;
	
}
