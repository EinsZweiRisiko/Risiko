package server;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import persistence.Store;

import server.exceptions.InvalidTerritoryStateException;
import server.exceptions.NotEnoughPlayersException;
import server.remoteexceptions.ServerFullException;
import valueobjects.BonusCard;
import valueobjects.BonusCardStack;
import valueobjects.Continent;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;

import commons.Action;
import commons.ClientMethods;
import commons.GameMethods;
import commons.actions.AttackAction;
import commons.actions.BonusCardAction;
import commons.actions.EventBoxAction;
import commons.actions.GameStartedAction;
import commons.actions.PhaseAction;
import commons.actions.PlayerJoinedAction;
import commons.actions.PrepareGUIAction;
import commons.actions.SupplyAction;
import commons.actions.TerritoryUnitsChangedAction;

import de.root1.simon.Registry;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.NameBindingException;
import de.root1.simon.exceptions.SimonRemoteException;

/**
 * The game class manages a complete game of Risk
 * 
 * @author Jannes, Hendrik
 * 
 */
@SimonRemote
public class GameMethodsImpl implements GameMethods, Serializable {

	private static final long serialVersionUID = -3491803188267650698L;

	// die gewürfelten Ergebnisse müssen auf dem Server lokal gespeichert werden
	List<Integer> attackDice;
	List<Integer> defendDice;

	// das angreifende land und angegeriffende Land muss temporär gespeichert werden
	Territory sourceTerritory;
	Territory targetTerritory;

	private boolean started = false; 
	private PlayerCollection players = new PlayerCollection();
	private List<ClientMethods> clients = new ArrayList<ClientMethods>();

	private TerritoryManager territoryManager = new TerritoryManager();
	private BonusCardStack bonusCardManager = new BonusCardStack();
	private BonusTracker bonusTracker = new BonusTracker();
	private Boolean recieveBonuscard = false;

	/**
	 * The number of Fight Rounds
	 */
	private int attackingRound = 0;

	/**
	 * The current player
	 */
	private Player currentPlayer;

	/**
	 * The current phase of a player's turn
	 */
	private Phase currentPhase = Phase.START;

	private AppServer appServer;

	/**
	 * Phases of a player's turn
	 */
	public static enum Phase {
		START, TURNINCARDS, PLACEMENT, ATTACK1, ATTACK2, ATTACK3, MOVEMENT1, MOVEMENT2, MOVEMENT3
	};

	public GameMethodsImpl(String name, int port) throws UnknownHostException,
	IOException, NameBindingException {
		Registry registry = Simon.createRegistry(port);
		registry.bind(name, this);
		this.appServer = appServer;
	}

	/**
	 * Starts this instance of Risk
	 */
	public void start() throws NotEnoughPlayersException {
		int playerCount = players.size();

		// Check if there are enough players
		if (playerCount < 3) {
			throw new NotEnoughPlayersException(playerCount);
		}

		// Start units for every player
		int startUnits;
		// Get the total amount of start units per player
		if (playerCount > 3) {
			startUnits = 30;
		} else if (playerCount == 3) {
			startUnits = 35;
		} else {
			startUnits = 36;
		}

		// Set the start units for each player
		for (Player player : players) {
			player.addSupplies(startUnits);
		}

		placeStartUnitsRandomly();
		notifyPlayers(new PrepareGUIAction());

		// Set the game status to started
		started = true;

		notifyPlayers(new GameStartedAction(currentPlayer, currentPhase, players));

		save();
		
		load();
		// Set the first phase
		nextPhase();
		nextPhase();
	}

