package server.remoteexceptions;

import de.root1.simon.exceptions.SimonRemoteException;

/**
 * This exception is thrown if a client tries to connect without a player name.
 * 
 * @author Jannes
 * 
 */
public class NoNameException extends SimonRemoteException {

	private static final long serialVersionUID = 2837050188548997353L;

	public NoNameException() {
		super("Please specify a name.");
	}
}
