package commons.actions;

import commons.Action;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;

public class PhaseAction extends Action {

	public PhaseAction(Player player, Phase phase) {
		super(player, phase);
	}

}
