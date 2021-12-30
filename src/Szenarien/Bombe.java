package Szenarien;

import slide.GameLogic;
import slide.Player;

public class Bombe {

	
	private static GameLogic szLogic = new GameLogic(); 
	
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
		
		szLogic.printBoard();
		
		szLogic.setBomb(2, 2);
	
		szLogic.printBoard();
		
		}
	
}
