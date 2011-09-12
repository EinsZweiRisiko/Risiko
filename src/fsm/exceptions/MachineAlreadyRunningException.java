package fsm.exceptions;

public class MachineAlreadyRunningException extends RuntimeException {
	
	private static final long serialVersionUID = 5440238970305669353L;

	public MachineAlreadyRunningException(String message) {
		super(message);
	}
	
}
