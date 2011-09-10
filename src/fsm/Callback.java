package fsm;

public interface Callback {
	
	public void stateChanged(State oldState, State newState);

}
