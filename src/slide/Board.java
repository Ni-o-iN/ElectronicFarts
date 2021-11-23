package slide;

/**
 * 
 * @author Team
 * @version 0.1 This class generates the Gamefield
 */
public class Board {
	private char[] signs = new char[] {'X','O'};
	private char bomb = 'B';
	private char block = '#';
	final int ROW = 6;
	final int COLLUM = 7;
	private char[][] spielfeld = new char[ROW][COLLUM];

	Board() {
		for (int z = 0; z < spielfeld.length; z++) {
			for (int s = 0; s < spielfeld[z].length; s++) {
				spielfeld[z][s] = '_';
			}
		}
		
	}

	public char[] getSigns(int pos) {
		return signs;
	}

	public char getBomb() {
		return bomb;
	}

	public char getBlock() {
		return block;
	}

	public char[][] getSpielfeld() {
		return spielfeld;
	}

	public void setSpielfeld(int row, int collum,char sign) {
		spielfeld[row][collum] = sign;
	}
}
