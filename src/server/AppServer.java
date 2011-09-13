package server;

import java.io.IOException;
import java.net.UnknownHostException;
import org.eclipse.swt.widgets.Display;


import server.gui.ServerMonitor;

import de.root1.simon.exceptions.NameBindingException;

/**
 * The Server Application to start the Server
 * 
 * @author Timur
 *
 */
public class AppServer {

	public static final int DEFAULT_PORT = 50001;

	/**
	 * Main
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String args[]) {
		start();
	}

	/**
	 * Start the server instance and listen on the default port
	 */
	public static void start() {
		start(DEFAULT_PORT);
	}

	/**
	 * Start the server instance and listen on the specified port
	 */
	public static void start(int port) {
		try {
			Display display = new Display();
			GameMethodsImpl gameImpl = new GameMethodsImpl("risk", port);
			
			//create a server monitor to observe the game
			ServerMonitor serverM = new ServerMonitor(gameImpl, display);
			gameImpl.setServerMonitor(serverM);
			gameImpl.setDisplay(display);
			
			serverM.updateConsole("Listening on port " + port + "...");
			serverM.start();
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NameBindingException e) {
			System.err.println(e.getMessage());
		}
	}
}
