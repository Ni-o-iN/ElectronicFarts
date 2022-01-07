package scenarios;

import gamelogic.GameLogic;

/**
 * @author julianlautenscheidt
 * @version 0.1
 * Scenario for a active vicoty
 */
public class GameWon {

	private static GameLogic szLogic = new GameLogic(); 
	/**
	 * @param args
	 * @version 0.1
	 * Scenario method in which an "active" victory is simulated
	 * 1. prepare victory
	 * 2. slide victory
	 * 3. request for victory
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
