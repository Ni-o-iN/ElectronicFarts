package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slide.Player;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.2
 * Tests für die Player Klasse
 *
 */
public class PlayerTest {

	private Player sp1;
	private Player sp2; 
	
	@Before 
	public void Spieler(){
		sp1 = new Player(0, "Spieler1"); 
		sp2 = new Player(1, "Spieler2");
	}
	
	/**
	 * Test bei dem die IDs und Namen auf Richtigkeit gecheckt werden
	 */
	@Test
	public void testZweiSpieler() {
		
		assertEquals("Spieler1", sp1.getName()); 
		assertEquals("Spieler2", sp2.getName());
		assertEquals(0, sp1.getId());
		assertEquals(1, sp2.getId());
		
	}
	
	/**
	 * Test bei dem Bombemstatus auf Richtigkeit gecheckt wird
	 */
	@Test
	public void testeBombenStatus() {
		
		assertEquals(true, sp1.getPlayerBombStatus());
		
		sp2.setPlayerBombStatusFalse();
		assertEquals(false, sp2.getPlayerBombStatus());
	}
}
