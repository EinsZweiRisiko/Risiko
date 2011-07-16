package server;

import java.io.IOException;
import java.net.UnknownHostException;

import ui.IO;

import de.root1.simon.exceptions.NameBindingException;

public class ServerApp {

	private static IO io = new IO();
	
	/**
	 * Ausgabe des Server "Welcome message".
	 *
	 */
	private static void printWelcomeMessage() {
		io.write("Warte auf Anfragen...");
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			// Starten des Servers
			new GameMethodsImpl("risk", 50001);

			// Ausgabe der "Welcome message" nach Start des Servers.
			ServerApp.printWelcomeMessage();
			
		} catch (UnknownHostException e) {
			io.writeError(e.getMessage());
		} catch (IOException e) {
			io.writeError(e.getMessage());
		} catch (NameBindingException e) {
			io.writeError(e.getMessage());
		}
	}

}
