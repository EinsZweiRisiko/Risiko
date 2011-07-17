package ui;

import java.net.UnknownHostException;

import server.Test;

import commons.GameMethods;

import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

/*
 * Erstellt die Verbindung zum Server
 * Client zum Server
 * 
 */
public class ClientApp {
	
	private Lookup connection;
	private GameMethods game;
	private Test test;
	
	public ClientApp() {
		try {
			// Connect to the server
			connect();
			game.login("TestUser", new ClientMethodsImpl());
			
			game.print("Hello World");
			
			
			// mit dem Game Objekt wird auf die in GameMethodsImpl.java Funktionen zu gegriffen
			/*
			 * Die Client app dient zur übertragung von Server Funktionen zum Client
			 * 
			 */
			test = game.getObj();
			System.out.println(test.getName());
			
			
			
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		} catch (LookupFailedException e) {
			System.err.println(e.getMessage());
		} catch (EstablishConnectionFailed e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Create a connection to the server
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 * @throws UnknownHostException
	 */
	private void connect() throws LookupFailedException, EstablishConnectionFailed, UnknownHostException {
		connection = Simon.createNameLookup("localhost", 50001);
		game = (GameMethods) connection.lookup("risk");
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		new ClientApp();
	}
}
