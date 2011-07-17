package gui;

import java.util.Observable;

import server.net.XRemoteObservable;
import server.net.XRemoteObserver;
import ui.IO;

import commons.ClientMethods;

import de.root1.simon.annotation.SimonRemote;

/**
 * Fills the client with with life.
 */

@SimonRemote
public class ClientMethodsImpl implements ClientMethods, XRemoteObserver {

	/**
	 * Print
	 */
	public void print(String msg) {
		System.out.println(msg);
	}

	@Override
	public void update(XRemoteObservable o, Object a) {
		IO.write("update: " + a);
		
	}

}