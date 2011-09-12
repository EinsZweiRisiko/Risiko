package valueobjects;

import java.io.Serializable;

/**
 * This class represents a bonus card that a player receives at the end of his
 * turn if he captured at least one territory.
 * 
 * These cards are also referred to as "RISK cards" in the manual.
 * 
 * @author Jannes
 * 
 */
public class BonusCard implements Serializable {

	private static final long serialVersionUID = -5904440912901516998L;

	public enum BonusCardType {
		Infantry,
		Cavalry,
		Artillery,
		Wildcard
	}
	private BonusCardType type;

	public void setType(BonusCardType type) {
		this.type = type;
	}

	/**
	 * Create a bonus card
	 * 
	 * @param type
	 */
	public BonusCard(BonusCardType type) {
		this.type = type;
	}

	/**
	 * Return the type of the BonusCard
	 * 
	 * @return BonusCardType
	 */
	public BonusCardType getType() {
		return type;
	}

}
