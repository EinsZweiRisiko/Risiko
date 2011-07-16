package server;


/**
 * A class which manages the amount of bonus supplies for when the players turn
 * in cards.
 * 
 * @author Jannes
 * 
 */
class BonusTracker {

	private static final int[] steps = {4, 6, 8, 10, 12, 15};
	private int currentIndex;
	private int currentValue;

	/**
	 * Constructor
	 */
	public BonusTracker() {
		// Current location in the list
		currentIndex = -1;
	}

	/**
	 * TODO doc
	 * @return
	 */
	public int getNextBonus() {
		++currentIndex;
		
		if (currentIndex < steps.length) {
			// The list isn't exceeded
			currentValue = steps[currentIndex];
		} else {
			// The list is exceeded
			currentValue += 5;
		}
		
		return currentValue;
	}

}
