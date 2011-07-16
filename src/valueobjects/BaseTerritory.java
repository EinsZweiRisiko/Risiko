package valueobjects;

import java.io.Serializable;

public class BaseTerritory implements Serializable {

	private String name;
	private Player owner;
	private int units = 0;
	
	public BaseTerritory(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Player getOwner() {
		return owner;
	}

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
