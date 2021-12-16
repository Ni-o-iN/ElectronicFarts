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

	public void setCOM() { // rdy
		COM = true;
	}

	public boolean getCOM() { // rdy
		return COM;
	}

	public Player getCurrentPlayer() { // rdy
		return currentPlayer;
	}

	public void setCurrentPlayer() { // TODO
		if (moveCounter % 2 == 1) {
			currentPlayer = players[0];
		} else
			currentPlayer = players[1];
	}

	public void addPlayer() { // rdy
		players[0] = new Player(1, "Spieler1");
		if (getCOM())
			players[1] = new Player(2, "COM");
		else
			players[1] = new Player(2, "Spieler2");
	}

	public char getPlayerSign() { // rdy
		if (moveCounter % 2 == 0) {
			return 'O';
		} else
			return 'X';
	}

	// TODO Methode damit die Steine durchsliden
	/**
	 * Throws stone until it hits first unempty field (haven't slided yet)
	 * @param row
	 * @param col
	 * @return
	 */
	private int connectToRowLR(int row, int col) {
		int touch = 0;
		for (int i = 0; i < col; i++) {
			if (!board.isEmpty(row, i)) { 
				touch = i - 1;
				board.setSignFromField(row, touch, getPlayerSign());
				break;
			}
		}
		return touch;
	}
	/**
	 *Help Method to slide stone one position forward 
	 * @param row
	 * @param firstSign
	 * @param lastSign
	 */
	private void slideLeftToRight(int row,int firstSign, int lastSign) {
		while(lastSign >= firstSign && lastSign < 6) {
			char ch = board.getSignFromField(row, lastSign );
			board.setSignFromField(row, lastSign + 1, ch);
			lastSign--;
		}
	}
	
	public void tokenSlideFromLeftToRight(int row, int col) {
			int firstSign = connectToRowLR(row, col);
			int lastSign = firstSign, counter = firstSign;
			int empty = 0;
			for (int i = counter; i < col; i++) {
				if(board.isEmpty(row, i)) {
					slideLeftToRight(row, firstSign, lastSign);
					lastSign++;
					firstSign++;
				}
				else if(!board.isBlocked(row, i) && !board.isEmpty(row, i)) {
					lastSign = i;
				}
				else
					break;
			}
	}

	public void myMove(String direction, int position) { // TODO
		int[] coordinates = getCoordinates(direction, position);
		if (direction == "left" && isValidMove(coordinates[0], coordinates[1])) {
			tokenSlideFromLeftToRight(coordinates[0],coordinates[1]);
		}

		if (direction == "right" && isValidMove(coordinates[0], coordinates[1])) {

		}

		if (direction == "top" && isValidMove(coordinates[0], coordinates[1])) {

		}

		if (direction == "bottom" && isValidMove(coordinates[0], coordinates[1])) {

		}
	}

	public int[] getCoordinates(String direction, int position) {
		int row, col;
		if (direction.equals("links")) {
			col = 0;
			row = position;
		} else if (direction.equals("rechts")) {
			col = 6;
			row = position;
		} else if (direction.equals("oben")) {
			col = position;
			row = 0;
		} else {
			col = position;
			row = 5;
		}
		int[] coordinates = new int[] { row, col };
		return coordinates;
	}

	private boolean isValidMove(int row, int col) { // TODO

		if (board.getSignFromField(row, col) == '_')
			return true;
		else
			return false;
	}

	private boolean isValidBombMove(int row, int column) { // rdy
		char[][] checkField = board.getField();
		if (checkField[row][column] == '#' || currentPlayer.getPlayerBombStatus() == false) {
			return false;
		} else
			return true;
	}

	public void setBomb(int row, int column) { // ready
		if (isValidBombMove(row, column)) {
			board.setSignFromField(row, column, board.getBlock());
			blast(row, column);
			currentPlayer.setPlayerBombStatusFalse();
		}
	}

	private void blast(int row, int column) { // rdy
		board.setSignFromField(row, column, '_'); // deletes field where bomb got placed
		board.setSignFromField(row + 1, column, '_'); // deletes field below bomb
		board.setSignFromField(row - 1, column, '_'); // deletes field above bomb
		board.setSignFromField(row, column + 1, '_'); // deletes field right next to bomb
		board.setSignFromField(row, column - 1, '_'); // deletes field left next to bomb
	}

	// this method will be called every move TODO method to display the winner
	public boolean isRunning() {
		char[][] tmp = board.getField();
		if (searchRow(tmp)) {
			System.out.println("Gewinner ist: " + whoWon());
			return false;
		} else if (searchCol(tmp)) {
			System.out.println("Gewinner ist: " + whoWon());
			return false;
		} else if (searchDiagonalNorthWest(tmp)) {
			System.out.println("Gewinner ist: " + whoWon());
			return false;
		} else if (searchDiagonalSouthWest(tmp)) {
			System.out.println("Gewinner ist: " + whoWon());
			return false;
		} else
			return true;
	}

	public void printBoard() { // rdy
		char[][] field = board.getField();
		System.out.printf("  ");
		for (int i = 0; i < field[0].length; i++) { // print out column numbers
			System.out.print((i + 1) + " ");
		}

		System.out.println(); // new line after column numbers

		for (int i = 0; i < field.length; i++) {
			System.out.printf("%d ", i + 1);
			for (int j = 0; j < field[i].length; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	public void setBlockField(int row, int column) { // rdy FIXME set from public to private
		board.setSignFromField(row -1, column -1, board.getBlock());
	}

	public boolean searchRow(char[][] tmp) { // (rdy) -> Test

		// check every row from left to right
		int countHit = 0;
		for (int row = 0; row < tmp.length; row++) {
			if (countHit >= 4)
				return true;
			countHit = 0;
			for (int col = 0; col < tmp[row].length; col++) {
				if (countHit >= 4)
					return true;
				else if (tmp[row][col] == getPlayerSign()) {
					countHit++;
				} else
					countHit = 0;
			}
		}
		return false;
	}

	public boolean searchCol(char[][] tmp) { // (rdy) -> Test

		// check every column from top to bottom
		int countHit = 0;
		for (int col = 0; col < tmp[0].length; col++) {
			if (countHit >= 4)
				return true;
			countHit = 0;
			for (int row = 0; row < tmp.length; row++) {
				if (countHit >= 4)
					return true;
				else if (tmp[row][col] == getPlayerSign()) {
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
	public boolean searchDiagonalNorthWest(char[][] tmp) { // North-west ---> south-east (rdy) -> Testen
		int countHit = 0;
		int row = 0, col = 0;

		while (row < 2 && col < 3) {
			if (countHit >= 4)
				return true;
			if (col > 3) {
				row++;
				col = 0;
			}
			if (tmp[row][col] == getPlayerSign())
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
	public boolean searchDiagonalSouthWest(char[][] tmp) { // South-west ---> North-east (rdy) --> Testen
		int countHit = 0;
		int row = 5, col = 0;

		while (row > 3 && col < 3) {
			if (countHit >= 4)
				return true;
			if (col > 3) {
				row--;
				col = 0;
			}
			if (tmp[row][col] == getPlayerSign())
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

	public boolean whoWon() {
		if (currentPlayer.getName() == "Spieler1")
			return true;
		else {
			return false;
		}
	}

}
