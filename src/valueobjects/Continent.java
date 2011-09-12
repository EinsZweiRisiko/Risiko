package valueobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import server.TerritoryManager;

/**
 * An abstract class that represents a continent on the board. It holds all the
 * territories that make up the continent as a list.
 * 
 * @author Jannes
 * 
 */
public abstract class Continent implements Iterable<Territory>, Serializable {

	private static final long serialVersionUID = 3709777686029660803L;

	protected String name;

	protected List<Territory> territoryList = new ArrayList<Territory>();

	protected int bonusSupplies = 0;

	/**
	 * Returns an iterator which can be used to iterate over all the territories
	 * of the continent.<br>
	 * Although it is possible, territories should not be removed from the list.
	 * 
	 * @return Iterator
	 */
	@Override
	public Iterator<Territory> iterator() {
		return territoryList.iterator();
	}

	/**
	 * Gets the name of this continent.
	 * 
	 * @return Name of the continent
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets a list with all territories of the continent.<br>
	 * Although it is possible, territories should not be removed from the list.
	 * 
	 * @return List of territories
	 * @see TerritoryManager
	 */
	public List<Territory> getTerritories() {
		return territoryList;
	}

	/**
	 * Gets the amount of bonus units a player gets each round for having
	 * conquered this territory.
	 * 
	 * @return Amount of bonus supplies
	 */
	public int getBonusSupplies() {
		return bonusSupplies;
	}

}
