package commons.actions;

import valueobjects.Territory;

import commons.Action;

public class AttackAction extends Action {
	
	private static final long serialVersionUID = -3602731354375567470L;
	
	Territory attackingTerritory;
	Territory attackedTerritory;
	int amount;
	
	public AttackAction(Territory attackingTerritory,
			Territory attackedTerritory, int amount) {
		this.attackedTerritory = attackedTerritory;
		this.attackingTerritory = attackingTerritory;
		this.amount = amount;
	}
	
	public Territory getAttackingTerritory() {
		return attackingTerritory;
	}

	public Territory getAttackedTerritory() {
		return attackedTerritory;
	}

	public int getAmount() {
		return amount;
	}

	
}
