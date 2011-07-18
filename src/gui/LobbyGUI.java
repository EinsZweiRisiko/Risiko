package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import server.exceptions.NotEnoughPlayersException;
import ui.IO;
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
	private AppClient app;
	private GameMethods game;
	private Composite lobby;
	private Label playerList;
	
	/**
	 * creates a new instance of a Lobby Window
	 * @param display the display which should be used for showing the shell
	 * @param app the calling AppClient
	 * @param game the instance of the game which should be started
	 * @param creator true == this GUI belongs to a user who is creating the game false == this GUI belongs to a user who is joining a game
	 */
	public LobbyGUI(Display display, final AppClient app, final GameMethods game){
		this.display = display;
		this.app = app;
		this.game = game;
		
		shell = new Shell(display, SWT.MIN);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		shell.setText("EinsZweiRisiko |Lobby");
		
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
		
		// if joining Player is a Creator, show him a start Button.
		if(game.getPlayers().size() == 1) {
			Button startGame = new Button(lobby,SWT.PUSH);
			startGame.setText("Spiel starten");		
			
			startGame.addMouseListener(new MouseListener() {
				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseUp(MouseEvent e) {
					try {
						game.start();
					} catch (NotEnoughPlayersException e1) {
						IO.writeError(e1.getMessage());
					}
				}
		      });
		}
		
		lobby.setBounds(0, 0, 250, 350);
		shell.pack();
		center(shell);
		shell.open();
	}
	
	public void start() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	/**
	 * Updates the text field.
	 */
	public void updateText() {
		PlayerCollection players = game.getPlayers();
		
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
