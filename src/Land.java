
public class Land {
	
	private Spieler besitzer;
	private int anzahlEinheiten = 0;
	private String name;
	private Kontinent kontinent;
	private Land[] nachbarn;
	
	public Land(String name) {
		this.name = name;
		
	}
	
	
	
	// Getter und Setter
	
	public Spieler getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(Spieler besitzer) {
		this.besitzer = besitzer;
	}

	public int getAnzahlEinheiten() {
		return anzahlEinheiten;
	}

	public void setAnzahlEinheiten(int anzahlEinheiten) {
		this.anzahlEinheiten = anzahlEinheiten;
	}

	public Kontinent getKontinent() {
		return kontinent;
	}

	public void setKontinent(Kontinent kontinent) {
		this.kontinent = kontinent;
	}
	
}
