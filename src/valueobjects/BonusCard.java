package valueobjects;

/**
 * This class represents a bonus card that a player receives at the end of his
 * turn if he captured at
 * least one territory.
 * 
 * These cards are also referred to as "RISK cards" in the Risk manual.
 * 
 * @author Jannes
 * 
 */
public class BonusCard {

	public static enum Types {
		Infantry, Cavalry, Artillery, WildCard
	};

	public BonusCard() {

	}

}
