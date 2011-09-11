package fsm;

public abstract class State {

	private StateMachine context;
	private String name;
	private Boolean endState = false;

	public State(StateMachine context, String name) {
		this.context = context;
		this.name = name;
		this.context.addState(this);
	}

	public State(StateMachine context, String name, Boolean endState) {
		this.context = context;
		this.name = name;
		this.endState = endState;
		this.context.addState(this);
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
	public Boolean isEndState() {
		return endState;
	}

	/**
	 * Manages the transitions from this state to other states.<br>
	 * Example:<br>
	 * context.changeState("s2");
	 * 
	 * @param event
	 */
	public abstract void handle(Event event);

	/**
	 * Gets called when this state is entered.
	 */
	public abstract void onEnter();

	/**
	 * Gets called when this state is left.
	 */
	public abstract void onLeave();

}
