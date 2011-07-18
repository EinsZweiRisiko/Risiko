package commons.actions;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;

import commons.Action;

public class PhaseAction extends Action {

	public PhaseAction(Player player, Phase phase) {
		super(player, phase);
	}

}
