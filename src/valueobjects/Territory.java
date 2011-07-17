package valueobjects;

import java.util.ArrayList;

/**
 * Represents a territory
 * 
 * @author Jannes, Hendrik
 * 
 */
public class Territory extends BaseTerritory {

	private static final long serialVersionUID = -3644006307202028619L;
	
	private Continent continent;
	private ArrayList<Territory> neighborList = new ArrayList<Territory>();

	/**
	 * Creates a new Territory
	 * @param name of the territory
	 */
	public Territory(String name) {
		super(name);
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
	public ArrayList<Territory> getNeighbors() {
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

}
