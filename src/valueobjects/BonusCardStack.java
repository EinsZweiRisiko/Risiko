package valueobjects;

import java.util.ArrayList;
import java.util.Collections;


public class BonusCardStack extends ArrayList<BonusCard> {

	private static final long serialVersionUID = -55075093605778452L;
	private ArrayList<BonusCard> stack = new ArrayList<BonusCard>();
	private final int AMOUNT_OF_BONUSCARDS = 44; //-1 in der for schleife
	
	/**
	 * Constructor
	 */
	public BonusCardStack() {
		// TODO setup bonus cards at the beginning of a game
		// 14 von jeder Sorte
		// 2 joker
		//
		// 1: infanterie
		// 2: Kavlerier
		// 3: Kanone
		
			
		//Random generate a Stack of Bonus cards
		for(int i = 0; i <= AMOUNT_OF_BONUSCARDS-1; i++) {
			if(i <= 13) {
				this.add(new BonusCard("Infantry"));
			}else if(i <= 27) {
				this.add(new BonusCard("Cavalry"));
			}else if(i <= 41) {
				this.add(new BonusCard("Artillery"));
			}else if(i <= 43) {
				this.add(new BonusCard("WildCard"));
			}
		}
		//Mix it
		Collections.shuffle(this);
		
	}

	public BonusCard retrieveCard() {
		Collections.shuffle(this);
		remove(0);
		return get(0);
		//TODO es wird nur eine Karte gelöscht und vom Kartendeck weg genommen
		// und nicht wieder zurück gelegt
	}
}
