package commons.actions;

import java.util.List;

import valueobjects.Player;
import commons.Action;

public class EventBoxAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -654987663584583297L;
	
	Player player;
	String msg;
	List<Integer> attackDice;
	List<Integer> defendDice;
	
	public EventBoxAction(Player player, String msg) {
		this.player = player;
		this.msg = msg;
	}
	
	public EventBoxAction(Player player, String msg, List<Integer> attackDice, List<Integer> defendDice) {
		this.player = player;
		this.msg = msg;
		this.attackDice = attackDice;
		this.defendDice = defendDice;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public List<Integer> getAttackDice() {
		return attackDice;
	}
	
	public List<Integer> getDefendDice() {
		return defendDice;
	}
}
