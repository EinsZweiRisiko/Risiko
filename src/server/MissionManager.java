package server;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import server.missions.ContinentHurdleMission;
import server.missions.EliminationMission;
import server.missions.Mission;
import server.missions.TerritoryHurdleMission;
import valueobjects.Continent;
import valueobjects.Player;

/**
 * This class is responsible for creating all missions at the beginning of a
 * game. And then it is supposed to assign each player a mission, so it is only
 * used for initialization. It doesn't need to be stored in a saved game.
 * 
 * @author Jannes
 * 
 */
public class MissionManager {

	Stack<Mission> missions = new Stack<Mission>();

	/**
	 * Constructor. Requires a fully instantiated TerritoryManager. The
	 * TerritoryManager obviously has to be replaced when loading a saved game.
	 * 
	 * @param t territoryManager
	 */
	public MissionManager(TerritoryManager t, List<Player> players) {
		Continent africa 		= t.getContinent("Africa");
		Continent asia 			= t.getContinent("Asia");
		Continent australia 	= t.getContinent("Australia");
		Continent europe 		= t.getContinent("Europe");
		Continent northAmerica 	= t.getContinent("NorthAmerica");
		Continent southAmerica 	= t.getContinent("SouthAmerica");

		// Create continent hurdle missions
		missions.push(new ContinentHurdleMission(t, northAmerica, africa));
		missions.push(new ContinentHurdleMission(t, northAmerica, australia));
		missions.push(new ContinentHurdleMission(t, asia, southAmerica));
		missions.push(new ContinentHurdleMission(t, asia, africa));
		missions.push(new ContinentHurdleMission(t, europe, southAmerica, 1));
		missions.push(new ContinentHurdleMission(t, europe, australia, 1));
		
		// Create territory hurdle missions
		missions.push(new TerritoryHurdleMission(t, 24));
		missions.push(new TerritoryHurdleMission(t, 18, 2));
		
		// Create player elimination missions
		for (Player enemy : players) {
			missions.push(new EliminationMission(t, enemy));
		}

		// Shuffle the missions
		Collections.shuffle(missions);
	}

	/**
	 * Removes one mission and returns it.
	 * 
	 * @return mission
	 */
	public Mission retrieveMission(Player player) {
		Mission mission = missions.pop();
		
		// Tell the mission who owns it
		mission.setOwner(player);

		return mission;
	}

}
