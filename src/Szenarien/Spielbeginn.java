package Szenarien;

	import slide.Board; 
	import slide.Difficulty;
	import slide.GameLogic;
	import slide.Player;
	import slide.Slide;

	public class Spielbeginn {
		
		private static GameLogic SzLogic = new GameLogic(); 
		
		public static void main (String[] args) {
			
			SzLogic.addPlayer(); 
			
			Player testPlayer1 = SzLogic.getCurrentPlayer();
			String testString1 = testPlayer1.toString(); 
			System.out.println(testString1);	
			}
		

}
