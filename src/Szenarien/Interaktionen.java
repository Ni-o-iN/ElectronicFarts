package Szenarien;

import gamelogic.GameLogic;

/**
 * @author julianlautenscheidt
 * @version 0.1
 * Szenario für verschiedeneInteraktionen
 */
public class Interaktionen {

	private static GameLogic szLogic = new GameLogic();
	
	/**
	 * @param args
	 * In diesem Szenario werden verschiedene Interaktionen geprüft
	 * 1. Stein slidet gegen Blockfield
	 * 2. Stein slidet gegen Stein und schiebt gegen Blockfield oder Rand
	 * 3. Einwurf "gegen" Stein
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
