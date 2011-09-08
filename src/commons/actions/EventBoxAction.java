package commons.actions;

import valueobjects.Player;
import commons.Action;

public class EventBoxAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -654987663584583297L;
	
	Player player;
	String msg;
	
	public EventBoxAction(Player player, String msg) {
		this.player = player;
		this.msg = msg;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getMsg() {
		return msg;
	}
}
