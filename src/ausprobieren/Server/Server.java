package ausprobieren.Server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements ServerInterface {
	
	Server() throws RemoteException
	{
		super();
	}

	public static void main(String[] args)
	{
		try {
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		}

		catch (RemoteException ex) {
			System.out.println(ex.getMessage());
		}
		try {
			Naming.rebind("Server", new Server());
			System.out.println("Server started");
		}
		catch (MalformedURLException ex) {
			System.out.println(ex.getMessage());
		}
		catch (RemoteException ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Override
	public void method() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int calc(int a, int b) throws RemoteException {
		// TODO Auto-generated method stub
		return a + b;
	}

}
