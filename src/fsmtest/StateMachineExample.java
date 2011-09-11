package fsmtest;

import fsm.Event;
import fsm.StateMachine;
import fsmtest.states.WaitingForConnections;
import fsmtest.states.WaitingForTurnInCards;


/**
 * An example of a finite-state machine
 * @author Jannes
 *
 */
public class StateMachineExample {

	public static void main(String[] args) {
		StateMachine machine = new StateMachine();
		new WaitingForConnections(machine);
		new WaitingForTurnInCards(machine);
		machine.start();
		System.err.println(machine.getState());
		machine.handle(new Event("GameStarts"));
		System.err.println(machine.getState());;
		
		/* StateMachine:
		 *   stateChanged(oldState, newState);
		 *   onChange
		 * 
		 * abstract State:
		 *   Always recreate states?
		 *   context.changeState(new Medium());
		 *   Name for the machine? machine/context 
		 */
	}

}
