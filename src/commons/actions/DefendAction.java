package commons.actions;

import valueobjects.Territory;
import commons.Action;

public class DefendAction implements Action {

	private Territory defendTerritory;
	private int amount;
	
	public DefendAction(Territory defendTerritory, int amount) {
		this.defendTerritory = defendTerritory;
		this.amount = amount;
	}

}
