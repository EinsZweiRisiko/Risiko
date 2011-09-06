package gui;

import java.net.UnknownHostException;

import org.eclipse.swt.widgets.Display;

import server.remoteexceptions.NoNameException;
import server.remoteexceptions.ServerFullException;
import valueobjects.Player;
import valueobjects.Territory;

import commons.Action;
import commons.ClientMethods;
import commons.GameMethods;
import commons.actions.AttackAction;
import commons.actions.GameStartedAction;
import commons.actions.NextPlayerAction;
import commons.actions.PhaseAction;
import commons.actions.PlayerJoinedAction;
import commons.actions.TerritoryUnitsChangedAction;

import cui.IO;
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
	private Player me;

	public AppClient() {
		display = new Display();

		// Show the connect window
		logingui = new LoginGUI(display, this);
		logingui.finalize();

		// TODO: check if the window was closed

		lobbygui = new LobbyGUI(display, this, game);
		lobbygui.start();

		// Show the main risk window
		rFenster = new RiskGUI(display, this, game);
		rFenster.start();
	}

	@Override
	public void update(final GameMethods server, Action a) {


		if (a instanceof PlayerJoinedAction) {
			// A player joined
			PlayerJoinedAction pja = (PlayerJoinedAction) a;
			IO.write("Player joined: " + pja.getPlayer().getName());

			// Queue the update function to run in the UI thread
			display.asyncExec(new Runnable() {
				public void run() {
					lobbygui.updateText();
				}
			});
		} else if (a instanceof GameStartedAction) {
			// Game started
			IO.write("Game started.");

			display.asyncExec(new Runnable() {
				public void run() {
					lobbygui.close();
				}
			});
		} else if (a instanceof NextPlayerAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updateCurrentPlayer();
				}
			});
		} else if (a instanceof PhaseAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updatePhase();
				}
			});
		} else if (a instanceof TerritoryUnitsChangedAction) {
			final Territory t = ((TerritoryUnitsChangedAction) a).getTerritory();
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updateTerritory(t);
				}
			});
		} else if (a instanceof AttackAction ) {
			final Territory t = ((AttackAction) a).getAttackedTerritory();	
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.defend(t);
				}
			});
		}else {
			IO.write("Unidentified action.");
		}
	}

	/**
	 * Create a connection to the server
	 * 
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 * @throws UnknownHostException
	 */
	public void connect(String ip, String name) throws LookupFailedException,
	EstablishConnectionFailed, UnknownHostException,
	ServerFullException, NoNameException {
		if (name.trim().isEmpty()) {
			throw new NoNameException();
		}

		connection = Simon.createNameLookup(ip, DEFAULT_PORT);
		game = (GameMethods) connection.lookup("risk");

		// Create player
		me = game.addPlayer(name, this);
	}

	/**
	 * Returns the instance of the player that uses this GUI.
	 * 
	 * @return
	 */
	public Player getClient() {
		return me;
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new AppClient();
	}

}
