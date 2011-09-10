package fsm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FiniteStateMachine {

	private Callback callback;
	
	private Set<State> states = new HashSet<State>();
	private State currentState;
	private Boolean running = false;
	
	public FiniteStateMachine(Callback callback, State firstState) {
		this.callback = callback;
		// Add the initial state
		addState(firstState);
		currentState = firstState;
	}
	
	public void addState(State state) {
		if (running) {
			throw new MachineAlreadyRunningException("You can't add another state, because the FSM is already running.");
		}
		// Set start state
		currentState = state;
		states.add(state);
	}
	
	public void execute() {
		running = true;
	}
	
	//
	
	public State getState() {
		return currentState;
	}
	
	public void setState(State newState) {
		// Change the state
		State oldState = currentState;
		currentState = newState;
		
		// Notify the callback (listener)
		callback.stateChanged(oldState, newState);
	}
	
}
