package domain.exceptions;

import valueobjects.Territory;

@SuppressWarnings("serial")
public class InvalidTerritoryStateException extends Exception {
	
	public InvalidTerritoryStateException(Territory territory) {
		super("The territory " + territory.toString() + " still holds units.");
	}
}