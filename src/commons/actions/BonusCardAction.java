package commons.actions;

import valueobjects.Player;
import commons.Action;

public class BonusCardAction extends Action {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5011371002553512051L;
	
	Player player;
	
	public BonusCardAction(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}
