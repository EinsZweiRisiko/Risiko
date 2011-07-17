package valueobjects;

import java.io.Serializable;

/**
 * Baseclass of Territory
 * @author Jannes, Hendrik
 *
 */
public class BaseTerritory implements Serializable {

	private static final long serialVersionUID = 9049248607652001341L;
	
	private String name;
	private Player owner;
	private int units = 0;
	
	/**
	 * Consturctor
	 * @param name of the territory
	 */
	public BaseTerritory(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return name of the Territory
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the owner of the Territory
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * 
	 * @return the units placed on the Territory
	 */
	public int getUnits() {
		return units;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public void addUnits(int units) {
		this.units += units;
	}
	
}
