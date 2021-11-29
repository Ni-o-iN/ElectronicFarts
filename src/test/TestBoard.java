package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slide.Board;

public class TestBoard {

	private Board board = new Board(); //Board nicht public
	
	
	@Before
	public void richteBoardEin() {
		
		board.setField(1, 1, 'X');
		char[][] testField = board.getField();
	}
	
	@Test
	public void testeBoardGröße() {
		
		assertEquals(6, testField.length); 
		assertEquals(6, testField[0].length); 
	
	}
	
	@Test
	public void testeSteinSetzen() {
		
		assertEquals('X', testField[1][1]);
	}

}
