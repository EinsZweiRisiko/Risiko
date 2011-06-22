package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;

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
	Player player;

	public Store(Player player) {
		this.player = player;
	}



	public ArrayList<String> buildInput() {

		ArrayList<String> input = new ArrayList<String>();
		ArrayList<Territory> territories;

		// Allgemeines Input Build


		//Spieler input Build
		input.add(player.getName());					// Name
		input.add(String.valueOf(player.getColor()));	// Farbe
		// Bonus Karten
		HashSet<BonusCard> territoryCards = player.getBonusCards();
		input.add(String.valueOf(territoryCards.size())); 		//Anzahl von Karten

		// TODO Typen der Bonuskarten einlesen und jeden Typ Zeilenweise schreiben/speichern

		//Länder im Besitz
		input.add(String.valueOf(player.getTerritoryCount())); // Anzahl der Länder

		territories = player.getTerritories();				// Name der Länder
		for(int i = 0; i <= territories.size(); i++) {
			input.add(territories.get(i).getName());
		}

		//anzahl der gesamten Einheiten
		input.add(String.valueOf(player.getAllUnits()));

		//anzahl der noch zu setzenden Einheiten
		input.add(String.valueOf(player.getSuppliesToAllocate()));

		// TODO Mission abspeichern


		return input;
	}

	public void load() {

	}

	public void save() {

		//zusammen bauen des input Arrays
		try{
			java.io.FileOutputStream fos = new java.io.FileOutputStream("C:\\test.txt");
			java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos);

			// TODO speichert er es Zeilenweise oder alles in eine Zeile?!
			oos.writeObject((java.util.ArrayList)  buildInput());
			oos.flush();
			fos.close();
		}
		catch(Exception e){}

	}

}
