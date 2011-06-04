package valueobjects.continents;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * Africa
 * 
 * @author Jannes
 * 
 */
public class Africa extends Continent {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2140046189563613850L;

	public Africa() {
		name = "Afrika";
		supplyBonus = 3;
		territoryList.add(new Territory("Nordwestafrika"));
		territoryList.add(new Territory("Ägypten"));
		territoryList.add(new Territory("Ostafrika"));
		territoryList.add(new Territory("Kongo"));
		territoryList.add(new Territory("Südafrika"));
		territoryList.add(new Territory("Madagaskar"));
	}

}
