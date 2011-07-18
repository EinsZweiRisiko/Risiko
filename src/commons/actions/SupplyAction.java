package commons.actions;

import valueobjects.Player;
import commons.Action;

public class SupplyAction extends Action {

	Player player;
	int supplies;
	
	public SupplyAction(Player player, int supplies) {
		this.player = player;
		this.supplies = supplies;
	}

}
