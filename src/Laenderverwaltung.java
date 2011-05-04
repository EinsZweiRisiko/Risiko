import java.util.Hashtable;

/**
 * 
 * @author Jannes, Timur
 * 
 */
public class Laenderverwaltung {

	/**
	 * Liste mit allen L�ndernamen
	 */
	private String[] laenderNamen = { "Alaska", "Nordwest-Territorium",
			"Gr�nland", "Alberta", "Ontario", "Quebec", "Weststaaten",
			"Oststaaten", "Mittelamerika", "Venezuela", "Peru", "Brasilien",
			"Argentinien", "Nordwestafrika", "�gypten", "Ostafrika", "Kongo",
			"S�dafrika", "Madagaskar", "Island", "Skandinavien", "Ukraine",
			"Gro�britannien", "Mitteleuropa", "Westeuropa", "S�deuropa",
			"Ural", "Sibirien", "Jakutien", "Irkutsk", "Kamtschatka",
			"Mongolei", "Japan", "Afghanistan", "China", "Mittlerer Osten",
			"Indien", "Siam", "Indonesien", "Neu-Guinea", "West-Australien",
			"Ost-Australien" };

	/**
	 * Eine Liste aller Grenzen, die zwischen jeweils zwei L�ndern verlaufen
	 */
	private static final String[][] grenzen = {
			{ "Alaska", "Nordwest-Territorium" }, { "Alaska", "Alberta" },
			{ "Alaska", "Kamtschatka" }, { "Nordwest-Territorium", "Alberta" },
			{ "Nordwest-Territorium", "Gr�nland" },
			{ "Nordwest-Territorium", "Ontario" }, { "Alberta", "Ontario" },
			{ "Alberta", "Weststaaten" }, { "Ontario", "Oststaaten" },
			{ "Ontario", "Quebec" }, { "Ontario", "Gr�nland" },
			{ "Gr�nland", "Quebec" }, { "Gr�nland", "Island" },
			{ "Weststaaten", "Oststaaten" },
			{ "Weststaaten", "Mittelamerika" }, { "Oststaaten", "Quebec" },
			{ "Oststaaten", "Mittelamerika" },
			{ "Mittelamerika", "Venezuela" }, { "Venezuela", "Peru" },
			{ "Venezuela", "Brasilien" }, { "Peru", "Brasilien" },
			{ "Peru", "Argentinien" }, { "Brasilien", "Nordwestafrika" },
			{ "Brasilien", "Argentinien" }, { "Nordwestafrika", "Westeuropa" },
			{ "Nordwestafrika", "S�deuropa" }, { "Nordwestafrika", "�gypten" },
			{ "Nordwestafrika", "Ostafrika" }, { "Nordwestafrika", "Kongo" },
			{ "�gypten", "Mitteleuropa" }, { "�gypten", "S�deuropa" },
			{ "�gypten", "Ostafrika" }, { "Ostafrika", "Mittlerer Osten" },
			{ "Ostafrika", "Kongo" }, { "Ostafrika", "S�dafrika" },
			{ "Ostafrika", "Madagaskar" }, { "S�dafrika", "Kongo" },
			{ "S�dafrika", "Madagaskar" }, { "Westeuropa", "Mitteleuropa" },
			{ "Westeuropa", "S�deuropa" }, { "Westeuropa", "Gro�britannien" },
			{ "Gro�britannien", "Skandinavien" },
			{ "Gro�britannien", "Island" },
			{ "Gro�britannien", "Mitteleuropa" },
			{ "Mitteleuropa", "Ukraine" }, { "Mitteleuropa", "Skandinavien" },
			{ "Island", "Skandinavien" }, { "Skandinavien", "Ukraine" },
			{ "S�deuropa", "Mittlerer Osten" },
			{ "Mittlerer Osten", "Indien" },
			{ "Mittlerer Osten", "Afghanistan" },
			{ "Mittlerer Osten", "Ukraine" }, { "Ukraine", "Afghanistan" },
			{ "Ukraine", "Ural" }, { "Ural", "Sibirien" },
			{ "Ural", "Afghanistan" }, { "Ural", "China" },
			{ "Afghanistan", "China" }, { "Afghanistan", "Indien" },
			{ "Indien", "China" }, { "Indien", "Siam" },
			{ "China", "Mongolei" }, { "China", "Siam" },
			{ "China", "Sibirien" }, { "Sibirien", "Mongolei" },
			{ "Sibirien", "Irkutsk" }, { "Sibirien", "Kamtschatka" },
			{ "Jakutien", "Irkutsk" }, { "Jakutien", "Kamtschatka" },
			{ "Irkutsk", "Kamtschatka" }, { "Japan", "Kamtschatka" },
			{ "Japan", "Mongolei" }, { "Mongolei", "Kamtschatka" },
			{ "Siam", "Indonesien" }, { "Indonesien", "Neu-Guinea" },
			{ "Indonesien", "West-Australien" },
			{ "West-Australien", "Ost-Australien" },
			{ "West-Australien", "Neu-Guinea" },
			{ "Ost-Australien", "Neu-Guinea" } };

