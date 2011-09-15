package fsmtest.states;

import fsm.Event;
import fsm.AbstractState;
import fsm.StateMachine;

public class WaitingForTurnInCards extends AbstractState {

	public WaitingForTurnInCards(StateMachine machine) {
		super(machine, "WaitingForTurnInCards");
	}
	
	@Override
	public void handle(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnter() {
		System.out.println("Entering " + getName());
	}

	@Override
	public void onLeave() {
		System.out.println("Leaving " + getName());
	}
	
}
