package ui;

import java.net.UnknownHostException;

import commons.GameMethods;

import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

public class ClientApp {
	
	private Lookup connection;
	private GameMethods game;
	
	public ClientApp() {
		try {
			// Connect to the server
			connect();
			game.login("TestUser", new ClientMethodsImpl());
			
			game.print("Hello World");
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (LookupFailedException e) {
			System.err.println(e.getMessage());
		} catch (EstablishConnectionFailed e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Create a connection to the server
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 * @throws UnknownHostException
	 */
	private void connect() throws LookupFailedException, EstablishConnectionFailed, UnknownHostException {
		connection = Simon.createNameLookup("localhost", 50001);
		game = (GameMethods) connection.lookup("risk");
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientApp();
	}
}
