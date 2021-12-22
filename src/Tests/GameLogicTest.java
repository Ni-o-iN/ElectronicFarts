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
 * @author julianlautenscheidt
 * @version 0.2 Test zu der GameLogic-Klasse
 */
public class GameLogicTest {

	private GameLogic testLogic = new GameLogic();
	private Board testBoard = new Board();
	private Player spielerTest1 = new Player(1, "Spieler1");
	private Scanner input = new Scanner(System.in);
	private Player spielerTest2 = new Player(0, null);
	private Player spielerTest3 = new Player(0, null);
	private Board expectedBoard = new Board();
	private Slide gameSlide = new Slide();
	private InputConversion userDirection = new InputConversion();

	/**
	 * Test bei dem gecheckt wird, ob SetCOM richtig initialisiert ist und sich auch
	 * umstellen lässt
	 */
	@Test
	public void testeSetCOM() {

		assertFalse(testLogic.getCOM());
		testLogic.setCOM();
		assertTrue(testLogic.getCOM());

	}

	@Before
	public void testInitAddPlayer() {

	}

	/**
	 * Test bei dem gecheckt wird, ob Player ein eigenes Objekt ist
	 */
	@Test
	public void testePlayerAufEigenesObjekt() {

		// assertNotEquals(spielerTest1, testLogic.addPlayer());

	}

	/*
	 * @Before public void initSpielerObjekte() { spielerTest2 =
	 * testLogic.getCurrentPlayer(); testLogic.moveCounter =+1; }
	 */
	/**
	 * Test bei dem gecheckt wird, ob Currentplayer durch movecounter zwischen 2
	 * Objekten wechselt
	 */
	/*
	 * @Test public void testeCurrentPlayer() {
	 * 
	 * }
	 */
	// Erst möglich wenn Movecounter initialisiert wurde

	/**
	 * Test bei dem gecheckt wird, ob die Bombe die Angrenzenden Felder "cleared"
	 * vorher Felder abfragen, dass sie auch wirklich befüllt sind/waren
	 */
	// TODO setFields könnten in eine BeforeMethode
	@Test
	public void testeBombMove() {

		expectedBoard.getField();

		testBoard.getField();
		testBoard.setSignFromField(2, 5, 'X');
		testBoard.getBomb();

		// Print of Expected Board with expected Slide
		System.out.println("Expected Slideboard");
		char[][] fieldExp = expectedBoard.getField();
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

		System.out.println();

		// Print of Actual Board
		printFieldForTests();

		// !! unterschiedliche Boards, daher unfunktional!! (bräuchte direkten Zugriff
		// auf Board aus Logic)

	}

	/**
	 * Test bei dem gecheckt wird, ob Mymove durchrutschen lässt
	 */
	@Test
	public void testeMyMove() {

		// Problem wie oben, diesmal mit getField
		// Auf Valide Move und Stopps kann noch nicht geprüft werden
	}

	/**
	 * Test bei dem gecheckt wird, ob Mymove valide ist
	 */
	@Test
	public void testeMyMoveValide() {

	}


	/**
	 * Uncompleted Tests if the bomb blasts the correct way it should. Problem:
	 * NullPointerException
	 */
	@Test
	public void testTheBlastFromTheBomb() {
		testBoard.getField();
		testBoard.setSignFromField(2, 3, 'X');
		testBoard.setSignFromField(2, 5, 'X');
		testBoard.setSignFromField(1, 4, 'X');
		testBoard.setSignFromField(3, 4, 'X');
		testBoard.setSignFromField(2, 4, 'X');

	}

	/**
	 * Uncomplete testmethod. Still in progress Tests the throw from the left side
	 * and the slide function.
	 * 
	 * @Author emreaydemir
	 * @version 0.1
	 */
	@Test
	public void testIfSlideFromLeftWorks() {

		// Expected Char at Board Row 3 Column 7 caused by slide from Row 3 Column 1

		expectedBoard.getField();
		expectedBoard.setSignFromField(2, 6, 'X');

		// Actual testing of slide function.
		testBoard.getField();
		testBoard.setSignFromField(2, 0, 'X');

		// Checks if slide function works.
		// assertArrayEquals(expectedBoard.getField(), testBoard.getField());

	}

	/**
	 * Test for isBoardFull, if Board is full it should return True, else false.
	 */
	@Test
	public void testIsBoardFull() {

		testBoard.getField();
		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 7; c++) {
				testBoard.setSignFromField(r, c, 'X');

			}
		}
		testLogic.boardIsFull(testBoard.getField());
		assertTrue(testLogic.boardIsFull(testBoard.getField()));

	}

	public void printFieldForTests() {
		// Print of Expected Board with expected Slide
		System.out.println("Board");
		char[][] fieldExp = testBoard.getField();
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
