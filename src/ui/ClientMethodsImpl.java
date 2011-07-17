package ui;

import commons.ClientMethods;

import de.root1.simon.annotation.SimonRemote;

/*
 * füllt die Funktionen des Clients mit Leben und Funktion
 * 
 * 
 */

@SimonRemote
public class ClientMethodsImpl implements ClientMethods {

	/**
	 * Print
	 */
	public void print(String msg) {
		System.out.println(msg);
	}

}