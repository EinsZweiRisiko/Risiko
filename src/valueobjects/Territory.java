package valueobjects;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Represents a territory
 * 
 * @author Jannes, Hendrik
 * 
 */
public class Territory implements Serializable {

	private static final long serialVersionUID = -3644006307202028619L;
	
	private Continent continent;
	private CopyOnWriteArrayList<Territory> neighborList = new CopyOnWriteArrayList<Territory>();
	
	private String name;
	private Player owner;
	private int units = 0;

	/**
	 * Creates a new Territory
	 * @param name of the territory
	 */
	public Territory(String name) {
		this.name = name;
	}
	
	/**
	 * Adds a territory to the list of Neighbors
	 * @param territory which should be added
	 */
	public void addNeighbor(Territory territory) {
		neighborList.add(territory);
	}
	
	/**
	 * Function to test if the given territory is a Neighbor of this territory
	 * @param territory
	 * @return true, if it is a neighbor. false if it is not a neighbor.
	 */
	public boolean isNeighborOf(Territory territory) {
		if (neighborList.contains(territory)) {
			return true;
		} else {
			return false;
		}
	}

	// Getter und Setter
	
	/**
	 * 
	 * @return a list of all neighbor objects.
	 */
	public CopyOnWriteArrayList<Territory> getNeighbors() {
		return neighborList;
	}

	/**
	 * 
	 * @return the continent of the territory.
	 */
	public Continent getContinent() {
		return continent;
	}
	
	/**
	 * Sets the contintent of the territory.
	 * @param continent the continent which should be set
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
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
