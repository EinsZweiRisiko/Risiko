public class Risiko {
	
	private int spielerzahl;
	private Spieler[] spieler;
	
	public Risiko() {
		Laenderverwaltung laenderverwaltung = new Laenderverwaltung();
		spielerzahl = IO.readInt("Wieviele Spieler?: ");
		
		spieler = new Spieler[spielerzahl];
		
		//Spielernamen setzen
		for(int i = 0; i < spielerzahl; i++){
			String name = IO.readString("Name Spieler "+ (i+1) +": ");
			spieler[i] = new Spieler(name);
		}
	}
	
	public static void main(String args[]) {
		Risiko risiko = new Risiko();		
	}
}
