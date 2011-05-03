public class Laenderverwaltung {

	public Laenderverwaltung(){
		
		String[][] laenderList = { { "Alaska", "NWT" }, 
				                   { "Alaska", "Alberta" },
				                   { "Alaska", "Kamtschatka" },
				                   { "NWT", "Alberta" },
				                   { "NWT", "Gr�nnland" },
				                   { "NWT", "Ontario"},
				                   { "Alberta", "Ontario"},
				                   { "Alberta", "Weststaaten"},
				                   { "Ontario", "Oststaaten"},
				                   { "Ontario", "Quebec"},
				                   { "Ontario", "Gr�nland"},
				                   { "Gr�nland", "Quebec"},
				                   { "Gr�nland", "Island"},
				                   { "Weststaaten", "Oststaaten"},
				                   { "Weststaaten", "Mittelamerika"},
				                   { "Oststaaten", "Quebec"},
				                   { "Oststaaten", "Mittelamaerika"},
				                   { "Mittelamerika", "Venezuela"},
				                   { "Venezuela", "Peru"},
				                   { "Venezuela", "Brasilien"},
				                   { "Peru", "Brasilien"},
				                   { "Peru", "Argentinien"},
				                   { "Brasilien", "Nordwestafrika"},
				                   { "Brasilien", "Argentinien"},
				                   { "Nordwestafrika", "Westeuropa"},
				                   { "Nordwestafrika", "S�deuropa"},
				                   { "Nordwestafrika", "�gypten"},
				                   { "Nordwestafrika", "Ostafrika"},
				                   { "Nordwestafrika", "Kongo"},
				                   { "�gypten", "Mitteleuropa"},
				                   { "�gypten", "S�deuropa"},
				                   { "�gypten", "Ostafrika"},
				                   { "Ostafrika", "Mittlerer Osten"},
				                   { "Ostafrika", "Kongo"},
				                   { "Ostafrika", "S�dafrika"},
				                   { "Ostafrika", "Madagaskar"},
				                   { "S�dafrika", "Kongo"},
				                   { "S�dafrika", "Madagaskar"},
				                   { "Westeuropa", "Mitteleuropa"},
				                   { "Westeuropa", "S�deuropa"},
				                   { "Westeuropa", "Gro�britannien"},
				                   { "Gro�britannien", "Skandinavien"},
				                   { "Gro�britannien", "Island"},
				                   { "Gro�britannien", "Mitteleuropa"},
				                   { "Mitteleuropa", "Ukraine"},
				                   { "Mitteleuropa", "Skandinavien"},
				                   { "Island", "Skandinavien"},
				                   { "Skandinavien", "Ukraine"},
				                   { "S�deuropa", "Mittlerer Osten"},
				                   { "Mittlerer Osten", "Indien"},
				                   { "Mittlerer Osten", "Afghanistan"},
				                   { "Mittlerer Osten", "Ukraine"},
				                   { "Ukraine", "Afghanistan"},
				                   { "Ukraine", "Ural"},
				                   { "Ural", "Sibirien"},
				                   { "Ural", "Afghanistan"},
				                   { "Ural", "China"},
				                   { "Afghanistan", "China"},
				                   { "Afghanistan", "Indien"},
				                   { "Indien", "China"},
				                   { "Indien", "Siam"},
				                   { "China", "Mongolei"},
				                   { "China", "Siam"},
				                   { "China", "Sibirien"},
				                   { "Sibirien", "Mongolei"},
				                   { "Sibirien", "Irkutsk"},
				                   { "Sibirien", "Kamtschatka"},
				                   { "Jakutien", "Irkutsk"},
				                   { "Jakutien", "Kamtschatka"},
				                   { "Irkutsk", "Kamtschatka"},
				                   { "Japan", "Kamtschatka"},
				                   { "Japan", "Mongolei"},
				                   { "Mongolei", "Kamtschatka"},
				                   { "Siam", "Indonesien"},
				                   { "Indonesien", "Neu-Guinea"},
				                   { "Indonesien", "West-Australien"},
				                   { "West-Australien", "Ost-Australien"},
				                   { "West-Australien", "Neu-Guinea"},
				                   { "Ost-Australien", "Neu-Guinea"} };
		
		Kontinent[] kontinent = new Kontinent[6];
		Land[] laender = new Land[42];
		
		//Nordamerika
		laender[0] = new Land("Alaska");
		laender[1] = new Land("NWT");
		laender[2] = new Land("Gr�nland");
		laender[3] = new Land("Alberta");
		laender[4] = new Land("Ontario");
		laender[5] = new Land("Quebec");
		laender[6] = new Land("Weststaaten");
		laender[7] = new Land("Oststaaten");
		laender[8] = new Land("Mittelamerika");
		// L�nder zu Kontinenten hinzuf�gen (NORDAMERIKA)
		kontinent[0] = new Kontinent("Nordamerika");
		kontinent[0].setLaender(laender[0]);
		kontinent[0].setLaender(laender[1]);
		kontinent[0].setLaender(laender[2]);
		kontinent[0].setLaender(laender[3]);
		kontinent[0].setLaender(laender[4]);
		kontinent[0].setLaender(laender[5]);
		kontinent[0].setLaender(laender[6]);
		kontinent[0].setLaender(laender[7]);
		kontinent[0].setLaender(laender[8]);
		
		//S�damerika
		laender[9] = new Land("Venezuela");
		laender[10] = new Land("Peru");
		laender[11] = new Land("Brasilien");
		laender[12] = new Land("Argentinien");
		// L�nder zu Kontinenten hinzuf�gen (S�DAMERIKA)
		kontinent[1] = new Kontinent("S�damerika");
		kontinent[1].setLaender(laender[9]);
		kontinent[1].setLaender(laender[10]);
		kontinent[1].setLaender(laender[11]);
		kontinent[1].setLaender(laender[12]);
		
		// Afrika
		laender[13] = new Land("Nordwestafrika");
		laender[14] = new Land("�gypten");
		laender[15] = new Land("Ostafrika");
		laender[16] = new Land("Kongo");
		laender[17] = new Land("S�dafrika");
		laender[18] = new Land("Madagaskar");
		// L�nder zu Kontinenten hinzuf�gen (AFRIKA)
		kontinent[2] = new Kontinent("Afrika");
		kontinent[2].setLaender(laender[13]);
		kontinent[2].setLaender(laender[14]);
		kontinent[2].setLaender(laender[15]);
		kontinent[2].setLaender(laender[16]);
		kontinent[2].setLaender(laender[17]);
		kontinent[2].setLaender(laender[18]);
		
		// Europa
		laender[19] = new Land("Island");
		laender[20] = new Land("Skandinavien");
		laender[21] = new Land("Ukraine");
		laender[22] = new Land("Gro�britannien");
		laender[23] = new Land("Mitteleuropa");
		laender[24] = new Land("Westeuropa");
		laender[25] = new Land("S�deuropa");
		// L�nder zu Kontinenten hinzuf�gen (EUROPA)
		kontinent[3] = new Kontinent("EUROPA");
		kontinent[3].setLaender(laender[19]);
		kontinent[3].setLaender(laender[20]);
		kontinent[3].setLaender(laender[21]);
		kontinent[3].setLaender(laender[22]);
		kontinent[3].setLaender(laender[23]);
		kontinent[3].setLaender(laender[24]);
		kontinent[3].setLaender(laender[25]);
		
		// Asien
		laender[26] = new Land("Ural");
		laender[27] = new Land("Sibirien");
		laender[28] = new Land("Jakutien");
		laender[29] = new Land("Irkutsk");
		laender[30] = new Land("Kamtschatka");
		laender[31] = new Land("Mongolei");
		laender[32] = new Land("Japan");
		laender[33] = new Land("Afghanistan");
		laender[34] = new Land("China");
		laender[35] = new Land("Mittlerer Osten");
		laender[36] = new Land("Indien");
		laender[37] = new Land("Siam");
		// L�nder zu Kontinenten hinzuf�gen (ASIEN)
		kontinent[4] = new Kontinent("Asien");
		kontinent[4].setLaender(laender[26]);
		kontinent[4].setLaender(laender[27]);
		kontinent[4].setLaender(laender[28]);
		kontinent[4].setLaender(laender[29]);
		kontinent[4].setLaender(laender[30]);
		kontinent[4].setLaender(laender[31]);
		kontinent[4].setLaender(laender[32]);
		kontinent[4].setLaender(laender[33]);
		kontinent[4].setLaender(laender[34]);
		kontinent[4].setLaender(laender[35]);
		kontinent[4].setLaender(laender[36]);
		kontinent[4].setLaender(laender[37]);
		
		//Australien
		laender[38] = new Land("Indonesien");
		laender[39] = new Land("Neu-Guinea");
		laender[40] = new Land("West-Australien");
		laender[41] = new Land("Ost-Australien");
		// L�nder zu Kontinenten hinzuf�gen (AUSTRALIEN)
		kontinent[5] = new Kontinent("Australien");
		kontinent[5].setLaender(laender[38]);
		kontinent[5].setLaender(laender[39]);
		kontinent[5].setLaender(laender[40]);
		kontinent[5].setLaender(laender[41]);
	}
}
