package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Hilfsklasse mit Ein-/Ausgabeanweisungen
public class IO {

	// Einlesen eines char
	public static char readChar() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			return eingabe.charAt(0);
		} catch (Exception e) {
			return '\0';
		}
	}

	// Einlesen eines short
	public static short readShort() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			Integer String_to_short = new Integer(eingabe);
			return (short) String_to_short.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	// Einlesen eines int
	public static int readInt() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			Integer String_to_int = new Integer(eingabe);
			return String_to_int.intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	// Einlesen eines long
	public static long readLong() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			Long String_to_long = new Long(eingabe);
			return String_to_long.longValue();
		} catch (Exception e) {
			return 0L;
		}
	}

	// Einlesen eines float
	public static float readFloat() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			Float String_to_float = new Float(eingabe);
			return String_to_float.floatValue();
		} catch (Exception e) {
			return 0.0F;
		}
	}

	// Einlesen eines double
	public static double readDouble() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			Double String_to_double = new Double(eingabe);
			return String_to_double.doubleValue();
		} catch (Exception e) {
			return 0.0;
		}
	}

	// Einlesen eines Strings
	public static String readString() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			return input.readLine();
		} catch (Exception e) {
			return "";
		}
	}

	// Ausgeben eines Strings und Einlesen eines string
	public static String readString(String str) {
		System.out.print(str);
		return readString();
	}
}