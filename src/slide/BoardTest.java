package slide;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import slide.Board;

public class BoardTest {

	private Board board = new Board(); 
	 	
	@Before
	public void initFeld() {
		
		board.setField(1, 1, 'X');	
		board.setField(4, 4, 'O');
	}
	
	char[][] testField = board.getField();
	
	@Test
	public void testeBoardGröße() {
		
		assertEquals(6, testField.length); 
		assertEquals(7, testField[0].length); 
	
	}
	
	@Test
	public void testeSteinSetzen() {
		
		assertEquals('X', testField[1][1]);
		assertEquals('O', board.getSignFromField(4, 4));
	}
}
