package ui.exceptions;

public class YesNoFormatException extends Exception {

	private static final long serialVersionUID = -2816673315217451370L;

	public YesNoFormatException(String input) {
		super("You didn't answer with 'yes' or 'no'.");
	}

}
