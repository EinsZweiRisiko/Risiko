package valueobjects;

import java.io.Serializable;
import java.util.Collections;
import java.util.Stack;

import valueobjects.BonusCard.BonusCardType;

public class BonusCardStack implements Serializable {

	private static final long serialVersionUID = -55075093605778452L;

	private Stack<BonusCard> stack = new Stack<BonusCard>();

	/**
	 * Generate bonus cards at the beginning of a game
	 * 
	 * 14x Infanterie
	 * 14x Kavallerie
	 * 14x Artillerie
	 * 2x Joker
	 */
	public BonusCardStack() {
		final int cardsEach = 14;
		// 14x each type
		for (int i = 0; i < cardsEach; ++i) {
			stack.push(new BonusCard(BonusCardType.Infantry));
			stack.push(new BonusCard(BonusCardType.Cavalry));
			stack.push(new BonusCard(BonusCardType.Artillery));
		}
		// 2x wildcard
		stack.push(new BonusCard(BonusCardType.Wildcard));
		stack.push(new BonusCard(BonusCardType.Wildcard));
		// Shuffle the stack
		Collections.shuffle(stack);
	}

	/**
	 * Emits one card from the stack. Returns null if the stack is empty.
	 * 
	 * @return BonusCard
	 */
	public BonusCard retrieveCard() {
		if (stack.empty()) {
			return null;
		} else {
			return stack.pop();
		}
	}
}
