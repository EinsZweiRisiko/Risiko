package gui;

import gui.lobby.LobbyGUI;
import gui.login.LoginGUI;
import gui.risk.RiskGUI;

import java.net.UnknownHostException;

import org.eclipse.swt.widgets.Display;

import server.AppServer;
import server.remoteexceptions.NoNameException;
import server.remoteexceptions.ServerFullException;
import valueobjects.Player;

import commons.Action;
import commons.ClientMethods;
import commons.GameMethods;
import commons.actions.AttackAction;
import commons.actions.BonusCardAction;
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

	public static String name = "ZwoEinsRisiko";

	private Lookup connection;
	private GameMethods server;
	private Player player;

	private Display display;
	private LobbyGUI lobbyWindow;
	private RiskGUI riskWindow;

	/**
	 * Main
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) {
		new AppClient();
	}

	/**
	 * Constructor
	 */
	public AppClient() {
		display = new Display();

		// Show the login window
		new LoginGUI(display, this);

		// Create the lobby and the risk board
		lobbyWindow = new LobbyGUI(display, this, server);
		riskWindow = new RiskGUI(display, this, server);
		
		// Show the lobby and then the risk board
		lobbyWindow.pumpLoop();
		riskWindow.pumpLoop();
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
		server = (GameMethods) connection.lookup("risk");

		// Create player
		player = server.addPlayer(name, this);
	}

	/**
	 * Returns the instance of the player that uses this GUI.
	 * 
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Action handler. Gets called by the server.
	 * 
	 * @param server
	 *            GameMethods instance over the network
	 * @param action
	 *            Action that the server sent to us
	 */
	@Override
	public void update(final GameMethods server, final Action action) {
		// TODO remove debug stuff
		System.out.println("[Action] " + action.getClass().getSimpleName());

		if (action instanceof PlayerJoinedAction) {
			// Queue the update function to run in the UI thread
			display.asyncExec(new Runnable() {
				public void run() {
					// A player joined, so we have to update the player list
					lobbyWindow.updatePlayerList();
				}
			});
		} else if (action instanceof GameStartedAction) {
			// Game started
			display.asyncExec(new Runnable() {
				public void run() {
					lobbyWindow.closeWindow();
					// TODO Because of this we probably needed to instantiate the risk window earlier
					riskWindow.updateCurrentPlayer(((GameStartedAction) action)
							.getPlayer());
					riskWindow.updatePhase(((GameStartedAction) action)
							.getPhase());
				}
			});
		} else if (action instanceof NextPlayerAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow.updateCurrentPlayer(((NextPlayerAction) action)
							.getPlayer());
				}
			});
		} else if (action instanceof PhaseAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow.updatePhase(((PhaseAction) action).getPhase());
				}
			});
		} else if (action instanceof TerritoryUnitsChangedAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow
							.updateTerritory(((TerritoryUnitsChangedAction) action)
									.getTerritory());
				}
			});
		} else if (action instanceof AttackAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow.defend(((AttackAction) action)
							.getAttackedTerritory());
				}
			});
		} else if (action instanceof EventBoxAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow.openEventBox(
							((EventBoxAction) action).getPlayer(),
							((EventBoxAction) action).getMsg());
				}
			});
		} else if (action instanceof PrepareGUIAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow.prepare();
				}
			});
		} else if (action instanceof BonusCardAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					riskWindow.updateBonusCard(((BonusCardAction) action)
							.getPlayer());
				}
			});
		} else {
			System.out.println("Unidentified action.");
		}
	}

}
