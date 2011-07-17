package gui;

import server.net.RemoteObservable;
import server.net.RemoteObserver;
import ui.IO;

import commons.ClientMethods;

import de.root1.simon.annotation.SimonRemote;

/**
 * Fills the client with with life.
 */

@SimonRemote
public class ClientMethodsImpl implements ClientMethods, RemoteObserver {

	/**
	 * Print
	 */
	public void print(String msg) {
		System.out.println(msg);
	}

	@Override
	public void update(RemoteObservable o, Object a) {
		IO.write("update: " + a);
	}

}