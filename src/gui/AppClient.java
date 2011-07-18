package gui;

import java.net.UnknownHostException;

import org.eclipse.swt.widgets.Display;

import server.exceptions.NotEnoughPlayersException;
import ui.IO;
import valueobjects.Player;

import commons.Action;
import commons.ClientMethods;
import commons.GameMethods;
import commons.actions.GameStartedAction;
import commons.actions.PlayerJoinedAction;

import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

@SimonRemote
public class AppClient implements ClientMethods {
	
	private static final int DEFAULT_PORT = 50001;
	
	private Lookup connection;
	private GameMethods game;
	
	private Display display;
	private static LoginGUI logingui;
	private static LobbyGUI lobbygui;
	private RiskGUI rFenster;
	
	private boolean creator = false;
	
	public AppClient() {
		display = new Display();
		
		// Show the connect window
		logingui = new LoginGUI(display, this);
		logingui.finalize();
		
		// TODO: check if the window was closed
		
		lobbygui = new LobbyGUI(display, this, game, creator);
		lobbygui.start();
		
		// Show the main risk window
		rFenster = new RiskGUI(display, game);
		rFenster.finalize();
	}
	
	@Override
	public void update(final GameMethods server, Action a) {
		if (a instanceof PlayerJoinedAction) {
			// A player joined
			PlayerJoinedAction pja = (PlayerJoinedAction) a;
			IO.write("Player joined: " + pja.getPlayer().getName());
			
			// Queue the update function to run in the fucking UI thread
			display.asyncExec(new Runnable() {
				public void run() {
					if (lobbygui != null) {
						lobbygui.updateText();
					}
				}
			});
		} else if (a instanceof GameStartedAction) {
			// Game started
			IO.write("Game started.");
			
			display.asyncExec(new Runnable() {
				public void run() {
					if (lobbygui != null) {
						lobbygui.close();
					}
				}
			});
			
		} else {
			IO.write("Unidentified action.");
		}
	}
	
	/**
	 * Create a connection to the server
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 * @throws UnknownHostException
	 */
	public void connect(String ip, String name) throws LookupFailedException, EstablishConnectionFailed, UnknownHostException {
		try {
			connection = Simon.createNameLookup(ip, DEFAULT_PORT);
			game = (GameMethods) connection.lookup("risk");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		// Create player
		game.addPlayer(name, this);
	}

	public void setCreator(boolean creator) {
		this.creator = creator;
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		new AppClient();
	}
}
