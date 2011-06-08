package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class for command line I/O<br/>
 * <br/>
 * How to convert Strings to Integer:
 * <ul>
 * <li>int primitive
 * 	<ul><li>Integer.parseInt()</li><li>(new Integer(str)).intValue()</li></ul>
 * </li>
 * <li>Integer object
 *  <ul><li>Integer.valueOf(str)</li><li>new Integer(str)</li></ul>
 * </li>
 * </ul>
 * This works the same for other types:
 * <ul>
 * <li>Long</li>
 * <li>Float</li>
 * <li>Double</li>
 * </ul>
 * 
 * @author Jannes
 * 
 */

public class I {

	/**
	 * Reads a String.<br/>
	 * <br/>
	 * Returns an empty String if an error occured. Checking exceptions for
	 * every print statement would suck and make the code unreadable.
	 * 
	 * @return String which contains the input from the user
	 */
	public static String read() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					System.in));
			return input.readLine();
		} catch (IOException e) {
			// The program is allowed to recover from this error without
			// checking for exceptions. But at least a stack trace is printed to
			// indicate that something went wrong.
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Outputs a String, then reads a String.<br/>
	 * <br/>
	 * Returns an empty String if an error occured. Checking exceptions for
	 * every print statement would suck and make the code unreadable.
	 * 
	 * @param message
	 *            String which is printed
	 * @return String which contains the input from the user
	 */
	public static String read(String message) {
		System.out.print(message);
		return read();
	}
}