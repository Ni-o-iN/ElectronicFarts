package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slide.Board;
import slide.GameLogic;

public class TestGameLogic {

	private GameLogic testLogic = new GameLogic(); 
	private Board testBoard = new Board();
	

	@Test
	public void testeSetCOM() {
		
		assertFalse(testLogic.getCOM()); 
		testLogic.setCOM();
		assertTrue(testLogic.getCOM()); 
		
	}

	@Before 
	public void testInitAddPlayer() {
		
		testLogic.addPlayer();
		testLogic.setCurrentPlayer();
	}
	
	@Test
	public void testePlayer() {
		
		assertEquals("Spieler1", testLogic.getCurrentPlayer()); 
		//current Player nicht angelegt, weiß nicht ob einfacher machbar als gedacht
	}
	
	@Test
	public void testeBombMove() {
		
		testBoard.setField(2, 2, '#');
		//assertEquals(true, testLogic.isValidBombMove(3, 3)); 
		//assertEquals(false, testLogic.isValidBombMove(2, 2)); 
		//nicht public - anderen Weg finden oder ändern
		
		testBoard.setField(3, 3, 'X');
		testBoard.setField(3, 2, 'O');
		testBoard.setBlockField(3, 4); //wieder private
		
		testLogic.setBomb(3, 3);
		
		//Abfrage auf Felder
		
	}
}
