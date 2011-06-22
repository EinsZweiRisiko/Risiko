package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import domain.managers.PlayerManager;

import valueobjects.BonusCard;
import valueobjects.Player;
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
	ArrayList<Player> players;
	PlayerManager playerManager;
	private final String SAVE_PATH = "C:\\riskSave.txt";

	public Store(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public ArrayList<String> buildInput() {

		// holt sich alle Spieler und schreibt sie lokal
		players = playerManager.getPlayers();

		// in Input werden die Daten 
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<Territory> territories;

		// Allgemeines Input Build

		/* TODO muss mit allen Spielern geschehen
		 *  eine while schleife durchladen aller spieler
		 *  alle spieler bekommen wir von PlayerManager mit getPlayers();
		 */

		for(int a = 0; a <= players.size(); a++) {
			//Spieler input Build
			input.add("# Name");
			input.add(players.get(a).getName());					// Name
			input.add("# Farbe");
			input.add(String.valueOf(players.get(a).getColor()));	// Farbe

			// Bonus Karten
			input.add("# Bonuskarten");
			HashSet<BonusCard> territoryCards = players.get(a).getBonusCards();
			input.add("# AnzahlBonusKarten");
			input.add(String.valueOf(territoryCards.size())); 		//Anzahl von Karten

			// TODO Typen der Bonuskarten einlesen und jeden Typ Zeilenweise schreiben/speichern
			input.add("# Bonuskarten");

			//Länder im Besitz
			input.add("# AnzahlLänder");
			input.add(String.valueOf(players.get(a).getTerritoryCount())); // Anzahl der Länder

			input.add("# BesitzendeLänder");
			territories = players.get(a).getTerritories();				// Name der Länder
			for(int i = 0; i <= territories.size(); i++) {
				input.add(territories.get(i).getName());
			}

			//anzahl der gesamten Einheiten
			input.add("# AnzahlGesamtEinheiten");
			input.add(String.valueOf(players.get(a).getAllUnits()));

			//anzahl der noch zu setzenden Einheiten
			input.add("# AnzahlZuSetzendeEinheiten");
			input.add(String.valueOf(players.get(a).getSuppliesToAllocate()));

			// TODO Mission abspeichern

		}
		return input;
	}

	public void load() {
		// einlesen der Daten
		// also seten den Variablen druch die setter Funktionen

		BufferedReader br;
		String line;
		int i = 0;
		
		try {
			br = new BufferedReader(new FileReader(SAVE_PATH));
			
			while((line = br.readLine()) != null){
				
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void save() {

			//zusammen bauen des input Arrays
			try{
				java.io.FileOutputStream fos = new java.io.FileOutputStream(SAVE_PATH);
				java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);

				// TODO speichert er es Zeilenweise oder alles in eine Zeile?!
				oos.writeObject((java.util.ArrayList)  buildInput());
				oos.flush();
				fos.close();
			}
			catch(Exception e){}
		}

	}
