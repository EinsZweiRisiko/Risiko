package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.GameMethodsImpl;
import server.GameMethodsImpl.Phase;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;
import valueobjects.BonusCard.BonusCardType;

/**
 * Speichern und Laden
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
	 */
	List<Player> players;
	GameMethodsImpl game;
	private final String SAVE_PATH = "C:\\riskSave2.txt";

	public Store(PlayerCollection players, GameMethodsImpl game) {
		this.players = players;
		this.game = game;
		buildInput();
		save();
	}

	public List<String> buildInput() {
		// in Input werden die Daten zusammengetragen
		List<String> input = new ArrayList<String>();
		List<Territory> territories;

		// Allgemeines Input Build
		// der aktuelle Spieler der dran war
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

	public void filterLoadFile(List<String> loadText) {
		Player loadedPlayer = null;

		//TODO reImplement setter
		for(int i = 0; i < loadText.size(); i++) {
			if(loadText.get(i) == "# Aktueller Spieler") {
				for(int a = 0; a < players.size(); a++) {
					if(players.get(a).getName() == loadText.get(i+1)) {
						game.setCurrentPlayer(players.get(a));
					}
				}
			}

			if(loadText.get(i) == "# Aktuelle Phase") {
				Phase phase = null;

				if(loadText.get(i+1) == "START") {
					phase = Phase.START;
				}else if(loadText.get(i+1) == "TURNINCARDS") {
					phase = Phase.TURNINCARDS;
				}else if(loadText.get(i+1) == "PLACEMENT") {
					phase = Phase.PLACEMENT;
				}else if(loadText.get(i+1) == "ATTACK1") {
					phase = Phase.ATTACK1;
				}else if(loadText.get(i+1) == "ATTACK2") {
					phase = Phase.ATTACK2;
				}else if(loadText.get(i+1) == "ATTACK3") {
					phase = Phase.ATTACK3;
				}else if(loadText.get(i+1) == "MOVEMENT1") {
					phase = Phase.MOVEMENT1;
				}else if(loadText.get(i+1) == "MOVEMENT2") {
					phase = Phase.MOVEMENT2;
				}else if(loadText.get(i+1) == "MOVEMENT3") {
					phase = Phase.MOVEMENT3;
				}
				game.setCurrentPhase(phase);
			}

			// hier beginnt das laden der Inhalte für den jeweiligen Spieler steht in loadedPlayer

			if(loadText.get(i) == "# Name") {
				for(int a = 0; a < players.size(); a++) {
					if(players.get(a).getName() == loadText.get(i+1)) {
						loadedPlayer = players.get(a);
					}
				}
			}

			if(loadText.get(i) == "# Farbe") {
				loadedPlayer.setColor(Integer.parseInt(loadText.get(i+1)));
			}

			if(loadText.get(i) == "# Bonuskarten") {
				BonusCard bonusCard = null;
				BonusCardType loadedCardType = null;

				// solange die Zeile nicht der näcshten Sektion übereinstimmt
				for(int a = i+1; !loadText.get(a).contains("# AnzahlLänder"); a++) {
					if(loadText.get(i+1) == "Infantry") {
						loadedCardType = loadedCardType.Infantry;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);
					}else if(loadText.get(i+1) == "Cavalry") {
						loadedCardType = loadedCardType.Cavalry;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);
					}else if(loadText.get(i+1) == "Artillery") {
						loadedCardType = loadedCardType.Artillery;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);
					}else if(loadText.get(i+1) == "Wildcard") {
						loadedCardType = loadedCardType.Wildcard;
						bonusCard = new BonusCard(loadedCardType);
						loadedPlayer.addBonusCard(bonusCard);;
					}
					i = a;
				}
			}

			if(loadText.get(i) == "# BesitzendeLänder:AnzahlEinheitendrauf") {
				for(int a = i+1; !loadText.get(a).contains("# AnzahlLänder"); a++) {
					// jetzt wird es richtig FIES!
					char[] zeichen = loadText.get(a).toCharArray();
					int endIndex = 0;

					for(int x = 0; a < zeichen.length; x++) {
						if(zeichen[x] == ':') {
							endIndex = a;
						}
					}
					game.getTerritoryManager().getTerritoryMap().get(loadText.get(a).substring(0, (endIndex-1)));
				}
			}

			if(loadText.get(i) == "# AnzahlGesamtEinheiten") {

			}
			if(loadText.get(i) == "# AnzahlZuSetzendeEinheiten") {

			}
		}
	}

	public void load() {
		// einlesen der Daten
		// also seten den Variablen druch die setter Funktionen

		BufferedReader br;
		String line;
		int i = 0;
		List<String> loadText = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(SAVE_PATH));

			while((line = br.readLine()) != null){
				// hier wird zeilenweise eingelsen
				loadText.add(line);
				filterLoadFile(loadText);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ausgeben des gelesenen
		for(int x = 0; i < loadText.size(); x++) {
			System.out.println(loadText.get(x));
		}
	}
}
