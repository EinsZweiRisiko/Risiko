package valueobjects.continents;

import valueobjects.Continent;
import valueobjects.Territory;

/**
 * South America
 * 
 * @author Jannes
 *
 */
public class SouthAmerica extends Continent {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -767593745864522688L;

	public SouthAmerica() {
		name = "Südamerika";
		supplyBonus = 2;
		territoryList.add(new Territory("Venezuela"));
		territoryList.add(new Territory("Peru"));
		territoryList.add(new Territory("Brasilien"));
		territoryList.add(new Territory("Argentinien"));
	}

}
