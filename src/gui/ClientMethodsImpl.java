package gui;

import ui.IO;

import commons.Action;
import commons.ClientMethods;
import commons.GameMethods;
import commons.actions.GameStartedAction;
import commons.actions.PlayerJoinedAction;

import de.root1.simon.annotation.SimonRemote;

/**
 * Fills the client with with life.
 */

@SimonRemote
public class ClientMethodsImpl implements ClientMethods {

	@Override
	public void update(GameMethods server, Action a) {
		if (a instanceof PlayerJoinedAction) {
			PlayerJoinedAction a2 = (PlayerJoinedAction) a;
			IO.write("Player joined: " + a2.getPlayer().getName());
		} else if (a instanceof GameStartedAction) {
			//GameStartedAction a2 = (GameStartedAction) a;
			IO.write("Game started");
		}
	}

}