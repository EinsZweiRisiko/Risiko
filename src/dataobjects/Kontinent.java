package dataobjects;

import java.util.ArrayList;


public class Kontinent {
	
	private String name;
	private ArrayList<Land> laenderListe = new ArrayList<Land>();
	
	public Kontinent(String name){
		this.setName(name);
	}
	
	public void addLand(Land land){
		laenderListe.add(land);
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
