package commons.actions;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;

import commons.Action;

public class PhaseAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6549826086379273945L;
	private Phase phase;
	
	public PhaseAction(Player player, Phase phase) {
		super(player, phase);
		
		this.phase = phase;
	}
	
	public Phase getPhase() {
		return phase;
	}

}
