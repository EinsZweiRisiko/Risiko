package server.remoteexceptions;

import de.root1.simon.exceptions.SimonRemoteException;

/**
 * This Exception is thrown if the Server already got 6 Players or if the game
 * was already started.
 * 
 * @author Jannes, Hendrik
 * 
 */
public class ServerFullException extends SimonRemoteException {

	private static final long serialVersionUID = 4894874648505078537L;

	public ServerFullException() {
		super("The game is already in progress or it is full.");
	}
}
