package userinterface;
import java.io.*;

// Hilfsklasse mit Ein-/Ausgabeanweisungen
public class IO {

  // Ausgeben eines Strings
  public static void print(String str) {
    System.out.print(str);
  }

  // Ausgeben eines Strings inklusive Zeilenvorschub
  public static void println(String str) {
    System.out.println(str);
  }

   // Ausgeben eines long
  public static void print(long number) {
    System.out.print(number);
  }

  // Ausgeben eines long inklusive Zeilenvorschub
  public static void println(long number) {
    System.out.println(number);
  }

  // Ausgeben eines double
  public static void print(double number) {
    System.out.print(number);
  }

  // Ausgeben eines double inklusive Zeilenvorschub
  public static void println(double number) {
    System.out.println(number);
  }

    // Ausgeben eines char
  public static void print(char zeichen) {
    System.out.print(zeichen);
  }

  // Ausgeben eines char inklusive Zeilenvorschub
  public static void println(char zeichen) {
    System.out.println(zeichen);
  }

    // Ausgeben eines Objektes
  public static void print(Object obj) {
    System.out.print(obj);
  }

  // Ausgeben eines char inklusive Zeilenvorschub
  public static void println(Object obj) {
    System.out.println(obj);
  }
  
  // Ausgeben eines Zeilenvorschub
  public static void println() {
    System.out.println();
  }

  // Einlesen eines char
  public static char readChar() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      return eingabe.charAt(0);
    }
    catch(Exception e) {
      return '\0';
    }
  }

  // Einlesen eines short
  public static short readShort() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Integer String_to_short = new Integer(eingabe);
      return (short)String_to_short.intValue();
    }
    catch (Exception e) {
      return 0;
    }
  }  

  // Einlesen eines int
  public static int readInt() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Integer String_to_int = new Integer(eingabe);
      return String_to_int.intValue();
    }
    catch (Exception e) {
      return 0;
    }
  }

  // Einlesen eines long
  public static long readLong() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Long String_to_long = new Long(eingabe);
      return String_to_long.longValue();
    }
    catch (Exception e) {
      return 0L;
    }
  }

  // Einlesen eines float
  public static float readFloat() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Float String_to_float = new Float(eingabe);
      return String_to_float.floatValue();
    }
    catch (Exception e) {
      return 0.0F;
    }
  }

  // Einlesen eines double
  public static double readDouble() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      String eingabe = input.readLine();
      Double String_to_double = new Double(eingabe);
      return String_to_double.doubleValue();
    }
    catch (Exception e) {
      return 0.0;
    }
  }

  // Einlesen eines Strings
  public static String readString() {
    try {
      BufferedReader input = 
        new BufferedReader(new InputStreamReader(System.in));
      return input.readLine();
    }
    catch (Exception e) {
      return "";
    }
  }
  
  // Ausgeben eines Strings und Einlesen eines char
  public static char readChar(String str) {
    System.out.print(str);
    return readChar();
  }

  // Ausgeben eines Strings und Einlesen eines short
  public static short readShort(String str) {
    System.out.print(str);
    return readShort();
  }  

  // Ausgeben eines Strings und Einlesen eines int
  public static int readInt(String str) {
    System.out.print(str);
    return readInt();
  }

  // Ausgeben eines Strings und Einlesen eines long
  public static long readLong(String str) {
    System.out.print(str);
    return readLong();
  }

  // Ausgeben eines Strings und Einlesen eines float
  public static float readFloat(String str) {
    System.out.print(str);
    return readFloat();
  }

  // Ausgeben eines Strings und Einlesen eines double
  public static double readDouble(String str) {
    System.out.print(str);
    return readDouble();
  }

  // Ausgeben eines Strings und Einlesen eines string
  public static String readString(String str) {
    System.out.print(str);
    return readString();
  }
}