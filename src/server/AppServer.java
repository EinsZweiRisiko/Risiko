package server;

import java.io.IOException;
import java.net.UnknownHostException;

import de.root1.simon.exceptions.NameBindingException;

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
			new GameMethodsImpl("risk", port);
			System.out.println("Listening on port " + port + "...");

		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NameBindingException e) {
			System.err.println(e.getMessage());
		}
	}

}
