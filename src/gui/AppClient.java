package gui;

import java.net.UnknownHostException;

import org.eclipse.swt.widgets.Display;

import server.exceptions.NotEnoughPlayersException;
import ui.IO;

import commons.ClientMethods;
import commons.GameMethods;

import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

public class AppClient {
	
	private Lookup connection;
	private GameMethods game;
	private static final int DEFAULT_PORT = 50001;
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		new AppClient();
	}
	
	/**
	 * Constructor initiates a connection to the server
	 */
	public AppClient() {
		Display display = new Display();
		
		// Show the connect window
		LoginGUI logingui = new LoginGUI(display, this);
		logingui.finalize();

		game.addPlayer("Player 2", new ClientMethodsImpl());
		game.addPlayer("Player 3", new ClientMethodsImpl());
		
		// TODO: Only the first player should be allowed to start the game
		try {
			game.start();
		} catch (NotEnoughPlayersException e) {
			IO.writeError(e.getMessage());
			System.exit(1);
		}
		
		// Show the main risk window
		RiskGUI rFenster = new RiskGUI(display, game);
		rFenster.finalize();
		
		// Close the risk window after the game has finished
		display.dispose();
	}
	
	
	/**
	 * Create a connection to the server
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 * @throws UnknownHostException
	 */
	public void connect(String ip, String name) throws LookupFailedException, EstablishConnectionFailed, UnknownHostException {
		connection = Simon.createNameLookup(ip, DEFAULT_PORT);
		game = (GameMethods) connection.lookup("risk");
		
		// Create player
		ClientMethods client = new ClientMethodsImpl();
		game.addPlayer(name, client);
		
		game.addObserver(client);
	}
}
