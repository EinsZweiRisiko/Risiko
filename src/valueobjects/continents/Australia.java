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

	private static final long serialVersionUID = 6052958239532645054L;

	/**
	 * Constructor automatically creates and adds Indonesien, Neu-Guinea,
	 * West-Australien and Ost-Australien. Also it defines the amount of supply
	 * (2) which is granted for succesful conquesting.
	 */
	public Australia() {
		name = "Australien";
		bonusSupplies = 2;
		territoryList.add(new Territory("Indonesien"));
		territoryList.add(new Territory("Neu-Guinea"));
		territoryList.add(new Territory("West-Australien"));
		territoryList.add(new Territory("Ost-Australien"));
	}

}
