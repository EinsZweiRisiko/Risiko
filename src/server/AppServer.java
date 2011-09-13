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

/**
 * The Server Application to start the Server
 * @author Timur
 *
 */
public class AppServer {

	public static final int DEFAULT_PORT = 50001;

	/**
	 * Main
	 * 
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String args[]) {
		start();
	}

	/**
	 * Start the server instance and listen on the default port
	 */
	public static void start() {
		start(DEFAULT_PORT);
	}

	/**
	 * Start the server instance and listen on the specified port
	 */
	public static void start(int port) {
		try {
			new GameMethodsImpl("risk", port);
			System.out.println("Listening on port " + port + "...");
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NameBindingException e) {
			System.err.println(e.getMessage());
		}
	}
}
