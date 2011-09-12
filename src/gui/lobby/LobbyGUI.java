package gui.lobby;

import gui.AppClient;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import server.exceptions.NotEnoughPlayersException;
import valueobjects.Player;
import valueobjects.PlayerCollection;

import commons.GameMethods;

/**
 * LobbyGUI provides a GUI in which Players can wait for other plays till they start the game
 * @author Hendrik
 *
 */
public class LobbyGUI {
	
	private Display display;
	private Shell shell;
//	private AppClient app;
	private GameMethods serverMethods;
	private Composite lobby;
	private Label playerList;
	
	/**
	 * creates a new instance of a Lobby Window
	 * @param display the display which should be used for showing the shell
	 * @param app the calling AppClient
	 * @param serverMethods the instance of the game which should be started
	 */
	public LobbyGUI(Display display, AppClient app, final GameMethods serverMethods) {
		this.display = display;
//		this.app = app;
		this.serverMethods = serverMethods;
		
		shell = new Shell(display, SWT.MIN);
		shell.setText("Lobby");
		// Set background
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		Image bg = new Image(display, "assets/loginbg.png");
		
		shell.setBackgroundImage(bg);
		
		lobby = new Composite(shell, SWT.INHERIT_DEFAULT);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.pack = true;
		rowLayout.justify = false;
		lobby.setLayout(rowLayout);
		
       	// Create text field
       	playerList = new Label(lobby, SWT.INHERIT_NONE);
       	playerList.pack();
		
		// Update the text
		updateText();
		
		// If the player is the first player, show him a start Button.
		if(serverMethods.getPlayers().size() == 1) {
			Button startGame = new Button(lobby,SWT.PUSH);
			startGame.setText("Spiel starten");		
			
			startGame.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseUp(MouseEvent event) {
					try {
						serverMethods.start();
					} catch (NotEnoughPlayersException e) {
						System.out.println(e.getMessage());
					}
				}
		      });
		}
		
		lobby.setBounds(0, 0, 250, 350);
		shell.pack();
		shell.open();
	}
	
	public void start() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * Updates the text field.
	 */
	public void updateText() {
		PlayerCollection players = serverMethods.getPlayers();
		
		String text = "";
		for (Player player:players) {
			text += player.getName() + "\n";
		}
		
		playerList.setText(text);
		playerList.pack();
		lobby.pack();
		shell.update();
	}
	
	public void close() {
		shell.dispose();
	}

}
