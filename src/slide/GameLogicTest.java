package slide;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameLogicTest {


	private GameLogic testLogic = new GameLogic(); 
	private Board testBoard = new Board();
	
	private Player spielerTest1 = new Player(1, "Spieler1");
	private Player spielerTest2 = new Player(0, null);
	private Player spielerTest3 = new Player(0, null);
	
	

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
	public void testePlayerAufEigenesObjekt() {
		
		assertNotEquals(spielerTest1, testLogic.getCurrentPlayer()); 
		
	}
	
	/*@Before
	public void initSpielerObjekte() {
		spielerTest2 = testLogic.getCurrentPlayer();
		testLogic.moveCounter =+1; 
	}
	
	@Test
	public void testeCurrentPlayer() {
		
	}
	*/
	//Erst möglich wenn Movecounter initialisiert wurde
	
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
	
	@Test
	public void testeMyMove() {
		
		//Problem wie oben, diesmal mit getField
		//Auf Valide Move und Stopps kann noch nicht geprüft werden
		
		
		
	}
}

