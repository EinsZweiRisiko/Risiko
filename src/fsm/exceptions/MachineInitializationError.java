package fsm.exceptions;

public class MachineInitializationError extends Error {
	private static final long serialVersionUID = 8857224395534090823L;

	public MachineInitializationError(String message) {
		super(message);
	}
}
