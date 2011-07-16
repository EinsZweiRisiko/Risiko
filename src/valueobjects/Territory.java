package valueobjects;

import java.util.ArrayList;

/**
 * Represents a territory
 * 
 * @author Jannes
 * 
 */
public class Territory extends BaseTerritory {

	private static final long serialVersionUID = -3644006307202028619L;
	
	private Continent continent;
	private ArrayList<Territory> neighborList = new ArrayList<Territory>();

	/**
	 * Creates a new Territory
	 * @param name
	 */
	public Territory(String name) {
		super(name);
	}

	public void addNeighbor(Territory land) {
		neighborList.add(land);
	}

	public boolean isNeighborOf(Territory land) {
		if (neighborList.contains(land)) {
			return true;
		} else {
			return false;
		}
	}

	// Getter und Setter

	public ArrayList<Territory> getNeighbors() {
		return neighborList;
	}

	public Continent getContinent() {
		return continent;
	}
	
	public void setKontinent(Continent continent) {
		this.continent = continent;
	}

}
