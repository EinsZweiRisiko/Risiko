package server;


/**
 * A class which manages the amount of bonus supplies for when the players turn
 * in cards.
 * 
 * @author Jannes
 * 
 */
class BonusTracker {

	/**
	 * Steps in which bonus supplies are awarded
	 */
	private static final int[] steps = {4, 6, 8, 10, 12, 15};

	/**
	 * Current location in the list
	 */
	private int currentIndex = 0;
	
	/**
	 * Current step. This is neccessary if we exceed the steps in the list.
	 */
	private int currentValue;

	/**
	 * TODO doc
	 * @return
	 */
	public int getNextBonus() {
		if (currentIndex < steps.length) {
			// The list isn't exceeded
			currentValue = steps[currentIndex];
		} else {
			// The list is exceeded
			currentValue += 5;
		}
		
		++currentIndex;
		return currentValue;
	}

}
