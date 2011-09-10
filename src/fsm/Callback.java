package fsm;

public interface Callback {
	
	public void stateChanged(State oldState, State newState);

	public void ended(State endState);
	
}
