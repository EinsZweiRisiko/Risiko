package domain;
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

/*
 * fail("Not yet implemented")
 * assertTrue(message, boolean)
 * assertFalse(message, boolean)
 * assertEquals(message, expected, actual)
 * assertEquals(message, expected, actual, delta)
 * assertNull(message, object)
 * assertNotNull(message, object)
 * assertSame(message, expected, actual)
 * 		check if both variables refer to the same object
 * assertNotSame(message, expected, actual)
 */

/*
 * @Test
 * @Test(expected=IllegalArgumentException.class)
 * @Test(timeout=100)
 * public void Test()
 * 
 * @BeforeClass
 * public static void setUpBeforeClass() throws Exception
 * 
 * @AfterClass
 * public static void tearDownAfterClass() throws Exception
 * 
 * @Before
 * public void setUp() throws Exception
 * 
 * @After
 * public void tearDown() throws Exception
 */