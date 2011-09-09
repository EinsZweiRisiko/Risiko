package valueobjects;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import valueobjects.Player;


public class PlayerTest {
	
	private Player player;

	@Before
	public void setUpBeforeClass() {
		player = new Player("Peter");
	}
	
	@Test
	public void testToString() {
		assertEquals("Name", "Peter", player.toString());
	}

}