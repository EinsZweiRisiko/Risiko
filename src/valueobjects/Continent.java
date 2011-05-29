package valueobjects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that represents a continent. It holds all the territories that make up the continent as a
 * list.
 * 
 * @author Jannes
 * 
 */
public class Continent implements Iterable<Territory> {

	/**
	 * Name of the continent
	 */
	private String name;

	/**
	 * The amount of bonus units a player gets each round when he has conquered the complete continent
	 */
	private int supplyBonus;

	/**
	 * Contains all territories that make this continent
	 */
	private ArrayList<Territory> territoryList = new ArrayList<Territory>();

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param supplyBonus
	 */
	public Continent(String name, int supplyBonus) {
		this.name = name;
		this.supplyBonus = supplyBonus;
	}

	/**
	 * Adds a territory to the list. This method is only used during initialization
	 * 
	 * @param territory
	 *            Territory that is to be added
	 */
	public void addTerritory(Territory territory) {
		territoryList.add(territory);
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
	 * Returns the amount of bonus units a player gets each round for having conquered this territory
	 * 
	 * @return
	 */
	public int getSupplyBonus() {
		return supplyBonus;
	}

	/**
	 * Returns a list with all territories of the continent
	 * 
	 * @return
	 */
	public ArrayList<Territory> getTerritories() {
		// But this is currently needed for a containsAll() check
		return territoryList;
		// TODO: This array should not be given out.
	}

	/**
	 * Returns an iterator which can be used to iterate over all the territories of the continent
	 * 
	 * @return Iterator
	 */
	@Override
	public Iterator<Territory> iterator() {
		// TODO: Protect territories from being removed
		return territoryList.iterator();
	}

}
