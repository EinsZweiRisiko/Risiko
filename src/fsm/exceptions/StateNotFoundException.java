package fsm.exceptions;

public class StateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3085561738118865877L;

	public StateNotFoundException(String message) {
		super(message);
	}
	
}
