package Szenarien;

import gamelogic.GameLogic;

/**
 * @author julianlautenscheidt
 * @version 0.1
 * Szenario zu aktivem Sieg
 */
public class Sieg {

	private static GameLogic szLogic = new GameLogic(); 
	/**
	 * @param args
	 * @version 0.1
	 * Szenariomethode in der ein "aktiver" Sieg simuliert wird
	 * 1. Sieg vorbereiten
	 * 2. Sieg sliden
	 * 3. Abfrage auf Sieg
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
