package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import server.GameMethodsImpl;
import server.GameMethodsImpl.Phase;
import server.exceptions.InvalidTerritoryStateException;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;
import valueobjects.BonusCard.BonusCardType;

/**
 * Save and load
 * 
 * @author Timur
 */
// Klasse dient zum speichern der Value Objects in eine TXT datei

public class Store {

	/*	Es wird am Anfang erstmal allgemeine Informationen abgespeichert dazu gehören:
	 * 
	 * Welche Runde
	 * Welcher Spieler ist gerade dran
	 * In welcher Phase befindet sich der Spieler
	 * 
	 * 
	 */

	/* es werden vom SPIELER folgende Informationen abgespeichert:
	 * 
	 * Name
	 * Farbe
	 * Bonuskarten
	 * Anzahl der besitzenden Länder
	 * Länder in Besitz
	 * Gesamt Einheiten
	 * Supply Einheiten
	 * Mission
	 * 
	 * 
	 * Die Informationen liegen alle in einer TXT Datei die in Kategorien unterteilt sind
	 * Siehe Textdatei
	 */
	List<Player> players;
	GameMethodsImpl game;
	private final String SAVE_PATH = "C:\\riskSave2.txt";
	List<String> playerMac;

	/**
	 * Constructur that get the parameters from the Server to handle with it
	 * @param players
	 * @param game
	 * @param arg
	 */
	public Store(PlayerCollection players, GameMethodsImpl game, String arg) {
		this.players = players;
		this.game = game;
		playerMac = new ArrayList<String>();

		if(arg == "save") {
			buildInput();
			save();
		}else if(arg == "load") {
			try {
				filterLoadFile(load());
			} catch (InvalidTerritoryStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//filterLoad2
		}
	}

	/**
	 * build the save Input List that will be write later in the .txt
	 * @return
	 */
	public List<String> buildInput() {
		// in Input werden die Daten zusammengetragen
		List<String> input = new ArrayList<String>();
		List<Territory> territories;


		// Allgemeines Input Build
		input.add("# Anzahl Spieler");
		input.add(String.valueOf(players.size()+1));

		// der aktuelle Spieler der dran ist
		input.add("# Aktueller Spieler");
		input.add(game.getActivePlayer().getName());

		//die Aktuelle Phase
		input.add("# Aktuelle Phase");
		input.add(game.getPhase().name());

		// hier beginnt das Inputbuild für jeden Spieler
		for(int a = 0; a < players.size(); a++) {
			//Spieler input Build
			input.add("# Name");
			input.add(players.get(a).getName());	// Name

			//MAC ADRESSE des SPIELERS
			input.add("# MAC Adresse");
			try {
				input.add(getMacAddress());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Spielerfarbe
			input.add("# Farbe");
			input.add(String.valueOf(players.get(a).getColor()));	// Farbe

			//Anzahl der Bonuskarten
			input.add("# AnzahlBonusKarten");
			input.add(String.valueOf(players.get(a).getBonusCards().size())); 	//Anzahl von Karten

			// Bonus Karten
			input.add("# Bonuskarten");
			for(int i = 0; i < players.get(a).getBonusCards().size(); i++) {
				input.add(players.get(a).getBonusCards().get(i).getType().name());
			}

			//Länder im Besitz
			input.add("# AnzahlLänder");
			input.add(String.valueOf(players.get(a).getTerritoryCount())); // Anzahl der Länder

			// Territory Liste
			input.add("# BesitzendeLänder:AnzahlEinheitendrauf");
			territories = players.get(a).getTerritories();				// Name der Länder
			for(int i = 0; i < territories.size(); i++) {
				input.add(territories.get(i).getName()+":"+ String.valueOf(territories.get(i).getUnitCount()));
			}

			//anzahl der gesamten Einheiten
			input.add("# AnzahlGesamtEinheiten");
			input.add(String.valueOf(players.get(a).getUnitCount()));

			//anzahl der noch zu setzenden Einheiten
			input.add("# AnzahlZuSetzendeEinheiten");
			input.add(String.valueOf(players.get(a).getSupplies()));

			// TODO Mission abspeichern muss noch geschrieben werden

		}
		return input;
	}
/**
 * edit the created loadText List and set the Values of the Server by the specified Data
 * 
 * @param loadText
 * @throws InvalidTerritoryStateException
 */
	public void filterLoadFile(List<String> loadText) throws InvalidTerritoryStateException {
		Player loadedPlayer = null;
		int player = 0;
		// player = server.addPlayer(name, this);

		System.out.println("[LOAD] ich bin in der filter METHODE VON LOAD du ARSCH!");
		// erstellen der Player

		//TODO reImplement setter
		for(int i = 0; i < loadText.size(); i++) {
			if(loadText.get(i).equalsIgnoreCase("# Aktueller Spieler")) {
				for(int a = 0; a < players.size(); a++) {
					if(players.get(a).getName().equalsIgnoreCase(loadText.get(i+1))) {
						game.setCurrentPlayer(players.get(a));
						System.out.println("Der geladene Spieler: "+ players.get(a).getName() +" wurde gesetzt");
					}
				}
			}

			if(loadText.get(i).equalsIgnoreCase("# Aktuelle Phase")) {
				System.out.println("[LOAD] Loaded Phase wird gesetzt");
				Phase phase = null;

				if(loadText.get(i+1).equalsIgnoreCase("START")) {
					phase = Phase.START;
				}else if(loadText.get(i+1).equalsIgnoreCase("TURNINCARDS")) {
					phase = Phase.TURNINCARDS;
				}else if(loadText.get(i+1).equalsIgnoreCase("PLACEMENT")) {
					phase = Phase.PLACEMENT;
				}else if(loadText.get(i+1).equalsIgnoreCase("ATTACK1")) {
					phase = Phase.ATTACK1;
				}else if(loadText.get(i+1).equalsIgnoreCase("ATTACK2")) {
					phase = Phase.ATTACK2;
				}else if(loadText.get(i+1).equalsIgnoreCase("ATTACK3")) {
					phase = Phase.ATTACK3;
				}else if(loadText.get(i+1).equalsIgnoreCase("MOVEMENT1")) {
					phase = Phase.MOVEMENT1;
				}else if(loadText.get(i+1).equalsIgnoreCase("MOVEMENT2")) {
					phase = Phase.MOVEMENT2;
				}else if(loadText.get(i+1).equalsIgnoreCase("MOVEMENT3")) {
					phase = Phase.MOVEMENT3;
				}
				game.setCurrentPhase(phase);
			}

			// hier beginnt das laden der Inhalte für den jeweiligen Spieler steht in loadedPlayer

			if(loadText.get(i).equalsIgnoreCase("# Name")) {
				System.out.println("[LOAD] Name wird gesetzt");
				for(int a = 0; a < players.size(); a++) {
					if(players.get(a).getName().equalsIgnoreCase(loadText.get(i+1))) {
						loadedPlayer = players.get(a);
						playerMac.add(players.get(a).getName());
					}
				}
			}

			// MAC Adresse zum Spieler zu gewiesen / erst Player danach MAC Adresse
			if(loadText.get(i).equalsIgnoreCase("# MAC Adresse")) {
				System.out.println("[LOAD] MAC wird gesetzt");
				playerMac.add(loadText.get(i+1));
			}

			if(loadText.get(i).equalsIgnoreCase("# Farbe")) {
				loadedPlayer.setColor(Integer.parseInt(loadText.get(i+1)));
			}

			if(loadText.get(i).equalsIgnoreCase("# Bonuskarten")) {
				BonusCard bonusCard = null;
				BonusCardType loadedCardType = null;

				// solange die Zeile nicht der näcshten Sektion übereinstimmt
				for(int a = i+1; !loadText.get(a).contains("# AnzahlLänder"); a++) {
					if(loadText.get(i+1).equalsIgnoreCase("Infantry")) {
						loadedCardType = BonusCardType.Infantry;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);
					}else if(loadText.get(i+1).equalsIgnoreCase("Cavalry")) {
						loadedCardType = BonusCardType.Cavalry;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);
					}else if(loadText.get(i+1).equalsIgnoreCase("Artillery")) {
						loadedCardType = BonusCardType.Artillery;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);
					}else if(loadText.get(i+1).equalsIgnoreCase("Wildcard")) {
						loadedCardType = BonusCardType.Wildcard;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);;
					}
					i = a;
				}
			}

