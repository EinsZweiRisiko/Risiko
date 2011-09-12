package valueobjects.continents;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * Europe
 * 
 * @author Jannes
 * 
 */
public class Europe extends Continent {

	private static final long serialVersionUID = -4583649431625747760L;

	/**
	 * Constructor automatically creates and adds Island, Skandinavien, Ukraine,
	 * Großbritannien, Mitteleuropa and Südeuropa. Also it defines the amount of
	 * supply (5) which is granted for succesful conquesting.
	 */
	public Europe() {
		name = "Europa";
		bonusSupplies = 5;
		territoryList.add(new Territory("Island"));
		territoryList.add(new Territory("Skandinavien"));
		territoryList.add(new Territory("Ukraine"));
		territoryList.add(new Territory("Großbritannien"));
		territoryList.add(new Territory("Mitteleuropa"));
		territoryList.add(new Territory("Westeuropa"));
		territoryList.add(new Territory("Südeuropa"));
	}

}
