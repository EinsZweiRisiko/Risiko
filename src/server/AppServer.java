package server;

import java.io.IOException;
import java.net.UnknownHostException;

import ui.IO;

import de.root1.simon.exceptions.NameBindingException;

public class AppServer {

	/**
	 * Ausgabe des Server "Welcome message".
	 *
	 */
	private static void printWelcomeMessage() {
		IO.write("Warte auf Anfragen...");
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			// Starten des Servers
			new GameMethodsImpl("risk", 50001);

			// Ausgabe der "Welcome message" nach Start des Servers
			AppServer.printWelcomeMessage();
			
		} catch (UnknownHostException e) {
			IO.writeError(e.getMessage());
		} catch (IOException e) {
			IO.writeError(e.getMessage());
		} catch (NameBindingException e) {
			IO.writeError(e.getMessage());
		}
	}

}
