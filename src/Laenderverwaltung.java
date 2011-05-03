public class Laenderverwaltung {

	public Laenderverwaltung(){
		
		String[][] laenderList = { { "Alaska", "NWT" }, 
				                   { "Alaska", "Alberta" },
				                   { "Alaska", "Kamtschatka" },
				                   { "NWT", "Alberta" },
				                   { "NWT", "Grönnland" },
				                   { "NWT", "Ontario"},
				                   { "Alberta", "Ontario"},
				                   { "Alberta", "Weststaaten"},
				                   { "Ontario", "Oststaaten"},
				                   { "Ontario", "Quebec"},
				                   { "Ontario", "Grönland"},
				                   { "Grönland", "Quebec"},
				                   { "Grönland", "Island"},
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
				                   { "Nordwestafrika", "Südeuropa"},
				                   { "Nordwestafrika", "Ägypten"},
				                   { "Nordwestafrika", "Ostafrika"},
				                   { "Nordwestafrika", "Kongo"},
				                   { "Ägypten", "Mitteleuropa"},
				                   { "Ägypten", "Südeuropa"},
				                   { "Ägypten", "Ostafrika"},
				                   { "Ostafrika", "Mittlerer Osten"},
				                   { "Ostafrika", "Kongo"},
				                   { "Ostafrika", "Südafrika"},
				                   { "Ostafrika", "Madagaskar"},
				                   { "Südafrika", "Kongo"},
				                   { "Südafrika", "Madagaskar"},
				                   { "Westeuropa", "Mitteleuropa"},
				                   { "Westeuropa", "Südeuropa"},
				                   { "Westeuropa", "Großbritannien"},
				                   { "Großbritannien", "Skandinavien"},
				                   { "Großbritannien", "Island"},
				                   { "Großbritannien", "Mitteleuropa"},
				                   { "Mitteleuropa", "Ukraine"},
				                   { "Mitteleuropa", "Skandinavien"},
				                   { "Island", "Skandinavien"},
				                   { "Skandinavien", "Ukraine"},
				                   { "Südeuropa", "Mittlerer Osten"},
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
		
		Land[] laender = new Array(42);
		//Nordamerika
		laender[0] = new Land("Alaska");
		laender[1] = new Land("NWT");
		laender[2] = new Land("Grönland");
		laender[3] = new Land("Alberta");
		laender[4] = new Land("Ontario");
		laender[5] = new Land("Quebec");
		laender[6] = new Land("Weststaaten");
		laender[7] = new Land("Oststaaten");
		laender[8] = new Land("Mittelamerika");
		//Südamerika
		laender[9] = new Land("Venezuela");
		laender[10] = new Land("Peru");
		laender[11] = new Land("Brasilien");
		laender[12] = new Land("Argentinien");
		// Afrika
		laender[13] = new Land("Nordwestafrika");
		laender[14] = new Land("Ägypten");
		laender[15] = new Land("Ostafrika");
		laender[16] = new Land("Kongo");
		laender[17] = new Land("Südafrika");
		laender[18] = new Land("Madagaskar");
		// Europa
		laender[19] = new Land("Island");
		laender[20] = new Land("Skandinavien");
		laender[21] = new Land("Ukraine");
		laender[22] = new Land("Großbritannien");
		laender[23] = new Land("Mitteleuropa");
		laender[24] = new Land("Westeuropa");
		laender[25] = new Land("Südeuropa");
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
		//Australien
		laender[38] = new Land("Indonesien");
		laender[39] = new Land("Neu-Guinea");
		laender[40] = new Land("West-Australien");
		laender[41] = new Land("Ost-Australien");
	}
}
