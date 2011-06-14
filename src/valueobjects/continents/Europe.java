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

	public Europe() {
		name = "Europa";
		supplies = 5;
		territoryList.add(new Territory("Island"));
		territoryList.add(new Territory("Skandinavien"));
		territoryList.add(new Territory("Ukraine"));
		territoryList.add(new Territory("Großbritannien"));
		territoryList.add(new Territory("Mitteleuropa"));
		territoryList.add(new Territory("Westeuropa"));
		territoryList.add(new Territory("Südeuropa"));
	}

}
