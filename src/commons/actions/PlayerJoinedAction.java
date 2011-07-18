package commons.actions;

import valueobjects.Player;

import commons.Action;

public class PlayerJoinedAction extends Action {

	private static final long serialVersionUID = -859928552333916476L;
	
	public PlayerJoinedAction(Player player) {
		super(player);
	}
	
}