package fsm;

public class Event {
	
	private String name;

	public Event(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean equals(String aString) {
		return this.name.equals(aString);
	}
	
}
