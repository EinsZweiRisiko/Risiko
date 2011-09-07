package commons.actions;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;
import commons.Action;

public class GameStartedAction extends Action {
	private static final long serialVersionUID = -3656945116694876614L;
	private Player currentPlayer;
	private Phase currentPhase;

	public GameStartedAction(Player currentPlayer, Phase currentPhase) {
		this.currentPlayer = currentPlayer;
		this.currentPhase = currentPhase;
	}

	public Player getPlayer() {
		return currentPlayer;
	}
	
	public Phase getPhase() {
		return currentPhase;
	}
}
