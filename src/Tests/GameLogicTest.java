package Tests;

import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import slide.Board;
import slide.GameLogic;
import slide.InputConversion;
import slide.Player;
import slide.Slide;

/**
 * 
 * @author julianlautenscheidt, emre aydemir
 * @version 0.3 Test zu der GameLogic-Klasse
 */
public class GameLogicTest {

	private GameLogic testLogic = new GameLogic();
	private Board testBoard = new Board();
	private Player spielerTest1 = new Player(1, "Spieler1");
	private Scanner input = new Scanner(System.in);
	private Board expectedBoard = new Board();
	private Slide gameSlide = new Slide();
	private InputConversion userDirection = new InputConversion();

	@Test
	public void testeSetCOM() {

		assertFalse(testLogic.getCOM());
		testLogic.setCOM();
		assertTrue(testLogic.getCOM());

	}

	@Test
	public void testePlayerAufEigenesObjekt() {

		// assertNotEquals(spielerTest1, testLogic.addPlayer());

	}

	@Test
	public void testDiagonalNorthWest() {
		testBoard.getBoard();

		testBoard.setSignFromField(0, 6, 'X');
		testBoard.setSignFromField(1, 5, 'X');
		testBoard.setSignFromField(2, 4, 'X');
		testBoard.setSignFromField(3, 3, 'X');

		assertTrue(testLogic.searchDiagonalNorthWest(testBoard.getBoard()));

	}

	@Test
	public void testDiagonalSouthWest() {

		testBoard.getBoard();
		testBoard.setSignFromField(5, 6, 'O');
		testBoard.setSignFromField(4, 5, 'O');
		testBoard.setSignFromField(3, 4, 'O');
		testBoard.setSignFromField(2, 3, 'O');
		printFieldForTests();
		assertTrue(testLogic.searchDiagonalSouthWest(testBoard.getBoard()));

	}

	@Test
	public void testIsSearchRowAHit() {
		testBoard.getBoard();
		testBoard.setSignFromField(2, 2, 'O');
		testBoard.setSignFromField(2, 3, 'O');
		testBoard.setSignFromField(2, 4, 'O');
		testBoard.setSignFromField(2, 5, 'O');

		testLogic.searchRow(testBoard.getBoard());

		assertTrue(testLogic.searchRow(testBoard.getBoard()));

	}

	@Test
	public void testIsSearchColumnAHit() {
		testBoard.getBoard();

		testBoard.setSignFromField(1, 0, 'O');
		testBoard.setSignFromField(2, 0, 'O');
		testBoard.setSignFromField(3, 0, 'O');
		testBoard.setSignFromField(4, 0, 'O');

		assertTrue(testLogic.searchCol(testBoard.getBoard()));

	}

	@Test
	public void testIsBoardFull() {

		testBoard.getBoard();
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				testBoard.setSignFromField(r, c, 'X');

			}
		}
		testLogic.boardIsFull(testBoard.getBoard());
		assertTrue(testLogic.boardIsFull(testBoard.getBoard()));

	}

	public void printFieldForTests() {
		// Print of Expected Board with expected Slide
		System.out.println("Board");
		char[][] fieldExp = testBoard.getBoard();
		System.out.printf("  ");
		for (int i = 0; i < fieldExp[0].length; i++) { // print out column numbers
			System.out.print((i + 1) + " ");
		}

		System.out.println(); // new line after column numbers

		for (int i = 0; i < fieldExp.length; i++) {
			System.out.printf("%d ", i + 1);
			for (int j = 0; j < fieldExp[i].length; j++) {
				System.out.print(fieldExp[i][j] + " ");
			}
			System.out.println();
		}

	}
}
