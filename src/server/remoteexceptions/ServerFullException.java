package server.remoteexceptions;

import de.root1.simon.exceptions.SimonRemoteException;

public class ServerFullException extends SimonRemoteException {

	private static final long serialVersionUID = 4894874648505078537L;

	public ServerFullException() {
		super("You can't connect to this server.");
	}
}
