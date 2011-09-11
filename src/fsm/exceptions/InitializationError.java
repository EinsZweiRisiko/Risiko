package fsm.exceptions;

public class InitializationError extends Error {
	private static final long serialVersionUID = 8857224395534090823L;

	public InitializationError(String message) {
		super(message);
	}
}
