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
		/*
		//Random generate a Stack of Bonus cards
		for(int i = 0; i <= 43; i++) {
			
			if(i <= 13) {
				stack.get(i).setType("Infantry");
			}else if(i <= 27) {
				stack.get(i).setType("Cavalry");
			}else if(i <= 41) {
				stack.get(i).setType("Artillery");
			}else if(i <= 43) {
				stack.get(i).setType("WildCard");
			}
		}
		//Mix it
		Collections.shuffle(stack);
		*/
	}

	public BonusCard retrieveCard() {
		// TODO Auto-generated method stub
		stack.remove(1);
		return stack.get(1);
	}
}
