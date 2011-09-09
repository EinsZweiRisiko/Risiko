/**
 * 
 */
package server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Jannes
 *
 */
public class BonusTrackerTest {

	@Test
	public void testBonusAmounts() {
		BonusTracker bonus = new BonusTracker();
		
		assertEquals(4, bonus.getNextBonus());
		assertEquals(6, bonus.getNextBonus());
		assertEquals(8, bonus.getNextBonus());
		assertEquals(10, bonus.getNextBonus());
		assertEquals(12, bonus.getNextBonus());
		assertEquals(15, bonus.getNextBonus());
		assertEquals(20, bonus.getNextBonus());
		assertEquals(25, bonus.getNextBonus());
		assertEquals(30, bonus.getNextBonus());
	}

}
