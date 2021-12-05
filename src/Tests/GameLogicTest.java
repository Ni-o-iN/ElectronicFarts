package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import slide.Board;
import slide.GameLogic;
import slide.Player;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.2
 * Test zu der GameLogic-Klasse
 */
public class GameLogicTest {


	private GameLogic testLogic = new GameLogic(); 
	private Board testBoard = new Board();
	
	private Player spielerTest1 = new Player(1, "Spieler1");
	private Player spielerTest2 = new Player(0, null);
	private Player spielerTest3 = new Player(0, null);
	
	
	/**
	 * Test bei dem gecheckt wird, ob SetCOM richtig initialisiert ist und sich auch umstellen lässt
	 */
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
	
	/**
	 * Test bei dem gecheckt wird, ob Player ein eigenes Objekt ist
	 */
	@Test
	public void testePlayerAufEigenesObjekt() {
		
		assertNotEquals(spielerTest1, testLogic.getCurrentPlayer()); 
		
	}
	
	
	/*@Before
	public void initSpielerObjekte() {
		spielerTest2 = testLogic.getCurrentPlayer();
		testLogic.moveCounter =+1; 
	}
	*/
	/**
	 * Test bei dem gecheckt wird, ob Currentplayer durch movecounter zwischen 2 Objekten wechselt
	 */
	/*
	@Test
	public void testeCurrentPlayer() {
		
	}
	*/
	//Erst möglich wenn Movecounter initialisiert wurde
	
	/**
	 * Test bei dem gecheckt wird, ob die Bombe die Angrenzenden Felder "cleared"
	 * vorher Felder abfragen, dass sie auch wirklich befüllt sind/waren
	 */
	// TODO setFields könnten in eine BeforeMethode
	@Test
	public void testeBombMove() {
		
		//testBoard.setBlockField(3, 4);
		//assertEquals(true, testLogic.isValidBombMove(3, 3)); 
		//assertEquals(false, testLogic.isValidBombMove(3, 4)); 
		//isValidBombMove & blockfield evtl sichtbar machen?
		
		
		testBoard.setField(3, 3, 'X');
		testBoard.setField(3, 2, 'O');
		 
		assertEquals('X', testBoard.getSignFromField(3, 3));
		assertEquals('O', testBoard.getSignFromField(3, 2));
		assertEquals('_', testBoard.getSignFromField(3, 1));
		
			
		testLogic.setBomb(3, 3);
		
		//assertNotEquals('X', testBoard.getSignFromField(3, 3));
		//assertNotEquals('O', testBoard.getSignFromField(3, 2));
		
		//!! unterschiedliche Boards, daher unfunktional!! (bräuchte direkten Zugriff auf Board aus Logic)
		
	}
	
	/**
	 * Test bei dem gecheckt wird, ob Mymove durchrutschen lässt
	 */
	@Test
	public void testeMyMove() {
		
		//Problem wie oben, diesmal mit getField
		//Auf Valide Move und Stopps kann noch nicht geprüft werden	
	}
	
	/**
	 * Test bei dem gecheckt wird, ob Mymove valide ist
	 */
	@Test
	public void testeMyMoveValide() {
		
	}
	
	/**
	 * Test bei dem gecheckt wird, ob Mymove stoppt durch Block 
	 */
	@Test
	public void testeMyMoveBlock() {
		
	}
}

