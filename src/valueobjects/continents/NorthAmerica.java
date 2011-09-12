package valueobjects.continents;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * North America
 * 
 * @author Jannes
 * 
 */
public class NorthAmerica extends Continent {

	private static final long serialVersionUID = 4544042189783571553L;

	/**
	 * Constructor automatically creates and adds Alaska, Nordwest-Territorium,
	 * Grönland, Alberta, Ontario, Quebec, Weststaaten, Oststaaten and
	 * Mittelamerika. Also it defines the amount of supply (5) which is granted
	 * for succesful conquesting.
	 */
	public NorthAmerica() {
		name = "Nordamerika";
		bonusSupplies = 5;
		territoryList.add(new Territory("Alaska"));
		territoryList.add(new Territory("Nordwest-Territorium"));
		territoryList.add(new Territory("Grönland"));
		territoryList.add(new Territory("Alberta"));
		territoryList.add(new Territory("Ontario"));
		territoryList.add(new Territory("Quebec"));
		territoryList.add(new Territory("Weststaaten"));
		territoryList.add(new Territory("Oststaaten"));
		territoryList.add(new Territory("Mittelamerika"));
	}

}
