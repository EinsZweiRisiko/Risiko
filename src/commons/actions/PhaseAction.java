package commons.actions;

import commons.Action;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;

public class PhaseAction extends Action {

	Player player;
	Phase phase;
	
	public PhaseAction(Player player, Phase phase) {
		this.player = player;
		this.phase = phase;
	}

}
