package fsm;

public interface State {
	/**
	 * Manages the transitions from this state to other states.<br>
	 * Example:<br>
	 * context.changeState("s2");
	 * 
	 * @param event
	 */
	public void handle(Event event);

	/**
	 * Gets called when this state is entered.
	 */
	public void onEnter();

	/**
	 * Gets called when this state is left.
	 */
	public void onLeave();
}
