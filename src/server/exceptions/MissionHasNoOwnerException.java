package server.exceptions;

public class MissionHasNoOwnerException extends RuntimeException {

	private static final long serialVersionUID = -4655087277054367931L;

	public MissionHasNoOwnerException(String message) {
		super(message);
	}

}
