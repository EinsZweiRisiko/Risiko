package server;

import gui.AppClient;

import java.io.IOException;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.root1.simon.exceptions.NameBindingException;

public class AppServer {

	private String events;
	private String[] player = new String[5];
	private String target;
	private String source;
	private String phase;
	private Text eventLog;
	private Text objects;

	public static final int DEFAULT_PORT = 50001;

	/**
	 * Main
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String args[]) {
		AppServer appServer = new AppServer();
	}
	
	public AppServer() {
		this.start();
	}

	/**
	 * Start the server instance and listen on the default port
	 */
	public void start() {
		start(DEFAULT_PORT);
	}

	/**
	 * Start the server instance and listen on the specified port
	 */
	public void start(int port) {
		
		openServerShell();
		
		try {
			new GameMethodsImpl("risk", port, this);
			this.updateEventLog("Listening on port " + port + "...");
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NameBindingException e) {
			System.err.println(e.getMessage());
		}
	}

	private void openServerShell() {
		Display display = new Display();
		Shell shell = new Shell(display, SWT.MIN);

		shell.setText("ZwoEinsRisiko Server");
		// Set background
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		Image bg = new Image(display, "assets/riskServer.png");

		shell.setBackgroundImage(bg);

		Composite innerWindow = new Composite(shell, SWT.NONE);
		innerWindow.setBounds(0, 0, 800, 480);

		Composite TopLeft = new Composite(innerWindow, SWT.NONE);
		Composite BottomLeft = new Composite(innerWindow, SWT.NONE);
		Composite TopRight = new Composite(innerWindow, SWT.NONE);
		Composite BottomRight = new Composite(innerWindow, SWT.NONE);

		TopLeft.setBounds(0, 0, 200, 25);
		BottomLeft.setBounds(0, 30, 205, 445);
		TopRight.setBounds(210, 0, 590, 25);
		BottomRight.setBounds(210, 30, 590, 445);

		Label title1 = new Label(TopLeft, SWT.NONE);
		title1.setText("Variable");
		title1.setLocation(5, 5);
		title1.pack();

		Label title2 = new Label(TopLeft, SWT.NONE);
		title2.setText("Status");
		title2.setLocation(105, 5);
		title2.pack();

		Label title3 = new Label(TopRight, SWT.NONE);
		title3.setText("EventLog");
		title3.setLocation(5, 5);
		title3.pack();

		objects = new Text(BottomLeft, SWT.NONE | SWT.MULTI | SWT.WRAP
				| SWT.RESIZE | SWT.V_SCROLL);
		objects.setBounds(0, 0, 205, 445);

		eventLog = new Text(BottomRight, SWT.NONE | SWT.MULTI | SWT.WRAP
				| SWT.RESIZE | SWT.V_SCROLL);
		eventLog.setBounds(0, 0, 590, 445);

		innerWindow.pack();
		shell.pack();
		shell.open();
		center(shell);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private static void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	public void updateEventLog(String string) {
		events = string + "\n" + events;
		eventLog.setText(events);
	}

	public void writeObjects(String newData, String field) {
		if(field == "player1"){
			this.player[0] = newData;
		}
		if(field == "player2"){
			this.player[1] = newData;
		}
		if(field == "player3"){
			this.player[2] = newData;
		}
		if(field == "player4"){
			this.player[3] = newData;
		}
		if(field == "player5"){
			this.player[4] = newData;
		}
		if(field == "player6"){
			this.player[5] = newData;
		}
		if(field == "target"){
			target = newData;
		}
		if(field == "source"){
			source = newData;
		}
		if(field == "phase"){
			phase = newData;
		}
		
		String playerData = null;
		for(String string:this.player){
			playerData = string + "\n";
		}
		
		
		objects.setText("Player: \n" + playerData + "\n Phase:" + phase  + "\n Territories: \n" + source + "\n" + target);
	}
	
	@Override
	public void finalize() {
		shell.dispose();
	}
	
}
