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
		rFenster = new RiskGUI(display, this, game);
		
		lobbygui = new LobbyGUI(display, this, game);
		lobbygui.start();

		// Show the main risk window
		rFenster.start();
	}

	@Override
	public void update(final GameMethods server, final Action a) {
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
			display.asyncExec(new Runnable() {
				public void run() {
					lobbygui.close();
					System.out.println(((GameStartedAction) a). getPlayer().getName() + "<--- GamestartedAction PLAYER");
					//rFenster.updateCurrentPlayer(((GameStartedAction) a). getPlayer());
					rFenster.updatePhase(((GameStartedAction) a).getPhase(), ((GameStartedAction) a).getPlayer(), ((GameStartedAction) a).getPlayers());
				}
			});
		} else if (a instanceof NextPlayerAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updateCurrentPlayer(((NextPlayerAction) a).getPlayer());
				}
			});
		} else if (a instanceof PhaseAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updatePhase(((PhaseAction) a).getPhase(), ((PhaseAction) a).getPlayer(), ((PhaseAction) a).getPlayers());
				}
			});
		} else if (a instanceof TerritoryUnitsChangedAction) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updateTerritory(((TerritoryUnitsChangedAction) a).getTerritory());
				}
			});
		} else if (a instanceof AttackAction ) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.defend(((AttackAction) a).getAttackedTerritory());
				}
			});
		}else if (a instanceof EventBoxAction ) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.openEventBox(((EventBoxAction) a).getPlayer(), ((EventBoxAction) a).getMsg());
				}
			});
		} else if (a instanceof PrepareGUIAction ) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.prepare();
				}
			});
		}else if (a instanceof BonusCardAction ) {
			display.asyncExec(new Runnable() {
				public void run() {
					rFenster.updateBonusCard(((BonusCardAction) a).getPlayer());
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
