package commons.actions;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;
import valueobjects.PlayerCollection;

import commons.Action;

public class PhaseAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6549826086379273945L;
	private Phase phase;
	private Player player;
	private PlayerCollection players;
	
	public PhaseAction(Player player, Phase phase, PlayerCollection players) {
		super(player, phase);
		
		this.player = player;
		this.players = players;
		this.phase = phase;
	}
	
	public Phase getPhase() {
		return phase;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public PlayerCollection getPlayers() {
		return players;
	}
}
