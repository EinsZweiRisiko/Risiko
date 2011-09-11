package fsm;

public abstract class AbstractState implements State {

	protected StateMachine machine;
	private String name;
	protected Boolean endState = false;

	public AbstractState(StateMachine machine, String name) {
		this.machine = machine;
		this.name = name;
		
		this.machine.addState(this);
	}

	public AbstractState(StateMachine context, String name, Boolean endState) {
		this.machine = context;
		this.name = name;
		this.endState = endState;
		
		this.machine.addState(this);
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

}
