package Szenarien;

	import gamelogic.GameLogic;

	/**
	 * @author julianlautenscheidt
	 * @version 0.3
	 * Szenario zu Spielbeginn
	 */
	public class Spielbeginn {
		
		private static GameLogic szLogic = new GameLogic(); 
		/**
		 * @param args
		 * Szenariomethode in der der Spielbeginn mit 4 einfachen Zügen simuliert wird
		 * 1. Leeres Board anzeigen
		 * 2. 6 Blockfelder setzen
		 * 3. 4 einfache Züge ohne Interaktionen
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
			szLogic.incrementMoveCounter(); //Soll so bleiben oder change in myMove?
			
			szLogic.myMove("Unten6");
			szLogic.incrementMoveCounter();
			
			szLogic.myMove("Rechts4");
			szLogic.incrementMoveCounter();
			
			szLogic.myMove("Links5");
			szLogic.incrementMoveCounter();
			
			szLogic.printBoard();
			}
		

}
