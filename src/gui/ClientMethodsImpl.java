package gui;

import java.util.Observable;

import ui.IO;

import commons.ClientMethods;

import de.root1.simon.annotation.SimonRemote;

/**
 * Fills the client with with life.
 */

@SimonRemote
public class ClientMethodsImpl implements ClientMethods {

	/**
	 * Print
	 */
	public void print(String msg) {
		System.out.println(msg);
	}

	@Override
	public void update(Observable o, Object a) {
		IO.write("update: " + a);
	}

}