package valueobjects;

import java.util.ArrayList;


public class Continent {
	
	private String name;

	private ArrayList<Territory> laenderListe = new ArrayList<Territory>();

	private int bonusSupply;
	
	public Continent(String name, int bonusSupply){
		this.name = name;
		this.bonusSupply = bonusSupply;
	}
	
	public void addLand(Territory land){
		laenderListe.add(land);
	}

	public int getBonusSupply() {
		return bonusSupply;
	}
	
	
	public ArrayList<Territory> getCountries() {
		return laenderListe;
	}
	
}
