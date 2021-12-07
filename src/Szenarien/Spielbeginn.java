package Szenarien;

	import slide.Board; 
	import slide.Difficulty;
	import slide.GameLogic;
	import slide.Player;
	import slide.Slide;

	public class Spielbeginn {
		
		private static GameLogic szLogic = new GameLogic(); 
		
		public static void main (String[] args) {
			
			szLogic.addPlayer(); 
			
			Player testPlayer1 = szLogic.getCurrentPlayer();
			String testString1 = testPlayer1.toString(); 
			System.out.println(testString1);	
			}
		

}