	/**
	 * Prepares and returns the next action in the sequence. This method can
	 * change the active player, so always use this method before getting the
	 * active player.<br>
	 * <br>
	 * The sequence of actions in a turn:
	 * <ol>
	 * <li>TURNINCARDS</li>
	 * <li>PLACEMENT</li>
	 * <li>ATTACK</li>
	 * <li>MOVEMENT</li>
	 * </ol>
	 * 
	 * @return Action The next action/phase
	 */
	public void nextPhase() {		
		notifyPlayers(new PhaseAction(currentPlayer, currentPhase, players));
		System.out.println("Es spielt: "+ currentPlayer.getName());
		// Which action comes afterwards the current one?
		switch (currentPhase) {
			// The first action is at the end of this switch block
			case TURNINCARDS:
				// Placing the supply units is next
				// überprüfen der karten und supply hnzufügen
				// currentphase = PLACEMENT
				preparePlacementAction();
				break;
			case PLACEMENT:
				// Attacking other players is next
				prepareAttack1Action();
				break;
			case ATTACK1:
				// here: choose the Teritory(Button)
				prepareAttack2Action();
				break;
			case ATTACK2:
				// here: choose territory to attack (button)
				prepareAttack3Action();
				break;
			case ATTACK3:
				//defend and reset the attack!
				prepareAttack1Action();
				break;
			case MOVEMENT1:
				// TODO Only if the player conquered at least one territory
				currentPlayer.addBonusCard(bonusCardManager.retrieveCard());
				// End of a player's turn. Start a new one.
				// Turning in cards is next
				prepareMovement2Action();
				break;
			case MOVEMENT2:
				prepareMovement3Action();
				System.out.println("MOVEMENT 2 präperiert MOVEMENT 3");
				break;
			case MOVEMENT3:
				prepareTurnInAction();
				break;
			default:
				// Start
				// Turning in cards is next
				prepareTurnInAction();
				break;
		}
	}

	/**
	 * Set the next player by getting the next Player from the players Collection function getNextPlayer()
	 * 
	 */
	public void nextPlayer() {
		// Advance to the next player
		currentPlayer = players.getNextPlayer();
		//notifyPlayers(new NextPlayerAction(currentPlayer));
	}

	/*
	 * START: *** UNITS / FIGHT / DEFEND / MOVE FUNCTIONS *** 
	 * 
	 */

	/**
	 * Calculates supply for the current player. This is only called once
	 * in every turn.<br>
	 * <br>
	 * This doesn't include unit supplies for cards that the player may turn in
	 * later.
	 */
	private void calculateSupplies() {
		// Base unit amount for occupied territories
		int supplies = currentPlayer.getTerritoryCount() / 3;
		// At least 3
		if (supplies < 3) {
			supplies = 3;
		}

		// TODO Extra supplies for conquered continents
		if(currentPlayer.getContinents(currentPlayer, territoryManager) != null) {
			for (Continent continent : currentPlayer.getContinents(currentPlayer, territoryManager)) {
				supplies += continent.getBonusSupplies();
			}	
		}


		// Add the supplies
		currentPlayer.addSupplies(supplies);
		System.out.println("Spieler: "+ currentPlayer.getName()+ " Supplies = "+ currentPlayer.getSupplies());
	}

	/**
	 * place the Units randomly on the territorys
	 */
	public void placeStartUnitsRandomly() {
		for (Territory territory : territoryManager.getRandomTerritoryList()) {
			// Cycle through all players
			currentPlayer = players.getNextPlayer();

			// Place one unit on the territory
			try {
				territoryManager.changeTerritoryOwner(currentPlayer, territory,
						1);
			} catch (InvalidTerritoryStateException e) {
				e.printStackTrace();
			}

			// Remove the placed units from the player's supply
			currentPlayer.subtractSupplies(1);
		}

		// Place the remaining units randomly
		while (!players.supplyAllocated()) {
			// Cycle through all players
			currentPlayer = players.getNextPlayer();

			// Add one unit to a random territory
			currentPlayer.getRandomTerritory().addUnits(1);
			// Remove it from the player's supply
			currentPlayer.subtractSupplies(1);
		}

		// Reset the current player to player 1
		players.resetActivePlayer();
		currentPlayer = getActivePlayer();
		nextPlayer();
	}

	@Override
	public void placeUnits(String territory, int amount) {
		if(currentPlayer.getSupplies() > 0){
			territoryManager.getTerritoryMap().get(territory).addUnits(amount);
			currentPlayer.subtractSupplies(amount);
			// Send a notification to all clients
			notifyPlayers(new TerritoryUnitsChangedAction(territoryManager.getTerritoryMap().get(territory), territoryManager.getTerritoryMap().get(territory).getUnitCount()));
			supplyChanged((territoryManager.getTerritoryMap().get(territory).getOwner()));
		}

		if(currentPlayer.getSupplies() == 0){
			nextPhase();
		}
	}

	@Override
	public void attack(Territory sourceTerritory, Territory targetTerritory, int amount) {
		// Angreifer(amount) das nicht mehr als 3 und nicht weniger als 1 sein
		
		attackDice = getDice(amount);

		// herausziehen des jeweiligen territory aus der MAP
		notifyPlayers(new AttackAction(sourceTerritory, targetTerritory, amount));
	}

