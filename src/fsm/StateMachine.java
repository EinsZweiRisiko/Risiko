package fsm;

import java.util.HashMap;
import java.util.Map;

import fsm.exceptions.InitializationError;
import fsm.exceptions.MachineAlreadyRunningException;

/**
 * Creates a finite-state machine.<br>
 * First, it has to be initialized with states. The first state will be the
 * start state. Then it has to be executed.
 * 
 * @author Jannes
 * 
 */
public class StateMachine {

	private Map<String, State> states = new HashMap<String, State>();
	private State startState;
	private State currentState;
	private Boolean running = false;

	/**
	 * Adds a possible state to the state machine.
	 * 
	 * @param state
	 */
	public void addState(State state) {
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
			throw new InitializationError("No start state defined.");
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
	public State getState() {
		if (!running) {
			throw new InitializationError("Not started yet.");
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
			throw new InitializationError("Not started yet.");
		}
		return currentState.getName();
	}

	/**
	 * Changes the current state
	 * 
	 * @param nextState
	 *            State
	 */
	public void toState(State nextState) {
		if (!running) {
			throw new InitializationError("Not started yet.");
		}
		// If this is run for the first time, currentState will be null
		State oldState = currentState;

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
			throw new InitializationError("Not started yet.");
		}
		// Get the state from the hashmap
		State nextState = states.get(name);
		if (nextState == null) {
			// TODO
			throw new RuntimeException("State not found");
		}
		// Set the state
		toState(nextState);
	}

	/**
	 * Handle an incoming event.
	 * 
	 * @param event Event
	 */
	public void handle(Event event) {
		if (!running) {
			throw new InitializationError("Not started yet.");
		}
		// Delegate the event to the appropriate state
		currentState.handle(event);
	}

}