package domain.exceptions;

public class InvalidInputException extends Exception {

	public InvalidInputException(String input) {
		super("Sie haben eine falsche Eingabe getätigt! Ihre Eingabe:" + input);
	}
}
