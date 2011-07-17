package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import server.GameMethodsImpl;
import server.exceptions.NotEnoughPlayersException;
import ui.IO;
import valueobjects.PlayerCollection;

import commons.GameMethods;

/**
 * LobbyGUI provides a GUI in which Players can wait for other plays till they start the game
 * @author Hendrik
 *
 */
public class LobbyGUI {
	
	private Shell shell;
	private AppClient app;
	
	public LobbyGUI(Display display,final AppClient app,GameMethods game,boolean creator){
		this.app = app;
		
		shell = new Shell(display);
		shell.setText("EinsZweiRisiko -- Lobby");
		
		GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.horizontalSpacing = 10;
        gridLayout.verticalSpacing = 10;
       	shell.setLayout(gridLayout);
		
		Text playerList = new Text(shell,SWT.MULTI);
		playerList.setText("WARTE AUF SPIELER \n");
		
		PlayerCollection player = game.getPlayers();
		
		for(int i = 0; i < player.size(); i++){
			playerList.append(player.get(i).getName() + "\n");
		}
		
		
		if(creator){
			Button startGame = new Button(shell,SWT.PUSH);
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
						app.startGame();
					} catch (NotEnoughPlayersException e1) {
						IO.writeError(e1.getMessage());
						System.exit(1);
					}
					// Close the window
					shell.dispose();
				}
		      });
		}
		
		center(shell);
		
		shell.pack();
		shell.open();
		
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
	
	@Override
	public void finalize() {
		shell.dispose();
	}

}
