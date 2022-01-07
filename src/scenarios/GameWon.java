package scenarios;

import gamelogic.GameLogic;

/**
 * Scenario for a active victory
 * @author julianlautenscheidt
 * @version 0.1
 */
public class GameWon {

	private static GameLogic szLogic = new GameLogic(); 
	/**
	 * Scenario method in which an "active" victory is simulated
	 * 1. prepare victory
	 * 2. slide victory
	 * 3. request for victory
	 * @param args
	 */
	public static void main(String[] args) {
		
		szLogic.addPlayer();
		
		szLogic.setBlockField(5, 3);
		szLogic.myMove("Rechts1");
		szLogic.myMove("Rechts1");
		szLogic.myMove("Rechts1");
		szLogic.myMove("Rechts5");
		
		szLogic.printBoard();
		
		szLogic.incrementMoveCounter();
		szLogic.myMove("Unten4");
		
		szLogic.printBoard();
		
		
		if (szLogic.whoWon() == true) {
			System.out.println("Spiel beendet");
		}
	}
}
