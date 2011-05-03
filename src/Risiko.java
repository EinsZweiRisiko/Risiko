public class Risiko {
	

	public Risiko() {
		
		//Länder && Spieler initialisieren
		Laenderverwaltung laenderVerwaltung = new Laenderverwaltung();
		Spielerverwaltung spielerVerwaltung = new Spielerverwaltung();
		
		while(!spielZuEnde()) {
			Spielzyklus spielzyklus = new Spielzyklus(spielerVerwaltung.getAktSpieler(), laenderVerwaltung);
			spielerVerwaltung.naechsterSpieler();
		}
	}
	
	public static void main(String args[]) {
		Risiko risiko = new Risiko();		
	}
	
	private boolean spielZuEnde(){
		//TODO Überprüfungsfunktion erstellen
		return false;
	}
}
