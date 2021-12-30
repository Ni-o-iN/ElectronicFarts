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
	private char[][] board = new char[ROW][COLUMN];

	public Board() {
		for (int z = 0; z < board.length; z++) {
			for (int s = 0; s < board[z].length; s++) {
				board[z][s] = '_';
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

	public char getBomb() {
		return this.bomb;
	}

	public char getBlock() {
		return this.block;
	}

	public char[][] getBoard() {
		return this.board;
	}

	public void setSignFromField(int row, int column, char sign) {
		this.board[row][column] = sign;
	}

	public char getSignFromField(int row, int column) {
		return board[row][column];
	}
}
