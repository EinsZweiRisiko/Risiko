import java.util.ArrayList;

/**
 * Land-Klasse
 * @author Jannes
 *
 */
public class Land {
	
	private Spieler besitzer;
	private int anzahlEinheiten = 0;
	private String name;
	private Kontinent kontinent;
	private ArrayList<Land> nachbarn = new ArrayList<Land>();
	
	public Land(String name) {
		this.name = name;
		
	}
	
	public void addNachbar(Land land) {
		nachbarn.add(land);
	}
	
	public boolean istNachbar(Land land) {
		if (nachbarn.contains(land)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Getter und Setter
	
	public String getName() {
		return name;
	}
	
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
