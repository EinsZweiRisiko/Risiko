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

	/**
	 * Constructor automatically creates and adds Indonesien, Neu-Guinea,
	 * West-Australien and Ost-Australien. Also it defines the amount of supply
	 * (2) which is granted for succesful conquesting.
	 */
	public Australia() {
		name = "Australien";
		supplies = 2;
		territoryList.add(new Territory("Indonesien"));
		territoryList.add(new Territory("Neu-Guinea"));
		territoryList.add(new Territory("West-Australien"));
		territoryList.add(new Territory("Ost-Australien"));
	}

}
