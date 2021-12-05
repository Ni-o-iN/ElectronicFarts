package slide;

import java.util.Scanner;

/**
 * 
 * @author Team
 *@version 0.1
 *This class will handle every input and output of the game 
 */
public class Slide {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		GameLogic board = new GameLogic();
		board.printBoard();
		
//		System.out.println("Wollen Sie mit COm spielen? (y/n)");
//		String enterCOM = in.next();
//		if(enterCOM.equals("y"))
//			board.setCOM();
//		System.out.println(board.getCOM());
	}

}
