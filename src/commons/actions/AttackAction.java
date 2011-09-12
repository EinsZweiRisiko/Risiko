package commons.actions;

import valueobjects.Territory;

import commons.Action;

public class AttackAction extends Action {
	
	private static final long serialVersionUID = -3602731354375567470L;
	
	Territory sourceTerritory;
	Territory targetTerritory;
	int amount;
	
	public AttackAction(Territory attackingTerritory, Territory attackedTerritory, int amount) {
		this.targetTerritory = attackedTerritory;
		this.sourceTerritory = attackingTerritory;
		this.amount = amount;
	}
	
	public Territory getSourceTerritory() {
		return sourceTerritory;
	}

	public Territory getTargetTerritory() {
		return targetTerritory;
	}

	public int getAmount() {
		return amount;
	}	
}
