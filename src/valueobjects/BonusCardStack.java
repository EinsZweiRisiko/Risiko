package valueobjects;

import java.util.ArrayList;
import java.util.Collections;


public class BonusCardStack extends ArrayList<BonusCard> {

	private ArrayList<BonusCard> stack = new ArrayList<BonusCard>();

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
		/*
		//Random generate a Stack of Bonus cards
		for(int i = 0; i <= 43; i++) {
			
			if(i <= 13) {
				get(i).setType("Infantry");
			}else if(i <= 27) {
				get(i).setType("Cavalry");
			}else if(i <= 41) {
				get(i).setType("Artillery");
			}else if(i <= 43) {
				get(i).setType("WildCard");
			}
		}
		//Mix it
		Collections.shuffle(this);
		*/
	}

	public BonusCard retrieveCard() {
		// TODO Auto-generated method stub
		remove(1);
		return get(1);
	}
}
