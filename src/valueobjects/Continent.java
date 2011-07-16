package valueobjects;

import java.util.ArrayList;
import java.util.Iterator;

import server.TerritoryManager;


/**
 * An abstract class that represents a continent. It holds all the territories
 * that make up the continent as a
 * list.
 * 
 * @author Jannes
 * 
 */
public abstract class Continent implements Iterable<Territory> {

	/**
	 * Name of the continent
	 */
	protected String name;

	/**
	 * The amount of bonus units a player gets each round when he has conquered
	 * the complete continent
	 */
	protected int supplies = 0;

	/**
	 * Contains all territories that make this continent up
	 */
	protected ArrayList<Territory> territoryList = new ArrayList<Territory>();

	/**
	 * Returns an iterator which can be used to iterate over all the territories
	 * of the continent
	 * 
	 * Territories should not be removed from the list.
	 * 
	 * @return Iterator
	 */
	@Override
	public Iterator<Territory> iterator() {
		return territoryList.iterator();
	}

	/**
	 * Returns the name of this continent
	 * 
	 * @return Name of the continent
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the amount of bonus units a player gets each round for having
	 * conquered this territory
	 * 
	 * @return Amount of bonus supplies
	 */
	public int getBonusSupplies() {
		return supplies;
	}

	/**
	 * Returns a list with all territories of the continent.
	 * 
	 * Territories should not be removed from the list.
	 * 
	 * @return List of territories
	 * @see TerritoryManager
	 */
	public ArrayList<Territory> getTerritories() {
		return territoryList;
	}

}
