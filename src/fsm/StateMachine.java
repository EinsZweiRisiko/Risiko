package fsm;

import java.util.HashMap;
import java.util.Map;

import fsm.exceptions.MachineInitializationError;
import fsm.exceptions.MachineAlreadyRunningException;
import fsm.exceptions.StateNotFoundException;

/**
 * Creates a finite-state machine.<br>
 * First, it has to be initialized with states. The first state will be the
 * start state. Then it has to be executed.
 * 
 * @author Jannes
 * 
 */
public class StateMachine {

	private Map<String, AbstractState> states = new HashMap<String, AbstractState>();
	private AbstractState startState;
	private AbstractState currentState;
	private boolean running = false;

	/**
	 * Adds a possible state to the state machine.
	 * 
	 * @param state
	 */
	public void addState(AbstractState state) {
		if (running) {
			throw new MachineAlreadyRunningException(
					"You can't add another state, because the machine is already running.");
		}
		// Is this the first state that gets added?
		if (states.isEmpty()) {
			startState = state;
		}
		// Add the state to the set of states
		states.put(state.getName(), state);
	}

	/**
	 * Starts the finite-state machine.
	 */
	public void start() {
		if (running) {
			throw new MachineAlreadyRunningException(
					"This machine is already running.");
		}
		if (startState == null) {
			throw new MachineInitializationError("No start state defined.");
		}
		// Start it
		running = true;
		toState(startState);
	}

	/**
	 * Resets the machine to the start state.
	 */
	public void restart() {
		toState(startState);
		// TODO what about a way to detect that this is a reset? circumvent
		// handlers?
	}

	/**
	 * Returns the current state as an object.
	 * 
	 * @return State state
	 */
	public AbstractState getState() {
		if (!running) {
			throw new MachineInitializationError("Not started yet.");
		}
		return currentState;
	}

	/**
	 * Returns the current state as string.
	 * 
	 * @return String state
	 */
	public String getStateName() {
		if (!running) {
			throw new MachineInitializationError("Not started yet.");
		}
		return currentState.getName();
	}

	/**
	 * Changes the current state
	 * 
	 * @param nextState
	 *            State
	 */
	public void toState(AbstractState nextState) {
		if (!running) {
			throw new MachineInitializationError("Not started yet.");
		}
		// If this is run for the first time, currentState will be null
		AbstractState oldState = currentState;

		// Set the new state
		currentState = nextState;

		// TODO are these handlers registered?
		if (oldState != null) {
			oldState.onLeave();
		}
		nextState.onEnter();
	}

	/**
	 * Changes the current state
	 * 
	 * @param name
	 *            String
	 */
	public void toState(String name) {
		if (!running) {
			throw new MachineInitializationError("Not started yet.");
		}
		if (!states.containsKey(name)) {
			throw new StateNotFoundException("The state '" + name + "' could not be found.");
		}
		// Set the state
		toState(states.get(name));
	}

	/**
	 * Handle an incoming event.
	 * 
	 * @param event Event
	 */
	public void handle(Event event) {
		if (!running) {
			throw new MachineInitializationError("Not started yet.");
		}
		// Delegate the event to the appropriate state
		currentState.handle(event);
	}

}