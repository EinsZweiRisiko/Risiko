package valueobjects;

import java.util.ArrayList;

/**
 * Represents a territory
 * 
 * @author Jannes
 * 
 */
public class Territory {

	private Player owner;
	private int units = 0;
	private String name;
	private Continent continent;
	private ArrayList<Territory> neighbors = new ArrayList<Territory>();

	public Territory(String name) {
		this.name = name;

	}

	public void addNeighbor(Territory land) {
		neighbors.add(land);
	}

	public boolean isNeighborOf(Territory land) {
		if (neighbors.contains(land)) {
			return true;
		} else {
			return false;
		}
	}

	// Getter und Setter

	public ArrayList<Territory> getNeighbors() {
		return neighbors;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getNumberOfUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	public void addUnits(int units) {
		this.units += units;
	}

	public int getUnits() {
		return units;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setKontinent(Continent continent) {
		this.continent = continent;
	}

}
