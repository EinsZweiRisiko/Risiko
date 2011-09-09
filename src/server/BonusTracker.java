package server;

import java.io.Serializable;

/**
 * A class which manages the amount of bonus supplies for when the players turn
 * in cards.
 * 
 * @author Jannes
 * 
 */
public class BonusTracker implements Serializable {

	private static final long serialVersionUID = 4047810657248296649L;

	/**
	 * Steps in which bonus supplies are awarded
	 */
	private static final int[] steps = { 4, 6, 8, 10, 12, 15 };

	/**
	 * Current location in the list
	 */
	private int currentIndex = 0;

	/**
	 * Current step. This is neccessary if we exceed the steps in the list.
	 */
	private int currentBonusValue;

	/**
	 * Returns the next bonus value for turning in cards.
	 * 
	 * @return Amount of bonus units
	 */
	public int getNextBonus() {
		if (currentIndex < steps.length) {
			// not exceeded yet
			currentBonusValue = steps[currentIndex];
		} else {
			// exceeded
			currentBonusValue += 5;
		}
		// Move on
		++currentIndex;

		return currentBonusValue;
	}

}