			if(loadText.get(i).equalsIgnoreCase("# BesitzendeLänder:AnzahlEinheitendrauf")) {
				System.out.println("[LOAD] besitzende Länder wird gesetzt");
				for(int a = i+1; !loadText.get(a).contains("# AnzahlGesamtEinheiten"); a++) {
					
					char[] zeichen = loadText.get(a).toCharArray();
					int endIndexTerritory = 0;

					for(int x = 0; x < zeichen.length; x++) {
						System.out.println("CHAR ARRAY: "+ zeichen[x]);
						if(zeichen[x] == ':') {
							endIndexTerritory = x;
							System.out.println("ENDE DES A'S: "+ x);
						}
					}

					String s1 = loadText.get(a).substring(0, (endIndexTerritory));
					System.out.println("was steht als Territory drine?!: "+ s1);

					Territory loadedTerritory = game.getTerritoryManager().getTerritoryMap().get(s1);

					int units =  Integer.parseInt(loadText.get(a).substring((endIndexTerritory+1), loadText.get(a).length()));
					//int units = game.getTerritoryManager().getTerritoryMap().get(s2).getUnitCount();
					System.out.println("[LOAD] Territory to change: "+ loadedTerritory.getName());
					System.out.println("[LOAD] Player to change: "+ loadedPlayer.getName());
					System.out.println("[LOAD] Units to change: "+ units);

					// 2. Art:
					game.getTerritoryManager().getTerritoryMap().get(s1).setOwner(loadedPlayer);
					game.getTerritoryManager().getTerritoryMap().get(s1).setUnitCount(units);
				}
			}

