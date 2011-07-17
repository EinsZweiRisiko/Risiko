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
	 * Constructor automatically creates and adds Venezuela, Peru, Brasilien and
	 * Argentinien. Also it defines the amount of supply (2) which is granted
	 * for succesful conquesting.
	 */
	public SouthAmerica() {
		name = "Südamerika";
		supplies = 2;
		territoryList.add(new Territory("Venezuela"));
		territoryList.add(new Territory("Peru"));
		territoryList.add(new Territory("Brasilien"));
		territoryList.add(new Territory("Argentinien"));
	}

}