	public void defend(Territory sourceTerritory, Territory targetTerritory, int amount) {
		// Verteidiger(amount) darf nicht mehr als 2 und nicht weniger als 1 sein
		defendDice = getDice(amount);
		//notifyPlayers(new DefendAction(defendTerritory, amount));
		// nun wird der Kampf bzw. die zwei würfel verglichen "Kampf" findet statt
		calculateDice(attackDice, defendDice, territoryManager.getTerritoryMap().get(sourceTerritory.getName()), territoryManager.getTerritoryMap().get(targetTerritory.getName()));
	}

	public void calculateDice(List<Integer> attackDice, List<Integer> defendDice, Territory sourceTerritory, Territory targetTerritory) {
		int defendLoseUnits = 0;
		int attackLoseUnits = 0;
		Boolean conquered = false;
		String defenderMsg = null;
		String attackerMsg = null;
		int newUnitCnt = 0;
		Player oldOwner = targetTerritory.getOwner();
		

		attackingRound++;

		System.out.println("------ Kampfrunde nr: "+ attackingRound +" ------");
		System.out.println("Es kämpfen: "+ sourceTerritory.getName() +" VS. "+ targetTerritory.getName());
		System.out.println("Verteidigerwürfelanzahl: "+ defendDice.size() +" Verteidigunswürfel Werte: "+ defendDice);
		System.out.println("Anfreiferwürfelanzahl: "+attackDice.size() +" Angriffwürfel Werte: "+ attackDice);
		System.out.println("Anzahl des Defendterritory: "+targetTerritory.getUnitCount());
		System.out.println("----------------------------------------------");

		//if there are more defending than attacking dices!
		int lowestDiceNumber = defendDice.size();
		if (lowestDiceNumber > attackDice.size()){
			lowestDiceNumber = attackDice.size();
		}

		for(int i = 0; i < lowestDiceNumber; i++) {
			if(defendDice.get(i) > attackDice.get(i) && targetTerritory.getUnitCount() != 0) {
				System.out.println("Defensive: "+ defendDice.get(i) +" schlägt Offensive: "+ attackDice.get(i));
				attackLoseUnits++;
			}else if(defendDice.get(i) == attackDice.get(i) && targetTerritory.getUnitCount() != 0) {
				System.out.println("Defensive: "+ defendDice.get(i) +" schlägt Offensive: "+ attackDice.get(i) +" Gleiche Augenzahl!");
				attackLoseUnits++;
			}else if(defendDice.get(i) < attackDice.get(i) && targetTerritory.getUnitCount() != 0) {
				System.out.println("Offensive: "+ attackDice.get(i) +" schlägt Defensive: "+ defendDice.get(i));
				defendLoseUnits++;
			}

			System.out.println("OFFENSIVE verliert: "+ attackLoseUnits +" Einheiten");
			System.out.println("DEFENSIVE verliert: "+ defendLoseUnits +" Einheiten");

			// Wenn Land erobert
			if((targetTerritory.getUnitCount() - defendLoseUnits) == 0){
				System.out.println(targetTerritory.getName() + " ÜBERNOMMEN!");
				conquered = true;

				targetTerritory.subtractUnits(defendLoseUnits);

				try {
					defenderMsg = "Du hast " + targetTerritory.getName() + " an " + sourceTerritory.getOwner().getName() + " verloren.";
					attackerMsg = "Du hast " + targetTerritory.getName() + " von " + targetTerritory.getOwner().getName() + " erobert.";


					newUnitCnt = attackDice.size() - attackLoseUnits;
					territoryManager.changeTerritoryOwner(sourceTerritory.getOwner(), targetTerritory, newUnitCnt);

					System.out.println(targetTerritory.getOwner().getName() + " <--defend OWNER attacker Territories--> ");

					newUnitCnt = attackDice.size() - attackLoseUnits;
					notifyPlayers(new TerritoryUnitsChangedAction(targetTerritory, newUnitCnt));
				} catch (InvalidTerritoryStateException e) {
					e.printStackTrace();
				}

				sourceTerritory.subtractUnits(attackDice.size());
				notifyPlayers(new TerritoryUnitsChangedAction(sourceTerritory, newUnitCnt));
				recieveBonuscard = true;
			}
		}

		if(!conquered){
			System.out.println("Besetzung der Länder ...");
			sourceTerritory.subtractUnits(attackLoseUnits);
			System.out.println("ATTACKING TERRITORY: "+ sourceTerritory.getUnitCount() +" - "+ attackLoseUnits +" = "+ (sourceTerritory.getUnitCount() - attackLoseUnits));
			targetTerritory.subtractUnits(defendLoseUnits);
			System.out.println("DEFENDING TERRITORY: "+ targetTerritory.getUnitCount() +" - "+ defendLoseUnits +" = "+ (targetTerritory.getUnitCount() - defendLoseUnits));


			notifyPlayers(new TerritoryUnitsChangedAction(sourceTerritory, sourceTerritory.getUnitCount()));
			notifyPlayers(new TerritoryUnitsChangedAction(targetTerritory, targetTerritory.getUnitCount()));

			defenderMsg = targetTerritory.getOwner().getName() + "(" + targetTerritory.getName() + ")" + " hat " + defendLoseUnits + " Einheiten verloren. " + "\n"  + sourceTerritory.getOwner().getName() + "(" + sourceTerritory.getName() + ")" + " hat" + attackLoseUnits + " Einheiten verloren.";
			attackerMsg = sourceTerritory.getOwner().getName() + "(" + sourceTerritory.getName() + ")" + " hat " + attackLoseUnits + " Einheiten verloren. " + "\n"  + targetTerritory.getOwner().getName() + "(" + targetTerritory.getName() + ")" + " hat" + defendLoseUnits + " Einheiten verloren.";
		}

		List<Territory> attackersTerritories = sourceTerritory.getOwner().getTerritories();


		System.out.println("Spieler Vergleich: "+ sourceTerritory.getOwner().getName() +" = "+ getActivePlayer().getName());
		for (int i2 = 0 ; i2 < attackersTerritories.size(); i2++){
			System.out.println("attackersTerritories Spieler: "+ attackersTerritories.get(i2).getOwner().getName() +" | Liste der Länder des Angreifers:"+ attackersTerritories.get(i2).getName() +" | Einheiten: "+ attackersTerritories.get(i2).getUnitCount());
		}

		notifyPlayers(new EventBoxAction(sourceTerritory.getOwner(),attackerMsg));
		notifyPlayers(new EventBoxAction(oldOwner,defenderMsg));

		// läutet die nächste Phase ein nachdem ein Kampf statt gefunden hat. In dem Fall ATTACK1
		nextPhase();
	}

