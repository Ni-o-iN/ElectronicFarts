package scenarios;

import gamelogic.GameLogic;

/**
 * @author julianlautenscheidt
 * @version 0.1
 * Scenario for different interactions
 */
public class Interactions {

	private static GameLogic szLogic = new GameLogic();
	
	/**
	 * @param args
	 * In this scenario several interactions are checked.
	 * 1. Stone slides against blockfield
	 * 2. Stone slides against stone and pushes against blockfield or the edge
	 * 3. Stone slides "against" stone
	 */
	public static void main(String[] args) {
		
		szLogic.addPlayer(); 
		
		szLogic.printBoard();
		
		szLogic.setBlockField(5, 5);
		szLogic.setBlockField(1, 2);
		szLogic.setBlockField(1, 3);
		szLogic.setBlockField(2, 1);
		szLogic.setBlockField(3, 1);
		szLogic.setBlockField(1, 1);
		
		szLogic.myMove("Unten2");
		szLogic.incrementMoveCounter();
		
		szLogic.myMove("Oben5");
		szLogic.incrementMoveCounter();
		
		szLogic.myMove("Oben5");
		szLogic.incrementMoveCounter();
		
		szLogic.printBoard();
		
		szLogic.myMove("Rechts3");
		szLogic.incrementMoveCounter();
		
		szLogic.myMove("Links4");
		szLogic.incrementMoveCounter();
		
		szLogic.printBoard();
		
		//szLogic.myMove("Rechts4");
		//szLogic.updateMoveCounter();
		
		szLogic.myMove("Unten3");
		szLogic.incrementMoveCounter();
		
		szLogic.printBoard();
	}
}
