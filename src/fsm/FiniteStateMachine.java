package fsm;

import java.util.HashSet;
import java.util.Set;

public class FiniteStateMachine {

	private Set<State> states = new HashSet<State>();
	private State currentState;
	private Callback callback;
	private Boolean running = false;
	
	public FiniteStateMachine(Callback callback, State startState) {
		this.callback = callback;
		addState(startState);
	}
	
	public void addState(State state) {
		if (running) {
			throw new MachineAlreadyRunningException("You can't add another state, because the FSM is already running.");
		}
		// Set start state
		currentState = state;
		states.add(state);
	}
	
	public void run() {
		running = true;
	}
	
	// Now we are live
	
	public State getState() {
		return null;
	}
	
	public void setState(State newState) {
		// Change the state
		State oldState = currentState;
		currentState = newState;
		
		// Notify the callback (listener)
		callback.stateChanged(oldState, newState);
	}
	
}
