package valueobjects.continents;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * Australia
 * 
 * @author Jannes
 * 
 */
public class Australia extends Continent {

	public Australia() {
		name = "Australien";
		supplyBonus = 2;
		territoryList.add(new Territory("Indonesien"));
		territoryList.add(new Territory("Neu-Guinea"));
		territoryList.add(new Territory("West-Australien"));
		territoryList.add(new Territory("Ost-Australien"));
	}
	
}
