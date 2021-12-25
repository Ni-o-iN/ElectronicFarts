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
		return getPlayer().getName();
	}

	public Player getPlayer() {
		return players[moveCounter % 2];
	}

	public void printBoard() { // rdy
		char[][] field = board.getBoard();
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
		char[][] tmp = board.getBoard();
		if (searchRow(tmp)) {
			System.out.println("Gewinner ist: " + getPlayerName());
			return false;
		} else if (searchCol(tmp)) {
			System.out.println("Gewinner ist: " + getPlayerName());
			return false;
		} else if (searchDiagonalNorthWest(tmp)) {
			System.out.println("Gewinner ist: " + getPlayerName());
			return false;
		} else if (searchDiagonalSouthWest(tmp)) {
			System.out.println("Gewinner ist: " + getPlayerName());
			return false;
		} else
			return true;
	}

	public void myMove(String input) {
		String direction = inputConversion.inputToDirection(input);
		int position = inputConversion.inputToPosition(input);
		if (direction.equals("Oben")) {
			lastFreeFieldFromTop(0, position);
		} else if (direction.equals("Rechts")) {
			lastFreeFieldFromRight(position, 6);
		} else if (direction.equals("Unten")) {
			lastFreeFieldFromBottom(5, position);
		} else if (direction.equals("Links")) {
			lastFreeFieldFromLeft(position, 0);
		}
	}
	/*
	 * Habe alle Last FreeField Methoden gefixt und getestet ( optimiert) 
	 * unnötige Zähler wurden entfernt. ggf. schauen wegen den übergabe parameter tokenSign wurde 
	 * ebenfalls entfernt
	 */
	public void lastFreeFieldFromTop(int row, int column) {
		for (int i = 0; i <= 5; i++) { // TODO Out of Bounds 
			if(board.isEmpty(i, column) && i == 5) {
				board.setSignFromField(i, column, getPlayerSign());
			} else if(board.nextFieldIsAToken(i, column)) {
				board.setSignFromField(--i, column, getPlayerSign());
				break;
			} else if (board.isBlocked(i, column)) {
				board.setSignFromField(--i, column, getPlayerSign());
				break;
			}
		} 
		//TODO ab hier Slide nachdem Token gesetzt wurde
	}

	public void lastFreeFieldFromRight(int row, int column) {
		for (int i = 6; i >= 0; i--) {	// TODO Out of Bounds 
			if(board.isEmpty(row, i) && i == 0) {
				board.setSignFromField(row, i, getPlayerSign());
			} else if(board.nextFieldIsAToken(row, i)) {
				board.setSignFromField(row, ++i, getPlayerSign());
				break;
			} else if (board.isBlocked(row, i)) {
				board.setSignFromField(row, ++i, getPlayerSign());
				break;
			}
		} 
		//TODO ab hier Slide nachdem Token gesetzt wurde
	}

	public void lastFreeFieldFromBottom(int row, int column) {
		for (int i = 5; i >= 0; i--) { // TODO Out of Bounds 
			if(board.isEmpty(i, column) && i == 0) {
				board.setSignFromField(i, column, getPlayerSign());
			} else if(board.nextFieldIsAToken(i, column)) {
				board.setSignFromField(++i, column, getPlayerSign());				
				break;
			} else if (board.isBlocked(i, column)) {
				board.setSignFromField(++i, column, getPlayerSign());
				break;
			}
		} 
		//TODO ab hier Slide nachdem Token gesetzt wurde
	}

	public void lastFreeFieldFromLeft(int row, int column) {
		
		for (int i = 0; i <= 6; i++) { 
			if(board.isEmpty(row, i) && i == 6) {
				board.setSignFromField(row, i, getPlayerSign());
			}else if(board.nextFieldIsAToken(row, i)) {
				board.setSignFromField(row, --i, getPlayerSign());
				column = i--;
				break;
			} else if (board.isBlocked(row, i)) {
				board.setSignFromField(row, --i, getPlayerSign());
				column = i--;
				break;
			}
		}
		//TODO ab hier Slide nachdem Token gesetzt wurde
		slideNextTokenFromLeft(row, column);
	}
	
	public void slideNextTokenFromLeft(int row, int column) {
		int tokenCounter = 0;
		for(int i = column; i <= 6; i++) {
			if(board.isEmpty(row, i) && !board.isBlocked(row, i)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while(tmpCounter != 0) {
					char sign = board.getSignFromField(row,(colShifter - 1)); // get sign before empty field
					board.setSignFromField(row, (colShifter -1), '_'); // deletes sign because we are moving from left to right 
					board.setSignFromField(row, colShifter, sign); // sets new sign
					tmpCounter--;
					colShifter--;
				}
				//TODO if blockiert
		}else {
			tokenCounter++;
			System.out.println(tokenCounter);
			}}
	}

	public void slideNextTokenFromTop(int row, int column) {
//		int test = row;
//		if(board.nextFieldIsAToken(++test, column)) {
//			board.setSignFromField(row, column, getPlayerSign());
//		}
//		int space = 0;
//		int test2 = column;
//		int numberOfTokens = 1;
//		for(int i = 0; i < 6-numberOfTokens; i++) {
//			if(board.nextFieldIsAToken(row, ++test2)) {
//				numberOfTokens++;
//			} else if(board.isEmpty(row, ++test2)) {
//				space++;
//				while(board.isEmpty(row, test2) && test2 < 5) {
//					test2++;
//					space++;
//				}
//				while(numberOfTokens != 0) {
//					char currentToken = board.getSignFromField(row, test2);
//					lastFreeFieldFromTop(row, test2, currentToken);
//					test2--;
//					space--;
//				}
//			} else if(board.isBlocked(row, ++test2)) {
//				board.setSignFromField(row, column, getPlayerSign());
//				break;
//			}
//		}
	}
	
	public void slideNextTokenFromRight(int row, int column) {
//		int test = column;
//		if(board.nextFieldIsAToken(row, --test)) {
//			board.setSignFromField(row, column, getPlayerSign());
//		}
	}
	
	public void slideNextTokenFromBottom(int row, int column) {
//		int test = row;
//		if(board.nextFieldIsAToken(--test, column)) {
//			board.setSignFromField(row, column, getPlayerSign());
//		}
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
		char[][] checkField = board.getBoard();
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
		char[][] checkField = board.getBoard();
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
		if (getPlayerName().equals("Spieler1"))
			return true;
		else {
			return false;
		}
	}

	public boolean boardIsFull(char[][] tmp) {
		boolean boardIsFull = false;
		for (int z = 0; z < tmp.length; z++) {
			for (int s = 0; s < tmp[z].length; s++) {
				if (tmp[z][s] == '_') {
					boardIsFull =  false;
				} else
					boardIsFull = true;
			}
		}
		return boardIsFull;
	}

}
