package commons.actions;

import server.GameMethodsImpl.Phase;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import commons.Action;

public class GameStartedAction extends Action {
	private static final long serialVersionUID = -3656945116694876614L;
	private Player player;
	private Phase currentPhase;
	PlayerCollection players;

	public GameStartedAction(Player player, Phase currentPhase, PlayerCollection players) {
		this.player = player;
		this.currentPhase = currentPhase;
		this.players = players;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Phase getPhase() {
		return currentPhase;
	}
	
	public PlayerCollection getPlayers() {
		return players;
	}
}
