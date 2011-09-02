package server.exceptions;

public class NotEnoughPlayersException extends Exception {
	private static final long serialVersionUID = -7522607164082455219L;

	public NotEnoughPlayersException(int players) {
		super("Not enough Players");
	}
}
