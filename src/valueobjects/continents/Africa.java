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

	public Africa() {
		name = "Afrika";
		supplies = 3;
		territoryList.add(new Territory("Nordwestafrika"));
		territoryList.add(new Territory("Ägypten"));
		territoryList.add(new Territory("Ostafrika"));
		territoryList.add(new Territory("Kongo"));
		territoryList.add(new Territory("Südafrika"));
		territoryList.add(new Territory("Madagaskar"));
	}

}
