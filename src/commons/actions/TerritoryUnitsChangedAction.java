package commons.actions;

import valueobjects.Territory;

import commons.Action;

public class TerritoryUnitsChangedAction extends Action {
	
	private static final long serialVersionUID = 3911218703070306425L;

	private Territory territory;
	
	public TerritoryUnitsChangedAction(Territory territory, int units) {
		this.territory = territory;
	}
	
	public Territory getTerritory() {
		return territory;
	}

}