	@Override
	public void move(Territory source, Territory target, int amount)
	throws SimonRemoteException {

		Territory source2 = territoryManager.getTerritoryMap().get(source.getName());
		Territory target2 = territoryManager.getTerritoryMap().get(target.getName());
		
		source2.subtractUnits(amount);
		target2.addUnits(amount);

		notifyPlayers(new TerritoryUnitsChangedAction(source2, source.getUnitCount()));
		notifyPlayers(new TerritoryUnitsChangedAction(target2, target.getUnitCount()));
	}

	/*
	 * START: *** UNITS / FIGHT / DEFEND / MOVE FUNCTIONS *** 
	 * 
	 */


	/*
	 * START: *** PLAYER FUNCTIONS *** 
	 */

	/**
	 * Adds a player to the game and consequently to the list of observers
	 * @return 
	 */
	public Player addPlayer(String name, ClientMethods client) throws ServerFullException {
		if (started) {
			// Game is already in progress
			throw new ServerFullException();
		} else if (players.size() >= 6) {
			// Too many players
			// TODO Reenable this check (for debugging purposes off)
			//throw new ServerFullException();
		}

		// Add the client
		if (client == null) {
			throw new NullPointerException();
		}
		clients.add(client);

		Player player = new Player(name);

		players.add(player);

		// Output a success message
		System.out.println("Client connected.");

		notifyPlayers(new PlayerJoinedAction(player));

		return player;
	}

	/**
	 * Deletes a player from the list of observers
	 */
	public void deletePlayer(ClientMethods client) {
		clients.remove(client);
	}

	/**
	 * notifiy the Players with the current Action
	 * 
	 * @param arg
	 */
	private void notifyPlayers(Action arg) {
		// Notify all observers
		for (ClientMethods client:clients) {
			client.update(this, arg);
		}
	}

	/*
	 * END: *** PLAYER FUNCTIONS *** 
	 */

