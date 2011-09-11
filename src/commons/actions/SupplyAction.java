package commons.actions;

import valueobjects.Player;

import commons.Action;

public class SupplyAction extends Action {

	private static final long serialVersionUID = -4315446016098748069L;
	
	Player player;
	int supplies;
	
	public SupplyAction(Player player, int supplies) {
		this.player = player;
		this.supplies = supplies;
	}

}
