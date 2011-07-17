package commons;

import de.root1.simon.annotation.SimonRemote;
/*
 * definiert die Funktionen die der Client bereit stellt
 * 
 */
@SimonRemote
public interface ClientMethods {

	public void print(String msg);
	
}
