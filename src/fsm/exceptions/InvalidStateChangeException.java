package fsm.exceptions;

public class InvalidStateChangeException extends Exception {

	private static final long serialVersionUID = 8435588848826768152L;

	public InvalidStateChangeException(String message) {
		super(message);
	}

}
