package valueobjects;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class that represents a territory on the board.
 * 
 * @author Jannes, Hendrik
 * 
 */
public class Territory implements Serializable {

	private static final long serialVersionUID = -3644006307202028619L;

	private String name;

	private Player owner;

	private int unitCount = 0;

	private Continent continent;

	private List<Territory> neighborList = new CopyOnWriteArrayList<Territory>();

	/**
	 * Creates a new Territory
	 * 
	 * @param name
	 *            of the territory
	 */
	public Territory(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the territory
	 * 
	 * @return Name
	 */
	public String toString() {
		return name;
	}

	/**
	 * Returns the name of the territory
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the owner of this territory
	 * 
	 * @return Player who owns this territory
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the player who owns this territory
	 * 
	 * @param owner
	 *            Player
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * Gets a count of the units that are placed on this territory
	 * 
	 * @return Unit count
	 */
	public int getUnitCount() {
		return unitCount;
	}

	/**
	 * Sets the count of units on this territory
	 * 
	 * @param units
	 *            Unit count
	 */
	public void setUnitCount(int units) {
		this.unitCount = units;
	}

	/**
	 * Add units to the total unit count
	 * 
	 * @param units
	 *            Units to add
	 */
	public void addUnits(int units) {
		this.unitCount += units;
	}

	/**
	 * Subtract units from the total unit count
	 * 
	 * @param units
	 *            Units to subtract
	 */
	public void subtractUnits(int units) {
		this.unitCount -= units;
	}

	/**
	 * Returns the continent of the territory
	 * 
	 * @return Continent
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * Sets the contintent of the territory
	 * 
	 * @param continent
	 *            Continent
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	/**
	 * Returns a list of all neighbor territories
	 * 
	 * @return List of territories
	 */
	public List<Territory> getNeighbors() {
		return neighborList;
	}

	/**
	 * Adds a territory to the list of neighbors
	 * 
	 * @param territory
	 *            Territory which shall be added
	 */
	public void addNeighbor(Territory territory) {
		neighborList.add(territory);
	}

	/**
	 * Test if the given territory is a neighbor of this territory
	 * 
	 * @param territory
	 *            Territory which should be tested
	 * @return True, if it is a neighbor<br>
	 *         False, if it isn't
	 */
	public boolean isNeighborOf(Territory territory) {
		return neighborList.contains(territory);
	}

}
