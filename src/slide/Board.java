package slide;

/**
 * 
 * @author Team
 * @version 0.4 This class generates the Gamefield
 */
public class Board {
	private char[] signs = new char[] { 'X', 'O' };
	private char bomb = 'B';
	private char block = '#';
	final int ROW = 6;
	final int COLUMN = 7;
	private char[][] field = new char[ROW][COLUMN];

	public Board() {
		for (int z = 0; z < field.length; z++) {
			for (int s = 0; s < field[z].length; s++) {
				field[z][s] = '_';
			}
		}

	}

	public boolean nextFieldIsAToken(int row, int column) {
		return (getSignFromField(row, column) == 'X') || (getSignFromField(row, column) == 'O');
	}

	public boolean isBlocked(int row, int col) {
		return (getSignFromField(row, col) == '#');
	}

	public boolean isEmpty(int row, int col) {
		return (getSignFromField(row, col) == '_');
	}

	public char getSigns(int pos) {
		return signs[pos];
	}

	public char getBomb() {
		return this.bomb;
	}

	public char getBlock() {
		return this.block;
	}

	public void setField(char[][] field) {
		this.field = field;
	}

	public char[][] getField() {
		return this.field;
	}

	public void setSignFromField(int row, int column, char sign) {
		this.field[row][column] = sign;
	}

	public char getSignFromField(int row, int column) {
		return field[row][column];
	}
}
