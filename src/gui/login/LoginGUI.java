package gui.login;

import gui.AppClient;
import gui.MessageDialog;

import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import server.remoteexceptions.NoNameException;
import server.remoteexceptions.ServerFullException;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

/**
 * Shows a login screen
 * 
 * @author Hendrik, Jannes
 */
public class LoginGUI {

	private AppClient app;

	private Display display;
	private Shell shell;
	private Button loadGame;
	private Button joinGame;
	private Text server;
	private Text name;

	/**
	 * Create a new instance of a login window.
	 * 
	 * @param display
	 *            The display to use for showing the shell
	 * @param appClient
	 *            The calling AppClient
	 */
	public LoginGUI(Display display, AppClient appClient) {
		// Save the calling AppClient
		this.display = display;
		app = appClient;

		// Create a new window
		shell = new Shell(display);
		shell.setText("Login - " + AppClient.name);

		// Set background image
		shell.setBackgroundImage(new Image(display, "assets/loginbg.png"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);

		// Create the user interface
		buildUserInterface();

		// Connect the event handlers
		connectEventHandlers();

		// Set the window size
		shell.pack();
		shell.setMinimumSize(shell.getSize());
		
		// Display the window
		pumpLoop();
	}

	/**
	 * Build the user interface
	 */
	private void buildUserInterface() {
		// Create layout manager
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.horizontalSpacing = 6;
		layout.verticalSpacing = 6;
		
		// Layout data for the text input fields
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;

		// Create composite
		Composite login = new Composite(shell, SWT.NONE);
		login.setLayout(layout);

		// Add name label
		Label nameLabel = new Label(login, SWT.NONE);
		nameLabel.setText("Your name: ");

		// Add name input
		name = new Text(login, SWT.SINGLE | SWT.BORDER);
		name.setText("test " + (int)(Math.random() * 30));
		name.selectAll();
		name.setLayoutData(gridData);

		// Add server label
		Label serverLabel = new Label(login, SWT.NONE);
		serverLabel.setText("Server: ");

		// Add server input
		server = new Text(login, SWT.SINGLE | SWT.BORDER);
		server.setText("localhost");
		server.setLayoutData(gridData);

		// Add "join game" button
		joinGame = new Button(login, SWT.PUSH);
		joinGame.setText("Join game");
		shell.setDefaultButton(joinGame);

		// Add "load game" button
		loadGame = new Button(login, SWT.PUSH);
		loadGame.setText("Load game");

		login.setBounds(0, 0, 250, 250);

		// Create composite
		Composite about = new Composite(shell, SWT.NONE);
		about.setLayout(layout);

		// Add about text
		Label aboutlabel = new Label(about, SWT.NONE);
		aboutlabel.setText(AppClient.name + "\n" + "Hochschule Bremen 2011\n"
				+ "Hendrik Druse, Jannes Meyer, Timur Teker");

		about.setBounds(0, 300, 250, 50);
	}

	/**
	 * Connect the event handlers
	 */
	private void connectEventHandlers() {
		// Join game
		joinGame.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				String ip = server.getText();
				String player = name.getText();
				try {
					// Connect to the server
					app.connect(ip, player);
					// Close the window after a successful connect
					shell.dispose();
				} catch (UnknownHostException e) {
					new MessageDialog(shell, SWT.ICON_WARNING, "Error",
							"Unknown host: " + e.getMessage());
				} catch (LookupFailedException e) {
					new MessageDialog(shell, SWT.ICON_WARNING, "Error",
							"Unknown host: " + e.getMessage());
				} catch (EstablishConnectionFailed e) {
					new MessageDialog(shell, SWT.ICON_WARNING, "Error",
							"Unknown host: " + e.getMessage());
				} catch (ServerFullException e) {
					new MessageDialog(shell, SWT.ICON_WARNING, "Error",
							"Unknown host: " + e.getMessage());
				} catch (NoNameException e) {
					new MessageDialog(shell, SWT.ICON_WARNING, "Error",
							"Unknown host: " + e.getMessage());
				}

			}
		});

		// Load game
		loadGame.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				// TODO Auto-generated method stub
			}

		});

		// Quit the program on window close
		shell.addListener(SWT.Close, new Listener() {

			public void handleEvent(Event event) {
				System.exit(0);
			}

		});
	}
	
	/**
	 * UI loop
	 */
	private void pumpLoop() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