	/* 
	 * START: *** GETTER and SETTER ***
	 */

	/**
	 * Returns the current phase.
	 * @return
	 */
	public Phase getPhase() {
		return currentPhase;
	}

	/**
	 * get the active Player
	 * 
	 * @return Player
	 */
	public Player getActivePlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public List<Integer> getDice(int amount) {
		List<Integer> dice = new ArrayList<Integer>();

		for(int i = 0; i < amount; i++) {
			dice.add(i, (int) ((Math.random()) * 6 + 1));
		}

		Collections.sort(dice);
		Collections.reverse(dice);

		return dice;
	}

	@Override
	public Mission getMyMission(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BonusCard> getMyBonusCards(Player player) {
		// TODO Auto-generated method stub
		return player.getBonusCards();
	}

	@Override
	public HashMap<String, Territory> getTerritories() {
		return territoryManager.getTerritoryMap();
	}

	public void setCurrentPhase(Phase currentPhase) {
		this.currentPhase = currentPhase;
	}
	
	@Override
	public List<Territory> getMyTerritories(Player player) {
		return player.getTerritories();
	}

	@Override
	public List<Territory> getMyTerritoriesForAttacking(Player player) {

		List<Territory> territories = getMyTerritories(player);
		List<Territory> attackingTerritories = new ArrayList<Territory>();

		for(int i = 0; i < territories.size(); i++) {
			List<Territory> neighbors = territories.get(i).getNeighbors();
			for(int j = 0; j < neighbors.size() ;j++){
				if(!neighbors.get(j).getOwner().equals(player) && (territoryManager.getTerritoryMap().get(territories.get(i).getName()).getUnitCount() > 1)){
					if(!attackingTerritories.contains(territories.get(i))) {
						attackingTerritories.add(territories.get(i));
					}
				}
			}
		}
		return attackingTerritories;
	}

	// TODO: rekusive überprüfung der Nachbar der Nachbarn usw.

	/**
	 * get all the Terrietories for moving
	 * 
	 */
	public List<Territory> getMyTerritoriesForMoving(Player player) {
		
		List<Territory> territories = getMyTerritories(player);
		List<Territory> moveTerritories = new ArrayList<Territory>();

		for(int i = 0; i < territories.size(); i++) {
			List<Territory> neighbors = territories.get(i).getNeighbors();
			for(int j = 0; j < neighbors.size() ;j++){
				if(neighbors.get(j).getOwner().equals(player) && (territoryManager.getTerritoryMap().get(territories.get(i).getName()).getUnitCount() > 1)){
					if(!moveTerritories.contains(territories.get(i))) {
						moveTerritories.add(territories.get(i));
					}
				}
			}
		}
		return moveTerritories;
	}

	/**
	 * get the territories for moving by the selected Territory
	 */
	public List<Territory> getSimilarNeighborsOf(Territory territory) {
		List<Territory> similarNeighbors = territory.getNeighbors();

		for(Territory territory2 : similarNeighbors) {
			if(!territory2.getOwner().equals(territory.getOwner())){
				similarNeighbors.remove(territory2);
			}
		}
		return similarNeighbors;
	}
	@Override
	public List<Territory> getOpposingNeighborsOf(Territory territory) {
		List<Territory> opposingNeighbors = territory.getNeighbors();
		for(Territory territory2 : opposingNeighbors) {
			if(territory2.getOwner().equals(territory.getOwner())){
				opposingNeighbors.remove(territory2);
			}
		}
		return opposingNeighbors;
	}

	/**
	 * @return all Players
	 */
	public PlayerCollection getPlayers() {
		return players;
	}

	public TerritoryManager getTerritoryManager() {
		return territoryManager;
	}

	/**
	 * Returns whether the game is over
	 * 
	 * @return True, if somebody has won the game
	 */
	public boolean isOver() {
		// TODO Distinguish between world domination/missions
		return players.size() == 1;
	}

	/**
	 * Returns the winner of the game, if there is one.<br>
	 * If the game isn't finished yet, <code>null</code> will be returned.
	 * 
	 * @return Winner of the game
	 */
	public Player getWinner() {
		// Return the last man standing
		Player winner = null;
		if (isOver()) {
			winner = players.getNextPlayer();
		}
		return winner;
	}

	/* 
	 * END: *** GETTER and SETTER ***
	 */

	/*
	 * START: PREPARE / PHASE FUNCTIONS  / functions for the PHASE SWITCHING
	 */

	/**
	 * TODO doc
	 */
	private void prepareTurnInAction() {
		// Can the player turn in cards?
		if (currentPlayer.canTurnInCards()) {
			currentPhase = Phase.TURNINCARDS;
			notifyPlayers(new PhaseAction(currentPlayer, currentPhase, players));
		} else {
			// If the player can't turn in cards, skip to the next step
			preparePlacementAction();
		}
	}

	/**
	 * prepare the placement phase by setting the currentphase to Phase.PLACEMENT 
	 */
	private void preparePlacementAction() {
		calculateSupplies();
		currentPhase = Phase.PLACEMENT;
		//notifyPlayers(new PhaseAction(currentPlayer, currentPhase));
	}

	/**
	 * prepare the attack1 phase by setting the currentphase to Phase.ATTACK1
	 */
	private void prepareAttack1Action() {
		/*
		 * Figure out which territories of the current player could be used for
		 * an attack
		 * Must be owned by the player
		 * Must have at least 2 units
		 */
		currentPhase = Phase.ATTACK1;
	}
	/**
	 * prepare the attack2 phase by setting the currentphase to Phase.ATTACK2
	 */
	private void prepareAttack2Action() {
		/*
		 * Figure out which territories of the current player could be used for
		 * an attack
		 * Must be owned by the player
		 * Must have at least 2 units
		 */
		currentPhase = Phase.ATTACK2;
	}
	/**
	 * prepare the attack3 phase by setting the currentphase to Phase.ATTACK3
	 */
	private void prepareAttack3Action() {
		/*
		 * Figure out which territories of the current player could be used for
		 * an attack
		 * Must be owned by the player
		 * Must have at least 2 units
		 */
		currentPhase = Phase.ATTACK3;
	}

	/**
	 * prepare the MOVEMENT1 phase by setting the currentphase to Phase.MOVEMENT1
	 */
	private void prepareMovement1Action() {
		/*
		 * Figure out which territories have units which are eligible to be
		 * moved
		 * The territory's units must not have participated in a battle
		 * The territory needs at least 2 units
		 */
		currentPhase = Phase.MOVEMENT1;
	}

	private void prepareMovement2Action() {
		currentPhase = Phase.MOVEMENT2;
	}

	private void prepareMovement3Action() {
		currentPhase = Phase.MOVEMENT3;
	}

	@Override
	public void endAttackPhase() {
		prepareMovement1Action();
		notifyPlayers(new PhaseAction(currentPlayer, currentPhase, players));
		prepareMovement2Action();
	}

	public void endMovementPhase() {

		if(recieveBonuscard){
			currentPlayer.addBonusCard(bonusCardManager.retrieveCard());

			//TODO EVENTBOX
			System.out.println(currentPlayer.getName() + " erhielt eine Bonuskarte" + "Er besitzt folgende Karten " + currentPlayer.getBonusCards());;

			notifyPlayers(new BonusCardAction(currentPlayer));

			recieveBonuscard = false;
		}
		nextPlayer();

		prepareTurnInAction();
		notifyPlayers(new PhaseAction(currentPlayer, currentPhase, players));
	}

	/*
	 * END: PREPARE / PHASE FUNCTIONS  / functions for the PHASE SWITCHING
	 */

	@Override
	public void save() {
		// TODO Auto-generated method stub
		Store save = new Store(players, this);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		Store load = new Store(players, this);
	}

	/**
	 * TODO doc
	 */
	public void redeemBonusCards(List<BonusCard> cards) {
		// TODO make this a real exception
		assert currentPlayer.getBonusCards().containsAll(cards);
		assert cards.size() == 3;
		// TODO Check if the card triple is valid
		// falls Bonuskarten drei stück verfügbar
		// wenn genau drei karten verfügbar und alle drei gleich
		if(currentPlayer.getBonusCards().size() == 3 && 
				currentPlayer.getBonusCards().containsAll(cards)) {
			// Redeem the cards
			currentPlayer.removeBonusCards(cards);
			currentPlayer.addSupplies(bonusTracker.getNextBonus());
		}else if(currentPlayer.getBonusCards().size() >= 3) {

		}
	}

	public void supplyChanged(Player player) {
		notifyPlayers(new SupplyAction(player,player.getSupplies()));
	}
}
