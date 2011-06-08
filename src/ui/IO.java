package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class for command line I/O
 * 
 * @author Jannes
 * 
 */
public class IO {
	/*
	 * How to convert Strings to something else:
	 * (new Integer(str)).intValue()
	 * (new Long(str)).longValue()
	 * (new Float(str)).floatValue()
	 * (new Double(str)).doubleValue()
	 */

	/**
	 * Reads a String.<br/>
	 * <br/>
	 * Returns an empty String if an error occured. Checking exceptions for
	 * every print statement would suck and make the code unreadable.
	 * 
	 * @return String which contains the input from the user
	 */
	public static String readString() {
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
	public static String readString(String message) {
		System.out.print(message);
		return readString();
	}
}