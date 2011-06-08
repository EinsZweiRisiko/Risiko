package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ui.exceptions.NoInputException;

/**
 * Helper class for command line I/O<br/>
 * <br/>
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
 * This works in the same way for other types:
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
	public static int readInt(String message) {
		// Contains the int that is to be returned
		int value = 0;
		// Contains whether the entered String was valid or not
		boolean successful = false;

		// Ask for input
		do {
			try {
				value = Integer.parseInt(read(message));
				successful = true;
			} catch (NumberFormatException e) {
				System.out.println("Das ist keine gültige Zahl.");
			}
		} while (!successful);

		return value;
	}
}