			if(loadText.get(i).equalsIgnoreCase("# AnzahlZuSetzendeEinheiten")) {
				loadedPlayer.setSuppliesToAllocate(Integer.parseInt(loadText.get(i+1)));
			}

			// hoch setzten des Spielers
			player++;
		}
	}


	public void filterLoad2() {

	}
	
	/**
	 * load the txt file and read the data into an List
	 * @return
	 */
	public List<String> load() {
		// einlesen der Daten
		// also seten den Variablen druch die setter Funktionen

		BufferedReader br;
		String line;
		List<String> loadText = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(SAVE_PATH));

			while((line = br.readLine()) != null){
				// hier wird zeilenweise eingelsen
				loadText.add(line);
				//filterLoadFile(loadText);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// ausgeben des gelesenen
		for(int x = 0; x < loadText.size(); x++) {
			System.out.println(loadText.get(x));
		}

		return loadText;
	}

	/**
	 * save the created input List into a txt file
	 */
	public void save() {

		//zusammen bauen des input Arrays
		List<String> input = new ArrayList<String>();

		for(int i = 0; i < buildInput().size(); i++) {
			System.out.println("Es wurde folgendes gelesen: "+ buildInput().get(i));
			input.add(buildInput().get(i));
		}

		// speichert
		try
		{
			File file = new File(SAVE_PATH);
			FileWriter fw = new FileWriter(file);

			for(int i = 0; i < input.size(); i++) {
				fw.write(input.get(i));
				fw.write("\r\n");
			}
			fw.flush();
			fw.close();
			System.out.println("GESPEICHERT!");
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	/**
	 * get the MAC Adress of the loval Machine/Client
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getMacAddress() throws Exception {

		String result = "";
		try {
			for (NetworkInterface ni : Collections.list(
					NetworkInterface.getNetworkInterfaces())) {
				byte[] hardwareAddress = ni.getHardwareAddress();

				if (hardwareAddress != null) {
					for (int i = 0; i < hardwareAddress.length; i++) {
						result += String.format((i == 0 ? "" : "") + "%02X", hardwareAddress[i]);
					}
					if (result.length() > 0 && !ni.isLoopback()) { return result; }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
