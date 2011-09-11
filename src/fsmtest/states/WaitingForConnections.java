package fsmtest.states;

import fsm.Event;
import fsm.StateMachine;
import fsm.AbstractState;

public class WaitingForConnections extends AbstractState {

	public WaitingForConnections(StateMachine machine) {
		super(machine, "WaitingForConnections");
	}
	
	@Override
	public void handle(Event event) {
		if (event.equals("GameStarts")) {
			machine.toState("WaitingForTurnInCards");
		}
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
