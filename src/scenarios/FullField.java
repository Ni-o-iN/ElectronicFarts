package scenarios;

import gamelogic.GameLogic;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.1
 * Scenario for FullField
 */
public class FullField {
	
	private static GameLogic szLogic = new GameLogic();

	/**
	 * 
	 * @param args
	 * Scenario where it is checked if a full field ends the game
	 * 1. fill field completely by block field & throw-in loops (make sure not 4 in a row)
	 * 2. check full field / win
	 */
	public static void main(String[] args) {
		
		szLogic.addPlayer(); 
		
		for(int j = 1; j<=6; j++) {
			szLogic.setBlockField(1, j);
		}
		
		
		for(int i = 1; i<=5; i++) {
			szLogic.myMove("Unten1");
			szLogic.incrementMoveCounter();
		}
		
		for(int i2 = 1; i2<=5; i2++) {
			szLogic.myMove("Unten2");
			szLogic.incrementMoveCounter();
		}
		
		for(int i3 = 1; i3<=5; i3++) {
			szLogic.myMove("Unten3");
			szLogic.incrementMoveCounter();
		}
		
		for(int i4 = 1; i4<=5; i4++) {
			szLogic.myMove("Unten4");
			szLogic.incrementMoveCounter();
		}
		
		for(int i5 = 1; i5<=5; i5++) {
			szLogic.myMove("Unten5");
			szLogic.incrementMoveCounter();
		}
		
		for(int i6 = 1; i6<=5; i6++) {
			szLogic.myMove("Unten6");
			szLogic.incrementMoveCounter();
		}
		
		for(int i7 = 1; i7<=6; i7++) {
			szLogic.myMove("Unten7");
			szLogic.incrementMoveCounter();
		}
		
		szLogic.printBoard();
		
		//szLogic.boardIsFull(szLogic.) //there should be a getBoard or something similar
	}
}
