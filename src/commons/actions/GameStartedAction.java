package commons.actions;

import valueobjects.Player;
import commons.Action;

public class GameStartedAction extends Action {
	private static final long serialVersionUID = -3656945116694876614L;
	private Player currentPlayer;

	public GameStartedAction(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getPlayer() {
		return currentPlayer;
	}
}
