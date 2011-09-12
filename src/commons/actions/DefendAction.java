package commons.actions;

import valueobjects.Territory;

import commons.Action;

public class DefendAction extends Action {

	private static final long serialVersionUID = 8856976568388995096L;
	private Territory defendTerritory;
	private int amount;
	
	public DefendAction(Territory defendTerritory, int amount) {
		this.defendTerritory = defendTerritory;
		this.amount = amount;
	}

}
