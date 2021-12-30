package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import slide.Board;

/**
 * 
 * @author julianlautenscheidt, emreaydemir
 * @version 0.3 Verschiedene Tests zu der Board-Klasse
 */
public class BoardTest {

	private Board testBoard = new Board();

	@Before
	public void initFeld() {

		testBoard.setSignFromField(1, 1, 'X');
		testBoard.setSignFromField(4, 4, 'O');
	}

	char[][] testField = testBoard.getBoard();

	@Test
	public void testeBoardGröße() {

		assertEquals(6, testField.length);
		assertEquals(7, testField[0].length);

	}

	@Test
	public void testIsFieldEmpty() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, '_');
		assertTrue(testBoard.isEmpty(2, 2));
	}

	@Test
	public void testIsFieldBlocked() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, '#');
		assertTrue(testBoard.isBlocked(2, 2));
	}

	@Test
	public void testIsNextFieldAToken() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, 'X');
		testBoard.setSignFromField(2, 3, 'O');
		assertTrue(testBoard.nextFieldIsAToken(2, 2));
		assertTrue(testBoard.nextFieldIsAToken(2, 3));
	}

	@Test

	public void testBoardVisualisation() {

		char[][] leer = new char[6][7];

		for (int a = 0; a < leer.length; a++) {
			for (int i = 0; i < leer[a].length; i++) {
				assertNotEquals(leer[a][i], testBoard.getBoard()[a][i]);
			}
		}

	}

	@Test
	public void testeSteinSetzen() {

		assertEquals('X', testField[1][1]);
		assertEquals('X', testBoard.getSignFromField(1, 1));
		assertEquals('O', testBoard.getSignFromField(4, 4));
		assertEquals('_', testBoard.getSignFromField(3, 2));
	}
}
