package Szenarien;

import slide.GameLogic;

/**
 * 
 * @author julianlautenscheidt
 * @version 0.1
 * Szenario für ein volles Feld
 */
public class FeldVoll {
	
	private static GameLogic szLogic = new GameLogic();
	
	/**
	 * 
	 * @param args
	 * Szenario bei dem geprüft wird, ob ein volles Feld das Spiel beendet
	 * 1. Feld durch Blockfelder- & Einwurfschleifen komplett füllen (achten, dass nicht 4 in einer Reihe)
	 * 2. volles Feld / Sieg abfragen
	 */
	public static void main(String[] args) {
		
		szLogic.addPlayer(); 
		
		for(int j = 1; j<=6; j++) {
			szLogic.setBlockField(1, j);
		}
		
		
		for(int i = 1; i<=5; i++) {
			szLogic.myMove("Unten1");
			szLogic.updateMoveCounter();
		}
		
		for(int i2 = 1; i2<=5; i2++) {
			szLogic.myMove("Unten2");
			szLogic.updateMoveCounter();
		}
		
		for(int i3 = 1; i3<=5; i3++) {
			szLogic.myMove("Unten3");
			szLogic.updateMoveCounter();
		}
		
		for(int i4 = 1; i4<=5; i4++) {
			szLogic.myMove("Unten4");
			szLogic.updateMoveCounter();
		}
		
		for(int i5 = 1; i5<=5; i5++) {
			szLogic.myMove("Unten5");
			szLogic.updateMoveCounter();
		}
		
		for(int i6 = 1; i6<=5; i6++) {
			szLogic.myMove("Unten6");
			szLogic.updateMoveCounter();
		}
		
		for(int i7 = 1; i7<=6; i7++) {
			szLogic.myMove("Unten7");
			szLogic.updateMoveCounter();
		}
		
		szLogic.printBoard();
		
		//szLogic.boardIsFull(szLogic.) //müsste ein getBoard oder sowas geben
	}
}
