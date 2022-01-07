package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slide.Player;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.2 Tests for the Player-class
 *
 */
public class PlayerTest {

	private Player sp1;
	private Player sp2;
	/**
	 * *Before every test-method-run the player1 object and player2 object is created/initialized.*
	 */
	@Before
	public void Spieler() {
		sp1 = new Player(0, "Spieler1");
		sp2 = new Player(1, "Spieler2");
	}
	/**
	 * *Tests if player1 and player2 got the required name and ID*
	 */
	@Test
	public void testZweiSpieler() {

		assertEquals("Spieler1", sp1.getName());
		assertEquals("Spieler2", sp2.getName());
		assertEquals(0, sp1.getId());
		assertEquals(1, sp2.getId());

	}
	/**
	 * *If player1 is able to set a bomb, player2 shouldnt be able to place a bomb, checks if the method getPlayerBombStatus works*
	 */
	@Test
	public void testeBombenStatus() {

		assertEquals(true, sp1.getPlayerBombStatus());

		sp2.setPlayerBombStatus(false);
		assertEquals(false, sp2.getPlayerBombStatus());
	}
}
