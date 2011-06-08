package ui.exceptions;

public class NoInputException extends RuntimeException {

	private static final long serialVersionUID = -4534087838461655757L;

	public NoInputException(Exception e) {
		super("The program was unable to read input from the command line.");
	}
	
}
