package valueobjects;

import java.io.Serializable;

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
public class BonusCard implements Serializable {
	
	private static final long serialVersionUID = -5904440912901516998L;
	
//	public static enum Types {
//		Infantry, Cavalry, Artillery, WildCard
//	};
	
	/**
	 * Possible types:
	 * Infantry, Cavalry, Artillery, WildCard
	 */
	private String type;
	
	public BonusCard(String type) {
		setType(type);
	}
	
	/**
	 * Sets the type of a BonusCard
	 * @param type the type which should be used for the BonusCard
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @return the type of the BonusCard
	 */
	public String getType() {
		return type;
	}
	
}
