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

	private static final long serialVersionUID = 6046402604974587744L;

	/**
	 * Constructor automatically creates and adds Ural, Sibirien, Jakutien,
	 * Irkutsk, Kamtschakta, Mongolei, Japan, Afghanistan, China, Mittlerer
	 * Osten, Indien and Siam. Also it defines the amount of supply (7) which is
	 * granted for succesful conquesting.
	 */
	public Asia() {
		name = "Asien";
		bonusSupplies = 7;
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
