package slide;

/**
 * 
 * @author Team
 * @version 0.3
 *
 */

public class GameLogic {

	private InputConversion inputConversion = new InputConversion();
	private Player currentPlayer;
	private int moveCounter = 0;
	private int roundCounter = 1;
	private Player[] players = new Player[2];
	private Board board = new Board();
	private boolean COM = false;
	private char thisToken;

	public void setCOM() { // rdy
		COM = true;
	}

	public boolean getCOM() { // rdy
		return COM;
	}

	public void addPlayer() { // rdy
		players[0] = new Player(1, "Spieler1");
		if (getCOM())
			players[1] = new Player(2, "COM");
		else
			players[1] = new Player(2, "Spieler2");
	}

	public String getPlayerName() {
		int index = (moveCounter % 2);
		String name = players[index].getName();
		return name;
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

	public void setBlockField(int row, int column) {
		board.setSignFromField(row - 1, column - 1, board.getBlock());
	}

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

	public void myMove(String input) {
		String direction = inputConversion.inputToDirection(input);
		int position = inputConversion.inputToPosition(input);

		if (direction.equals("Oben")) {
			lastFreeFieldFromTop(position, 0, getPlayerSign());
		} else if (direction.equals("Rechts")) {
			lastFreeFieldFromRight(6, position, getPlayerSign());
		} else if (direction.equals("Unten")) {
			lastFreeFieldFromBottom(position, 5, getPlayerSign());
		} else if (direction.equals("Links")) {
			lastFreeFieldFromLeft(0, position, getPlayerSign());
		}
	}

	public void lastFreeFieldFromTop(int row, int column, char tokenSign) {
		while (board.isEmpty(row, column++)) {
			column++;
		}
		if (column == 5) {
			board.setSignFromField(row, column, tokenSign);
		} else if (board.nextFieldIsAToken(row, column)) {
			slideNextTokenFromTop(row, column);
		} else if (board.isBlocked(row, column)) {
			board.setSignFromField(row, column, tokenSign);
		}
	}

	public void lastFreeFieldFromRight(int row, int column, char tokenSign) {
		while (board.isEmpty(row--, column)) {
			row--;
		}
		if (row == 0) {
			board.setSignFromField(row, column, tokenSign);
		} else if (board.nextFieldIsAToken(row--, column)) {
			slideNextTokenFromRight(row, column);
		} else if (board.isBlocked(row--, column)) {
			board.setSignFromField(row, column, tokenSign);
		}
	}

	public void lastFreeFieldFromBottom(int row, int column, char tokenSign) {
		while (board.isEmpty(row, column--)) {
			column--;
		}
		if (column == 0) {
			board.setSignFromField(row, column, tokenSign);
		} else if (board.nextFieldIsAToken(row, column--)) {
			slideNextTokenFromBottom(row, column);
		} else if (board.isBlocked(row, column--)) {
			board.setSignFromField(row, column, getPlayerSign());
		}
	}

	public void lastFreeFieldFromLeft(int row, int column, char tokenSign) {
		while (board.isEmpty(row++, column)) {
			row++;
		}
		if (row == 6) {
			board.setSignFromField(row, column, tokenSign);
		} else if (board.nextFieldIsAToken(row++, column)) {
			slideNextTokenFromLeft(row, column);
		} else if (board.isBlocked(row++, column)) {
			board.setSignFromField(row, column, tokenSign);
		}
	}

	public void slideNextTokenFromTop(int row, int column) {
		int numberOfTokens = 0;
		while(board.nextFieldIsAToken(row, column++)) {
			numberOfTokens++;
		}
		if(board.isBlocked(row, column+numberOfTokens+1)) {
			board.setSignFromField(row, column, getPlayerSign());
		} else if(board.isEmpty(row, column+numberOfTokens+1)) {
			thisToken = board.getSignFromField(row, column+numberOfTokens);
			lastFreeFieldFromTop(row, column+numberOfTokens, thisToken);
		}
	}
	
	public void slideNextTokenFromRight(int row, int column) {
		int numberOfTokens = 0;
		while(board.nextFieldIsAToken(row--, column)) {
			numberOfTokens++;
		}
		if(board.isBlocked(row-numberOfTokens-1, column)) {
			board.setSignFromField(row, column, getPlayerSign());
		} else if(board.isEmpty(row-numberOfTokens-1, column)) {
			thisToken = board.getSignFromField(row-numberOfTokens, column);
			lastFreeFieldFromRight(row-numberOfTokens, column, thisToken);
		}
	}
	
	public void slideNextTokenFromBottom(int row, int column) {
		int numberOfTokens = 0;
		while(board.nextFieldIsAToken(row, column--)) {
			numberOfTokens++;
		}
		if(board.isBlocked(row, column-numberOfTokens-1)) {
			board.setSignFromField(row, column, getPlayerSign());
		} else if(board.isEmpty(row, column-numberOfTokens-1)) {
			thisToken = board.getSignFromField(row, column-numberOfTokens);
			lastFreeFieldFromTop(row, column-numberOfTokens, thisToken);
		}
	}
	
	public void slideNextTokenFromLeft(int row, int column) {
		int numberOfTokens = 0;
		while(board.nextFieldIsAToken(row++, column)) {
			numberOfTokens++;
		}
		if(board.isBlocked(row+numberOfTokens+1, column)) {
			board.setSignFromField(row, column, getPlayerSign());
		} else if(board.isEmpty(row+numberOfTokens+1, column)) {
			thisToken = board.getSignFromField(row+numberOfTokens, column);
			lastFreeFieldFromRight(row+numberOfTokens, column, thisToken);
		}
	}
	
	

	public void updateMoveCounter() {
		moveCounter++;
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
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
//	private int connectToRowLR(int row, int col) {
//		int touch = 0;
//		for (int i = 0; i < col; i++) {
//			if (!board.isEmpty(row, i)) {
//				touch = i - 1;
//				board.setSignFromField(row, touch, getPlayerSign());
//				break;
//			}
//		}
//		return touch;
//	}

	/**
	 * Help Method to slide stone one position forward
	 * 
	 * @param row
	 * @param firstSign
	 * @param lastSign
	 */
//	private void slideLeftToRight(int row, int firstSign, int lastSign) {
//		while (lastSign >= firstSign && lastSign < 6) {
//			char ch = board.getSignFromField(row, lastSign);
//			board.setSignFromField(row, lastSign + 1, ch);
//			lastSign--;
//		}
//	}
//
//	public void tokenSlideFromLeftToRight(int row, int col) {
//		int firstSign = connectToRowLR(row, col);
//		int lastSign = firstSign, counter = firstSign;
//		int empty = 0;
//		for (int i = counter; i < col; i++) {
//			if (board.isEmpty(row, i)) {
//				slideLeftToRight(row, firstSign, lastSign);
//				lastSign++;
//				firstSign++;
//			} else if (!board.isBlocked(row, i) && !board.isEmpty(row, i)) {
//				lastSign = i;
//			} else
//				break;
//		}
//	}

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

	private boolean isValidMove(String input) {
		String direction = inputConversion.inputToDirection(input);
		int position = inputConversion.inputToPosition(input);

		// if (board.getSignFromField(row, col) == '_')
		// return true;
		// else
		return false;
	}
	
	public boolean isValidBlockMove(int row, int column) {
		row = row - 1;
		column = column - 1;
		char[][] checkField = board.getField();
		if (checkField[row][column] == '#') {
			return false;
		} else
			return true;
	}

	private boolean isValidBombMove(String inputString) {
		int[] cords = inputConversion.inputToCords(inputString);
		int row = cords[0] - 1;
		int column = cords[1] - 1;
		// row = row - 1;
		// column = column - 1;
		char[][] checkField = board.getField();
		if (checkField[row][column] == '#' || currentPlayer.getPlayerBombStatus() == false) {
			return false;
		} else {
			setBomb(row, column);
			return true;
			}
	}

	public void setBomb(int row, int column) { 
		
		board.setSignFromField(row, column, board.getBlock());
		blast(row, column);
		currentPlayer.setPlayerBombStatusFalse();
	}

	private void blast(int row, int column) { // rdy
		board.setSignFromField(row, column, '_');
		board.setSignFromField(row + 1, column, '_');
		board.setSignFromField(row - 1, column, '_');
		board.setSignFromField(row, column + 1, '_');
		board.setSignFromField(row, column - 1, '_');
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
		if (currentPlayer.getName().equals("Spieler1"))
			return true;
		else {
			return false;
		}
	}

	public boolean boardIsFull(char[][] tmp) {
		for (int z = 0; z < tmp.length; z++) {
			for (int s = 0; s < tmp[z].length; s++) {
				if (tmp[z][s] == '_') {
					return false;
				} else
					return true;
			}
		}
		return false;
	}

}
