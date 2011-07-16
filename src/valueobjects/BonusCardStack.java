package valueobjects;

import java.util.ArrayList;
import java.util.Collections;


public class BonusCardStack extends ArrayList<BonusCard> {

	private static final long serialVersionUID = -55075093605778452L;

	/**
	 * Constructor
	 */
	public BonusCardStack() {
		// TODO setup bonus cards at the beginning of a game
		// 14 von jeder sorte
		// 2 joker
		// 1: infanterie
		// 2: Kavlerier
		// 3: Kanone
		
		//Random generate a Stack of Bonus cards
		for(int i = 1; i <= 44; i++) {
			if(i <= 14) {
				get(i).setType("Infantry");
			}
			if(i <= 28) {
				get(i).setType("Cavalry");
			}
			if(i <= 42) {
				get(i).setType("Artillery");
			}
			if(i <= 44) {
				get(i).setType("WildCard");
			}
		}
		//Mix it
		Collections.shuffle(this);
	}

	public BonusCard retrieveCard() {
		// TODO Auto-generated method stub
		remove(1);
		return get(1);
	}
}
