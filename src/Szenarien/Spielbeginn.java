package Szenarien;

	import slide.GameLogic;


	public class Spielbeginn {
		
		private static GameLogic szLogic = new GameLogic(); 
		
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
			szLogic.updateMoveCounter(); //Soll so bleiben oder change in myMove?
			
			szLogic.myMove("Unten6");
			szLogic.updateMoveCounter();
			
			szLogic.myMove("Rechts2");
			szLogic.updateMoveCounter();
			
			szLogic.myMove("Links5");
			szLogic.updateMoveCounter();
			
			szLogic.printBoard();
			}
		

}
