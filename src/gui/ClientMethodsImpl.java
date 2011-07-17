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
			// A player joined
			PlayerJoinedAction a2 = (PlayerJoinedAction) a;
			IO.write("player joined: " + a2.getPlayer().getName());
			
//			AppClient.updateLobby();
		} else if (a instanceof GameStartedAction) {
			IO.write("Game started");
		}
	}
	
	public void gameStarted() {
		
	}

}