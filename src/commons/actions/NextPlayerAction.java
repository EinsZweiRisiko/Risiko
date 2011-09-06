package commons.actions;

import valueobjects.Player;

import commons.Action;

public class NextPlayerAction extends Action {

	private static final long serialVersionUID = 7929665552509714032L;
	private Player player;
	
	public NextPlayerAction(Player player) {
		super(player);
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}
