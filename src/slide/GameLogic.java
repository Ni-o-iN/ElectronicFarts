package slide;

/**
 * 
 * @author Team
 * @version 0.3
 *
 */

public class GameLogic {

	private Player currentPlayer;
	private int moveCounter = 1;
	private int roundCounter = 1;
	private Player[] players = new Player[2];
	private Board board = new Board();
	private boolean COM = false;

	public void setCOM() {
		COM = true;
	}

	public boolean getCOM() {
		return COM;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer() {
		if (moveCounter % 2 == 1) {
			currentPlayer = players[0];
		} else
			currentPlayer = players[1];
	}

	public void addPlayer() {
		players[0] = new Player(1, "Spieler1");
		if (getCOM())
			players[1] = new Player(2, "COM");
		else
			players[1] = new Player(2, "Spieler2");
	}

	public char setPlayerSign() {
		if (moveCounter % 2 == 0) {
			return 'O';
		} else
			return 'X';
	}

	public void myMove(String direction, int position) {
		if (direction == "left") {
			if (board.getSignFromField(position, 1) == '_') { // can you even slide the token in this row
				int i = 1;
				while (board.getSignFromField(position, i + 1) == '_') { // can the token slide one field further
					i++;
				}
				board.setField(position, i, setPlayerSign());
			} else {
				isValidMove();
			}
		}

		if (direction == "right") {
			if (board.getSignFromField(position, 7) == '_') { // can you even slide the token in this row
				int i = 7;
				while (board.getSignFromField(position, i + 1) == '_') { // can the token slide one field further
					i++;
				}
				board.setField(position, i, setPlayerSign());
			} else {
				isValidMove();
			}
		}

		if (direction == "top") {
			if (board.getSignFromField(1, position) == '_') { // can you even slide the token in this column
				int i = 1;
				while (board.getSignFromField(i + 1, position) == '_') { // can the token slide one field further
					i--;
				}
				board.setField(i, position, setPlayerSign());
			} else {
				isValidMove();
			}
		}

		if (direction == "bottom") {
			if (board.getSignFromField(6, position) == '_') { // can you even slide the token in this column
				int i = 6;
				while (board.getSignFromField(i + 1, position) == '_') { // can the token slide one field further
					i--;
				}
				board.setField(i, position, setPlayerSign());
			} else {
				isValidMove();
			}
		}
	}

	private boolean isValidBombMove(int row, int column) {
		char[][] checkField = board.getField();
		if (checkField[row][column] == '#' || currentPlayer.getPlayerBombStatus() == false) {
			return false;
		} else
			return true;
	}

	public void setBomb(int row, int column) {
		if (isValidBombMove(row, column)) {
			board.setField(row, column, board.getBlock());
			blast(row, column);
			currentPlayer.setPlayerBombStatusFalse();
		}
	}

	private void blast(int row, int column) {
		board.setField(row, column, '_'); // deletes field where bomb got placed
		board.setField(row + 1, column, '_'); // deletes field below bomb
		board.setField(row - 1, column, '_'); // deletes field above bomb
		board.setField(row, column + 1, '_'); // deletes field right next to bomb
		board.setField(row, column - 1, '_'); // deletes field left next to bomb
	}
// this method will be called every run TODO method to display the winner
	public boolean isRunning() {
		if(whoWon())
		return false;
		else
			return true;
	}
//TODO
	private boolean isValidMove() {
		return false;
	}

	public void printBoard() {
		char[][] field = board.getField();

		for (int i = 0; i < field[0].length; i++) {
			System.out.print((i + 1) + " ");
		}

		System.out.println();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	private void setBlockField(int row, int column) {
		board.setField(row, column, board.getBlock());
	}

	public boolean searchRaw(char[][] tmp) {

//		check every row left to right
		int countHit = 0;
		for (int row = 0; row < tmp.length; row++) {
			if (countHit >= 4)
				return true;
			countHit = 0;
			for (int col = 0; col < tmp[row].length; col++) {
				if (countHit >= 4)
					return true;
				else if (tmp[row][col] == setPlayerSign()) {
					countHit++;
				} else
					countHit = 0;
			}
		}
		return false;
	}

	public boolean searchCol(char[][] tmp) {

//		check every column from above to below
		int countHit = 0;
		for (int col = 0; col < tmp[0].length; col++) {
			if (countHit >= 4)
				return true;
			countHit = 0;
			for (int row = 0; row < tmp.length; row++) {
				if (countHit >= 4)
					return true;
				else if (tmp[row][col] == setPlayerSign()) {
					countHit++;
				} else
					countHit = 0;
			}
		}
		return false;
	}

	/**
	 * for detecting if game is won. While loop row <= 2 please mind the array index
	 * 2 = (3)
	 * 
	 * @param tmp
	 * @return
	 */
	public boolean searchDiagonalNorthWest(char[][] tmp) { // North-west ---> south-east
		int countHit = 0;
		int row = 0, col = 0;

		while (row < 2 && col < 3) {
			if (countHit >= 4)
				return true;
			if (col > 3) {
				row++;
				col = 0;
			}
			if (tmp[row][col] == setPlayerSign())
				countHit++;
			else
				countHit = 0;
			col++;
		}
		if (countHit >= 4)
			return true;
		else
			return false;

	}
// TODO check if diagonal works
	/**
	 * for detecting if game is won. While loop row >= please mind the array index 5
	 * = (6)
	 * 
	 * @param tmp
	 * @return
	 */
	public boolean searchDiagonalSouthWest(char[][] tmp) {
		int countHit = 0;
		int row = 5, col = 0;

		while (row > 3 && col < 3) {
			if (countHit >= 4)
				return true;
			if (col > 3) {
				row--;
				col = 0;
			}
			if (tmp[row][col] == setPlayerSign())
				countHit++;
			else
				countHit = 0;
			col++;
		}
		if (countHit >= 4)
			return true;
		else
			return false;

	}
	// TODO richtiger Spieler
	public boolean whoWon() {
		char[][] tmp = board.getField();
		if (searchRaw(tmp))
			return true;
		else if (searchCol(tmp))
			return true;
		else if(searchDiagonalNorthWest(tmp))
			return true;
		else if(searchDiagonalSouthWest(tmp))
			return true;
		else
			return false;
	}

}
