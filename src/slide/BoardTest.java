package slide;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import slide.Board;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.2
 * Verschiedene Tests zu der Board-Klasse
 */
public class BoardTest {

	private Board board = new Board(); 
	 	
	@Before
	public void initFeld() {
		
		board.setField(1, 1, 'X');	
		board.setField(4, 4, 'O');
	}
	
	char[][] testField = board.getField();
	
	/**
	 * Test bei dem gecheckt wird, ob das Board die richtige Größe (6x7) hat
	 */
	@Test
	public void testeBoardGröße() {
		
		assertEquals(6, testField.length); 
		assertEquals(7, testField[0].length); 
	
	}
	
	/**
	 * Test bei dem gecheckt wird, ob vorher initialisierte Felder das Richtige Symbol anzeigen
	 * einmal manuel aufegrufen, danach mit der getSignFromField Methode
	 */
	@Test
	public void testeSteinSetzen() {
		
		assertEquals('X', testField[1][1]);
		assertEquals('X', board.getSignFromField(1, 1));
		assertEquals('O', board.getSignFromField(4, 4));
		assertEquals('_', board.getSignFromField(3, 2));
	}
}
