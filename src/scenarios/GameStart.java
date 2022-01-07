package scenarios;

	import gamelogic.GameLogic;

	/**
	 * @author julianlautenscheidt
	 * @version 0.3
	 * Scenario for the Beginning of the Game
	 */
	public class GameStart {
		
		private static GameLogic szLogic = new GameLogic(); 
		/**
		 * @param args
		 * Scenario method in which the start of the game is simulated with 4 simple moves
		 * 1. show empty board
		 * 2. Set 6 block fields
		 * 3. 4 simple moves without interactions
		 */
		public static void main (String[] args) {
			
			szLogic.addPlayer(); 
			
			szLogic.printBoard();		
			
			szLogic.setBlockField(1, 1);
			szLogic.setBlockField(1, 2);
			szLogic.setBlockField(1, 3);
			szLogic.setBlockField(3, 1);
			szLogic.setBlockField(3, 2);
			szLogic.setBlockField(3, 3);
			
			szLogic.printBoard();
			
			szLogic.myMove("Oben4");
			szLogic.incrementMoveCounter(); //Should it remain like that or should it be changed to myMove?
			
			szLogic.myMove("Unten6");
			szLogic.incrementMoveCounter();
			
			szLogic.myMove("Rechts4");
			szLogic.incrementMoveCounter();
			
			szLogic.myMove("Links5");
			szLogic.incrementMoveCounter();
			
			szLogic.printBoard();
			}
		

}
