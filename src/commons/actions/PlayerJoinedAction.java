package commons.actions;

import valueobjects.Player;

import commons.Action;

public class PlayerJoinedAction implements Action {

	private static final long serialVersionUID = -859928552333916476L;
	
	private Player player;
	
	public PlayerJoinedAction(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}