	/**
	 * Liste aller Kontinente
	 */
	private Kontinent[] kontinente = new Kontinent[6];

	/**
	 * Liste aller L�nderobjekte als Hasthable
	 */
	private Hashtable<String, Land> laender = new Hashtable<String, Land>();

	/**
	 * Konstruktor
	 */
	public Laenderverwaltung() {
		// L�nder erstellen
		for (String name : laenderNamen) {
			laender.put(name, new Land(name));
		}

		// Nachbarl�nder zuweisen
		initNachbarlaender();

		// Nordamerika erstellen
		kontinente[0] = new Kontinent("Nordamerika");
		kontinente[0].addLand(laender.get("Alaska"));
		kontinente[0].addLand(laender.get("Nordwest-Territorium"));
		kontinente[0].addLand(laender.get("Gr�nland"));
		kontinente[0].addLand(laender.get("Alberta"));
		kontinente[0].addLand(laender.get("Ontario"));
		kontinente[0].addLand(laender.get("Quebec"));
		kontinente[0].addLand(laender.get("Weststaaten"));
		kontinente[0].addLand(laender.get("Oststaaten"));
		kontinente[0].addLand(laender.get("Mittelamerika"));

		// S�damerika erstellen
		kontinente[1] = new Kontinent("S�damerika");
		kontinente[1].addLand(laender.get("Venezuela"));
		kontinente[1].addLand(laender.get("Peru"));
		kontinente[1].addLand(laender.get("Brasilien"));
		kontinente[1].addLand(laender.get("Argentinien"));

		// Afrika erstellen
		kontinente[2] = new Kontinent("Afrika");
		kontinente[2].addLand(laender.get("Nordwestafrika"));
		kontinente[2].addLand(laender.get("�gypten"));
		kontinente[2].addLand(laender.get("Ostafrika"));
		kontinente[2].addLand(laender.get("Kongo"));
		kontinente[2].addLand(laender.get("S�dafrika"));
		kontinente[2].addLand(laender.get("Madagaskar"));

		// Europa erstellen
		kontinente[3] = new Kontinent("Europa");
		kontinente[3].addLand(laender.get("Island"));
		kontinente[3].addLand(laender.get("Skandinavien"));
		kontinente[3].addLand(laender.get("Ukraine"));
		kontinente[3].addLand(laender.get("Gro�britannien"));
		kontinente[3].addLand(laender.get("Mitteleuropa"));
		kontinente[3].addLand(laender.get("Westeuropa"));
		kontinente[3].addLand(laender.get("S�deuropa"));

		// Asien erstellen
		kontinente[4] = new Kontinent("Asien");
		kontinente[4].addLand(laender.get("Ural"));
		kontinente[4].addLand(laender.get("Sibirien"));
		kontinente[4].addLand(laender.get("Jakutien"));
		kontinente[4].addLand(laender.get("Irkutsk"));
		kontinente[4].addLand(laender.get("Kamtschatka"));
		kontinente[4].addLand(laender.get("Mongolei"));
		kontinente[4].addLand(laender.get("Japan"));
		kontinente[4].addLand(laender.get("Afghanistan"));
		kontinente[4].addLand(laender.get("China"));
		kontinente[4].addLand(laender.get("Mittlerer Osten"));
		kontinente[4].addLand(laender.get("Indien"));
		kontinente[4].addLand(laender.get("Siam"));

		// Australien erstellen
		kontinente[5] = new Kontinent("Australien");
		kontinente[5].addLand(laender.get("Indonesien"));
		kontinente[5].addLand(laender.get("Neu-Guinea"));
		kontinente[5].addLand(laender.get("West-Australien"));
		kontinente[5].addLand(laender.get("Ost-Australien"));
	}

	/**
	 * Berechnet aus der Grenzliste f�r jedes Land alle benachbarten L�nder
	 */
	private void initNachbarlaender() {
		Land land1;
		Land land2;
		for (String[] grenze : grenzen) {
			// Land-Objekte holen
			land1 = getLandByName(grenze[0]);
			land2 = getLandByName(grenze[1]);
			// Gegenseitig als Nachbarn hinzuf�gen
			land1.addNachbar(land2);
			land2.addNachbar(land1);
		}
	}

	/**
	 * Sucht nach einem Land
	 * 
	 * @param name
	 *            Landname als String
	 * @return Landobjekt
	 */
	public Land getLandByName(String name) {
		return laender.get(name);
	}

	/**
	 * Liefert ein Land durch Angabe einer Zahl
	 * 
	 * @param number
	 * @return Land mit dieser Nummer
	 */
	public Land getLandByNumber(int number) {
		// TODO: diese Methode sollte es eigentlich nicht geben
		return getLandByName(laenderNamen[number]);
	}

	public boolean isAlleLaenderBesetzt() {

		for (int i = 0; i < 41; i++) {
			if(getLandByName(laenderNamen[i]).getBesitzer() == null){
				return false;
			}
		}
		return true;
	}
}
