package slide;
/**
 * 
 * @author Team
 *@version 0.1
 *This class will handle every input and output of the game 
 */
public class Slide {

	public static void main(String[] args) {
		
		Board board = new Board();
		char[][] tmp = board.getField();
		for (int s = 0; s < tmp[0].length; s++) {
			System.out.print((s + 1) + " ");
		}

		System.out.println();

		for (int z = 0; z < tmp.length; z++) {
			for (int s = 0; s < tmp[z].length; s++) {
				System.out.print(tmp[z][s] + " ");
			}
			System.out.println();
		}

		System.out.println();
		int i = 0;
		while(i < 3) {
			System.out.println("i");
			i++;
		}
			
	}

}
