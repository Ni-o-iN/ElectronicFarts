package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import slide.Board;

/**
 * 
 * @author julianlautenscheidt, emreaydemir
 * @version 0.3 Tests for the Board-class
 */
public class BoardTest {

	private Board testBoard = new Board();

	
	/**
	 * *Initialises board-field before every test-method-run*
	 */
	@Before
	public void initFeld() {

		testBoard.setSignFromField(1, 1, 'X');
		testBoard.setSignFromField(4, 4, 'O');
	}

	char[][] testField = testBoard.getBoard();

	/**
	 * *Tests if the board has the required width and lenght. Should be 6x7 *
	 */
	@Test
	public void testeBoardGröße() {

		assertEquals(6, testField.length);
		assertEquals(7, testField[0].length);

	}
	/**
	 * *Tests if the fieldIsEmpty-Method works, if the field is Empty, it must deliver the boolean true*
	 */
	@Test
	public void testIsFieldEmpty() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, '_');
		assertTrue(testBoard.isEmpty(2, 2));
	}
	/**
	 * *Tests if the IsFieldBlocked-Method works. If the field is Blocked, it must deliver the boolean true*
	 */
	@Test
	public void testIsFieldBlocked() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, '#');
		assertTrue(testBoard.isBlocked(2, 2));
	}
	/**
	 * *Tests if the nextFieldIsAToken-Method works. If it recognizes that the next field is a token, it must deliver the boolean true*
	 */
	@Test
	public void testIsNextFieldAToken() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, 'X');
		testBoard.setSignFromField(2, 3, 'O');
		assertTrue(testBoard.isToken(2, 2));
		assertTrue(testBoard.isToken(2, 3));
	}
	/**
	 * *Tests if the board is visualized in the correct way. Correct way: It definitively shouldnt be empty*
	 */
	@Test
	public void testBoardVisualisation() {

		char[][] leer = new char[6][7];

		for (int a = 0; a < leer.length; a++) {
			for (int i = 0; i < leer[a].length; i++) {
				assertNotEquals(leer[a][i], testBoard.getBoard()[a][i]);
			}
		}

	}
	/**
	 * *Tests if the user is able to place a stone and if it has a an effect on the Field*
	 */
	@Test
	public void testeSteinSetzen() {

		assertEquals('X', testField[1][1]);
		assertEquals('X', testBoard.getSignFromField(1, 1));
		assertEquals('O', testBoard.getSignFromField(4, 4));
		assertEquals('_', testBoard.getSignFromField(3, 2));
	}
}
