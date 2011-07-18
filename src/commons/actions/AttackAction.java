package commons.actions;

import valueobjects.Player;
import valueobjects.Territory;
import commons.Action;

public class AttackAction implements Action {
	
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
