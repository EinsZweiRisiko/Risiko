package fsm;

public class State {

	private String name;
	private FiniteStateMachine owner;
	
	// TODO End state and start state
	public State(FiniteStateMachine owner, String name, Boolean startState, Boolean endState) {
		this.name = name;
		this.owner = owner;
		owner.addState(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void handle(Event event) {
		/*
		 * TODO Transitions
		 * on X 
		 * to s2
		 */
	}
	
}
