package server;

import java.io.IOException;
import java.net.UnknownHostException;

import de.root1.simon.exceptions.NameBindingException;

public class AppServer {

	/**
	 * Ausgabe des Server "Welcome message".
	 *
	 */
	private static void printWelcomeMessage() {
		System.out.println("Warte auf Anfragen...");
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
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (NameBindingException e) {
			System.out.println(e.getMessage());
		}
	}

}
