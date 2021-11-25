package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slide.Player;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.1
 * Test erstmal nur zur Probe, Spieler sind selbst angelegt und nicht vom Spiel
 * JUnit 4 genutzt
 *
 */
public class TestPlayer {

	private Player sp1;
	private Player sp2; 
	
	@Before 
	public void Spieler(){
		sp1 = new Player(0, "Spieler1"); 
		sp2 = new Player(1, "Spieler2");
	}
	

	@Test
	public void testZweiSpieler() {
		
		assertEquals("Spieler1", sp1.getName()); 
		assertEquals("Spieler2", sp2.getName());
		assertEquals(0, sp1.getId());
		assertEquals(1, sp2.getId());
		
	}
}
