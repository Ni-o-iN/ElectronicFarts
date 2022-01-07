package scenarios;

import gamelogic.GameLogic;
/**
 * Scenario for Bomb
 * @author julianlautenscheidt
 * @version 0.2
 */
public class Bomb {

	private static GameLogic szLogic = new GameLogic(); 
	
	/**
	 * Scenario in which the bomb is simulated. 
	 * 1. set some block fields (important here is only (2,2), then throw in some stones. 
	 * 2. stones should be on (2,2) (2,1) (3,2) & (3,1).
	 * 3rd bomb on (2,2). Block fields should be unchanged, as well as stone on (3,1). 
	 * @param args
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
		szLogic.incrementMoveCounter();
		
		szLogic.myMove("Unten2");
		szLogic.incrementMoveCounter();
		
		szLogic.myMove("Unten2");
		szLogic.incrementMoveCounter();
		
		szLogic.myMove("Unten1");
		szLogic.incrementMoveCounter();
		
		szLogic.printBoard();
		
		szLogic.setBomb(2, 2);
	
		szLogic.printBoard();
		
		}
	
}
