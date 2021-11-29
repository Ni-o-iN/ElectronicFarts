package slide;

/**
 * 
 * @author Team
 * @version 0.4 This class generates the Gamefield
 */
public class Board {
	private char[] signs = new char[] {'X','O'};
	private char bomb = 'B';
	private char block = '#';
	final int ROW = 6;
	final int COLUMN = 7;
	private char[][] field = new char[ROW][COLUMN];

	Board() {
		for (int z = 0; z < field.length; z++) {
			for (int s = 0; s < field[z].length; s++) {
				field[z][s] = '_';
			}
		}
		
	}

	public char getSigns(int pos) {
		return signs[pos];
	}

	public char getBomb() {
		return bomb;
	}

	public char getBlock() {
		return block;
	}

	public char[][] getField() {
		return field;
	}

	public void setField(int row, int column,char sign) {
		field[row][column] = sign;
	}
	
	public char getSignfromField(int row, int column) {
		return field[row][column];
	}
}
