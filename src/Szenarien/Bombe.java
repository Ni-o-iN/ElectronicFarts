package Szenarien;

import slide.GameLogic;
/**
 * 
 * @author julianlautenscheidt
 * @version 0.2
 * Bombenszenario
 *
 */
public class Bombe {

	private static GameLogic szLogic = new GameLogic(); 
	/**
	 * 
	 * @param args
	 * Szenario in dem die Bombe simuliert wird. 
	 * 1. Ein paar Blockfelder setzen (wichtig ist hierbei nur (2,2), dann ein paar Steine einwerfen. 
	 * 2. Steine sollten auf (2,2) (2,1) (3,2) & (3,1) liegen.
	 * 3. Bombe auf (2,2). Blockfelder sollten unverändert sein, ebenso Stein auf (3,1). 
	 */
	public static void main (String[] args) {
		
		szLogic.addPlayer(); 

		szLogic.setBlockField(1, 1);
		szLogic.setBlockField(1, 2);
		szLogic.setBlockField(1, 3);
		szLogic.setBlockField(5, 4);
		szLogic.setBlockField(5, 5);
		szLogic.setBlockField(5, 6);
		
		szLogic.printBoard();
		
		szLogic.myMove("Rechts2");
		szLogic.updateMoveCounter();
		
		szLogic.myMove("Unten2");
		szLogic.updateMoveCounter();
		
		szLogic.myMove("Unten2");
		szLogic.updateMoveCounter();
		
		szLogic.myMove("Unten1");
		szLogic.updateMoveCounter();
		
		szLogic.printBoard();
		
		szLogic.setBomb(2, 2);
	
		szLogic.printBoard();
		
		}
	
}
