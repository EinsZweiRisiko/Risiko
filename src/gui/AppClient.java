package gui;

import gui.lobby.LobbyGUI;
import gui.login.LoginGUI;
import gui.risk.RiskGUI;

import java.net.UnknownHostException;

import org.eclipse.swt.widgets.Display;

import server.AppServer;
import server.GameMethodsImpl.Phase;
import server.remoteexceptions.NoNameException;
import server.remoteexceptions.ServerFullException;
import valueobjects.Player;
import valueobjects.Territory;

import commons.Action;
import commons.ClientMethods;
import commons.GameMethods;
import commons.actions.AttackAction;
import commons.actions.EventBoxAction;
import commons.actions.GameStartedAction;
import commons.actions.NextPlayerAction;
import commons.actions.PhaseAction;
import commons.actions.PlayerJoinedAction;
import commons.actions.PrepareGUIAction;
import commons.actions.TerritoryUnitsChangedAction;

import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

@SimonRemote
public class AppClient implements ClientMethods {

	private Lookup connection;
	private GameMethods game;

	private Display display;
	private static LoginGUI logingui;
	private static LobbyGUI lobbygui;
	private RiskGUI rFenster;
	private Player me;
	public static String name = "ZwoEinsRisiko";

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
			System.out.println("Player joined: " + pja.getPlayer().getName());

			// Queue the update function to run in the UI thread
			display.asyncExec(new Runnable() {
				public void run() {
					lobbygui.updateText();
				}
			});
		} else if (a instanceof GameStartedAction) {
			// Game started
			System.out.println("Game started.");
			final Player player = ((GameStartedAction) a). getPlayer();
			final Phase phase = ((GameStartedAction) a).getPhase();
			display.asyncExec(new Runnable() {
				public void run() {
					lobbygui.close();
					System.out.println(player.getName() + "<--- GamestartedAction PLAYER");
					rFenster.updateCurrentPlayer(player);
					rFenster.updatePhase(phase);
				}
			});
		} else if (a instanceof NextPlayerAction) {
			final Player player = ((NextPlayerAction) a).getPlayer();
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updateCurrentPlayer(player);
				}
			});
		} else if (a instanceof PhaseAction) {
			final Phase phase = ((PhaseAction) a).getPhase();
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updatePhase(phase);
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
		}else if (a instanceof EventBoxAction ) {
			final Player player = ((EventBoxAction) a).getPlayer();	
			final String msg = ((EventBoxAction) a).getMsg();
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.openEventBox(player, msg);
				}
			});
		} else if (a instanceof PrepareGUIAction ) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.prepare();
				}
			});
		}else {
			
			System.out.println("Unidentified action.");
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

		connection = Simon.createNameLookup(ip, AppServer.DEFAULT_PORT);
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
