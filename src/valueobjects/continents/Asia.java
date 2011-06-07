package valueobjects.continents;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * Asia
 * 
 * @author Jannes
 * 
 */
public class Asia extends Continent {

	public Asia() {
		name = "Asien";
		supplyBonus = 7;
		territoryList.add(new Territory("Ural"));
		territoryList.add(new Territory("Sibirien"));
		territoryList.add(new Territory("Jakutien"));
		territoryList.add(new Territory("Irkutsk"));
		territoryList.add(new Territory("Kamtschatka"));
		territoryList.add(new Territory("Mongolei"));
		territoryList.add(new Territory("Japan"));
		territoryList.add(new Territory("Afghanistan"));
		territoryList.add(new Territory("China"));
		territoryList.add(new Territory("Mittlerer Osten"));
		territoryList.add(new Territory("Indien"));
		territoryList.add(new Territory("Siam"));
	}
	
}
