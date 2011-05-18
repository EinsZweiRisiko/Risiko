package ui;

//Import phase constants
import static domain.Constants.*;


public interface UserInterface {

	
	Land getOriginatingCountry(int phase);
	Land getTargetCountry(int phase);
	boolean askForAttack();
	void turnInCards();
	
	
}
