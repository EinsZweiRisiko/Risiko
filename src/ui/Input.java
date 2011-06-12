package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ui.exceptions.NoInputException;
import ui.exceptions.YesNoFormatException;

/**
 * Helper class for command line I/O<br>
 * <br>
 * How to convert Strings to Integer:
 * <ul>
 * <li>As primitive
 * <ul>
 * <li><code>Integer.parseInt()</code></li>
 * <li><code>(new Integer(str)).intValue()</code></li>
 * </ul>
 * </li>
 * <li>As object
 * <ul>
 * <li><code>new Integer(str)</code></li>
 * <li><code>Integer.valueOf(str)</code></li>
 * </ul>
 * </li>
 * </ul>
 * This works in a similar way for other types:
 * <ul>
 * <li>Long</li>
 * <li>Float</li>
 * <li>Double</li>
 * </ul>
 * 
 * @author Jannes
 * 
 */

public class Input {

	/**
	 * Reads a String.
	 * 
	 * @return String which contains the input from the user
	 * @throws NoInputException
	 *             in the rare case that it wasn't possible to read from the
	 *             command line.
	 */
	public static String read() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			return reader.readLine();
		} catch (IOException e) {
			throw new NoInputException(e);
		}
	}

	/**
	 * Prints a message and reads a String.
	 * 
	 * @param message
	 *            String which is printed
	 * @return String which contains the input from the user
	 * @throws NoInputException
	 *             in the rare case that it wasn't possible to read from the
	 *             command line.
	 */
	public static String read(String message) {
		System.out.print(message + " ");
		return read();
	}

	/**
	 * Prints a message and reads an integer. If the user doesn't put a
	 * valid number in, he is prompted again.
	 * 
	 * @param message
	 *            String which is printed
	 * @return int which contains the input from the user
	 */
	public static int readNumber(String message) {
		// Ask for input
		while (true) {
			try {
				return Integer.parseInt(read(message));
			} catch (NumberFormatException e) {
				System.out.println("That's not a number.");
			}
		}
	}

	// TODO I'm not completely sure that these kinds of checks are allowed in
	// the UI. But for now let's leave them in.
	/**
	 * Prints a message and reads an integer. The integer must be between
	 * <code>min</code> and <code>max</code>. If the user doesn't put a
	 * valid number in, he is prompted again.
	 * 
	 * @param message
	 *            String which is printed
	 * @return int which contains the input from the user
	 */
	public static int readNumberInRange(String message, int min, int max) {
		// Add the range to the end of the message
		message += String.format(" (%d-%d)", min, max);

		// Ask for input
		int value;
		while (true) {
			value = readNumber(message);
			if (min <= value && value <= max) {
				// Valid input
				return value;
			} else {
				// Invalid input, the user will be asked again
				System.out.println("That's not between " + min + " and " + max
						+ ".");
			}
		}
	}

	/**
	 * Prints a message and reads 'yes' or 'no' from the input. If the user
	 * doesn't put a valid answer in, he is prompted again.
	 * 
	 * @param message
	 *            String which is printed
	 * @return boolean which contains the answer from the user
	 */
	public static boolean readYesNo(String message) {
		// Add yes/no to the end of the message
		message += " (yes/no)";

		// Ask for input
		while (true) {
			try {
				return parseYesNo(read(message));
			} catch (YesNoFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Returns the boolean that represents the String argument. Case and
	 * surrounding whitespace of the String is ignored.<br>
	 * true, if the String equals to "yes", "y" or "1".<br>
	 * false, if the String equals to "no", "n" or "0".
	 * 
	 * @param input
	 *            String that should be parsed.
	 * @return The boolean parse from the String argument.
	 * 
	 * @throws YesNoFormatException
	 */
	private static boolean parseYesNo(String input) throws YesNoFormatException {
		// Normalize the input
		input = input.toLowerCase().trim();

		// Check whether the input is valid and, if so, return it
		if (input.equals("y") || input.equals("yes") || input.equals("1")) {
			return true;
		} else if (input.equals("n") || input.equals("no") || input.equals("0")) {
			return false;
		} else {
			throw new YesNoFormatException(input);
		}
	}

}