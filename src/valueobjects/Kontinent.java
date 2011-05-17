package valueobjects;

import java.util.ArrayList;


public class Kontinent {
	
	private String name;

	private ArrayList<Land> laenderListe = new ArrayList<Land>();

	private int bonusSupply;
	
	public Kontinent(String name, int bonusSupply){
		this.name = name;
		this.bonusSupply = bonusSupply;
	}
	
	public void addLand(Land land){
		laenderListe.add(land);
	}

	public int getBonusSupply() {
		return bonusSupply;
	}
	
	
	public ArrayList<Land> getCountries() {
		return laenderListe;
	}
	
}
