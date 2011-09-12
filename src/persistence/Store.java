package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import server.GameMethodsImpl;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;

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
	PlayerCollection playerManager;
	private final String SAVE_PATH = "C:\\riskSave2.txt";

	public Store(PlayerCollection playerManager, GameMethodsImpl game) {
		this.playerManager = playerManager;
		this.game = game;
	}

	public List<String> buildInput() {

		// holt sich alle Spieler und schreibt sie lokal
		players = playerManager;

		// in Input werden die Daten 
		List<String> input = new ArrayList<String>();
		List<Territory> territories;

		// Allgemeines Input Build
		// der aktuelle Spieler der dran war
		input.add("# Aktueller Spieler");
		input.add(game.getActivePlayer().getName());
		
		//die Aktuelle Phase
		input.add("# Aktuelle Phase");
		// TODO Pahse zum String konventieren altaaaa!!!1
//		input.add(Enum.toString(game.getCurrentPhase()));
		
		/* TODO muss mit allen Spielern geschehen
		 *  eine while schleife durchladen aller spieler
		 *  alle spieler bekommen wir von PlayerManager mit getPlayers();
		 */

		for(int a = 0; a <= players.size()-1; a++) {
			//Spieler input Build
			input.add("# Name");
			input.add(players.get(a).getName());	// Name

			input.add("# Farbe");
			input.add(String.valueOf(players.get(a).getColor()));	// Farbe

			
			//Anzahl der Bonuskarten
			List<BonusCard> territoryCards = players.get(a).getBonusCards();
			input.add("# AnzahlBonusKarten");
			input.add(String.valueOf(territoryCards.size())); 	//Anzahl von Karten
			
			// Bonus Karten
			input.add("# Bonuskarten");
			for(int i = 0; i <= territoryCards.size(); i++) {
				input.add(territoryCards.get(i).getType().toString());
			}
			
			//Länder im Besitz
			input.add("# AnzahlLänder");
			input.add(String.valueOf(players.get(a).getTerritoryCount())); // Anzahl der Länder
			
			input.add("# BesitzendeLänder:AnzahlEinheitendrauf");
			territories = players.get(a).getTerritories();				// Name der Länder
			for(int i = 0; i <= territories.size(); i++) {
				input.add(territories.get(i).getName()+":"+ String.valueOf(territories.get(i).getUnits()));
			}
			
			//anzahl der gesamten Einheiten
			input.add("# AnzahlGesamtEinheiten");
			input.add(String.valueOf(players.get(a).getAllUnits()));

			//anzahl der noch zu setzenden Einheiten
			input.add("# AnzahlZuSetzendeEinheiten");
			input.add(String.valueOf(players.get(a).getSupplies()));

			// TODO Mission abspeichern muss noch geschrieben werden

		}
		return input;
	}

	public void save() {
		/*
		System.out.println("START DES SPEICHERNS");
		for(int i = 0; i <= buildInput().size(); i++) {
			System.out.println(buildInput().get(i));
		}
		System.out.println("ENDE DES SPEICHERNS");
		 */

		//zusammen bauen des input Arrays
		List<String> input = new ArrayList<String>();
		for(int i = 0; i <= buildInput().size()-1; i++) {
			System.out.println("Es wurde folgendes gelesen: "+ buildInput().get(i));
			input.add(buildInput().get(i));
		}

		// speichert
		try
		{
			File file = new File(SAVE_PATH);
			FileWriter fw = new FileWriter(file);

			for(int i = 0; i <= input.size()-1; i++) {
				fw.write(input.get(i));
				fw.write("\r\n");
			}
			fw.flush();
			fw.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

	}

	public void filterLoadFile(List<String> loadText) {
		int player = 0;

		for(int i = 0; i <= loadText.size(); i++) {
			if(loadText.get(i) == "# Name") {
				player++;
				players.get(player).setName(loadText.get(i+1));
			}
			if(loadText.get(i) == "# Farbe") {
				players.get(player).setColor(Integer.parseInt(loadText.get(i+1)));
			}
			if(loadText.get(i) == "# Bonuskarten") {
				while(loadText.get(i+1).contains("#")) {

				}
				//players.get(player).setBonusCards
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
	}
}
