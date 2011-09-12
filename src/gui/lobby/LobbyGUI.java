package gui.lobby;

import gui.AppClient;
import gui.MessageDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import server.exceptions.NotEnoughPlayersException;
import valueobjects.Player;

import commons.GameMethods;

/**
 * LobbyGUI provides a GUI in which Players can wait for other plays until they
 * start the game
 * 
 * @author Hendrik, Jannes
 * 
 */
public class LobbyGUI {

	private GameMethods server;
//	private AppClient app;
	private boolean leader;

	private Display display;
	private Shell shell;
	private Label playerList;
	private Button startGame;

	/**
	 * creates a new instance of a Lobby Window
	 * 
	 * @param display
	 *            The display which should be used for showing the shell
	 * @param appClient
	 *            The calling AppClient
	 * @param serverMethods
	 *            The instance of the game which should be started
	 */
	public LobbyGUI(Display display, AppClient appClient,
			GameMethods serverMethods) {
		// Save these variables
		this.display = display;
//		this.app = appClient;
		this.server = serverMethods;
		// Detect if the player is the first player
		leader = server.getPlayers().size() == 1;

		// Create a new window
		shell = new Shell(display);
		shell.setText("Lobby - " + AppClient.name);

		// Set background image
		shell.setBackgroundImage(new Image(display, "assets/loginbg.png"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		// Create the user interface
		buildUserInterface();
		updatePlayerList();
		
		// Connect the event handlers
		connectEventHandlers();
		
		// Set the window size
		shell.pack();
		shell.setMinimumSize(shell.getSize());
	}

	private void buildUserInterface() {
		// Create layout manager
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		shell.setLayout(layout);
		
		// Add text field
		playerList = new Label(shell, SWT.NONE);
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.heightHint = 300;
		gridData.widthHint = 240;
		playerList.setLayoutData(gridData);

		// Add start button, if the player is the game leader
		if (leader) {
			startGame = new Button(shell, SWT.PUSH);
			startGame.setText("Spiel starten");
			shell.setDefaultButton(startGame);
		}
	}

	/**
	 * Connect the event handlers
	 */
	private void connectEventHandlers() {
		// Start game (feature detecting the button instead of relying on the
		// leader status)
		if (startGame != null) {
			startGame.addListener(SWT.Selection, new Listener() {

				public void handleEvent(Event event) {
					try {
						server.start();
					} catch (NotEnoughPlayersException e) {
						new MessageDialog(shell, SWT.ICON_WARNING, "Error", e
								.getMessage());
					}
				}

			});
		}

		// Quit the program on window close
		shell.addListener(SWT.Close, new Listener() {

			public void handleEvent(Event event) {
				// TODO disconnect
				System.exit(0);
			}

		});
	}

	/**
	 * Updates the text field.
	 */
	public void updatePlayerList() {
		String text = "";
		for (Player player : server.getPlayers()) {
			text += player.getName() + "\n";
		}

		playerList.setText(text);
	}

	/**
	 * UI loop
	 */
	public void pumpLoop() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Close the lobby window
	 */
	public void closeWindow() {
		// We can't use shell.close() because the event handler for that would
		// cause us to disconnect from the server.
		shell.dispose();
	}

}
