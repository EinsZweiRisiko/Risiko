package domain.managers;

import java.util.ArrayList;
import java.util.Collections;

import valueobjects.BonusCard;

public class BonusCardStack {

	private ArrayList<BonusCard> stack = new ArrayList<BonusCard>();
	
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
				stack.get(i).setType("Infantry");
			}
			if(i <= 28) {
				stack.get(i).setType("Cavalry");
			}
			if(i <= 42) {
				stack.get(i).setType("Artillery");
			}
			if(i <= 44) {
				stack.get(i).setType("WildCard");
			}
		}
		//Mix it
		Collections.shuffle(stack);
	}

	public BonusCard retrieveCard() {
		// TODO Auto-generated method stub
		stack.remove(1);
		return stack.get(1);
	}
}
