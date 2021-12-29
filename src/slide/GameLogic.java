package slide;

/**
 * 
 * @author Team
 * @version 0.3
 *
 */

public class GameLogic {

	private InputConversion inputConversion = new InputConversion();
	private int moveCounter = 0;
	private Player[] players = new Player[2];
	private Board board = new Board();
	private boolean COM = false;
	private char[][] checkField = board.getBoard();

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

	public Player getPlayers(int i) {
		return players[i];
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

	public boolean isValidMove(String input) {
		int row = -1, column = -1;
		String direction = inputConversion.inputToDirection(input);
		int position = inputConversion.inputToPosition(input);
		if (direction.equals("Oben")) {
			row = 0;
			column = position;
		} else if (direction.equals("Rechts")) {
			row = position;
			column = 6;
		} else if (direction.equals("Unten")) {
			row = 5;
			column = position;
		} else if (direction.equals("Links")) {
			row = position;
			column = 0;
		}
		if (board.getSignFromField(row, column) == '_')
			return true;
		else
			return false;
	}

	/*
	 * Habe alle Last FreeField Methoden gefixt und getestet ( optimiert)
	 * unnötige Zähler wurden entfernt. ggf. schauen wegen den übergabe parameter
	 * tokenSign wurde
	 * ebenfalls entfernt
	 */
	public void lastFreeFieldFromTop(int row, int column) {
		for (int i = 0; i <= 5; i++) {
			if (board.isEmpty(i, column) && i == 5) {
				board.setSignFromField(i, column, getPlayerSign());
			} else if (board.nextFieldIsAToken(i, column)) {
				board.setSignFromField(--i, column, getPlayerSign());
				row = i--;
				break;
			} else if (board.isBlocked(i, column)) {
				board.setSignFromField(--i, column, getPlayerSign());
				row = i--;
				break;
			}
		}
		slideNextTokenFromTop(row, column);
	}

	public void slideNextTokenFromTop(int row, int column) {
		int tokenCounter = 0;
		for (int i = row; i <= 5; i++) {
			if (board.isBlocked(i, column))
				break;
			else if (board.isEmpty(i, column) && !board.isBlocked(i, column)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while (tmpCounter != 0) {
					char sign = board.getSignFromField((colShifter - 1), column); // get sign before empty field
					board.setSignFromField((colShifter - 1), column, '_'); // deletes sign because we are moving from
																			// left to right
					board.setSignFromField(colShifter, column, sign); // sets new sign
					tmpCounter--;
					colShifter--;
				}
			} else {
				tokenCounter++;

			}
		}
	}

	public void lastFreeFieldFromBottom(int row, int column) {
		for (int i = 5; i >= 0; i--) {
			if (board.isEmpty(i, column) && i == 0) {
				board.setSignFromField(i, column, getPlayerSign());
			} else if (board.nextFieldIsAToken(i, column)) {
				board.setSignFromField(++i, column, getPlayerSign());
				row = i++;
				break;
			} else if (board.isBlocked(i, column)) {
				board.setSignFromField(++i, column, getPlayerSign());
				row = i++;
				break;
			}
		}
		slideNextTokenFromBottom(row, column);
	}

	public void slideNextTokenFromBottom(int row, int column) {
		int tokenCounter = 0;
		for (int i = row; i >= 0; i--) {
			if (board.isBlocked(i, column))
				break;
			else if (board.isEmpty(i, column) && !board.isBlocked(i, column)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while (tmpCounter != 0) {
					char sign = board.getSignFromField((colShifter + 1), column); // get sign before empty field
					board.setSignFromField((colShifter + 1), column, '_'); // deletes sign because we are moving from
																			// left to right
					board.setSignFromField(colShifter, column, sign); // sets new sign
					tmpCounter--;
					colShifter++;
				}
			} else {
				tokenCounter++;

			}
		}
	}

	public void lastFreeFieldFromRight(int row, int column) {
		for (int i = 6; i >= 0; i--) { // TODO Out of Bounds
			if (board.isEmpty(row, i) && i == 0) {
				board.setSignFromField(row, i, getPlayerSign());
			} else if (board.nextFieldIsAToken(row, i)) {
				board.setSignFromField(row, ++i, getPlayerSign());
				column = i++;
				break;
			} else if (board.isBlocked(row, i)) {
				if (i == 6) {
					break;
				} else {
					board.setSignFromField(row, ++i, getPlayerSign());
					column = i++;
					break;
				}
			}
		}
		slideNextTokenFromRight(row, column);
	}

	public void slideNextTokenFromRight(int row, int column) {
		int tokenCounter = 0;
		for (int i = column; i >= 0; i--) {
			if (board.isBlocked(row, i))
				break;
			else if (board.isEmpty(row, i) && !board.isBlocked(row, i)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while (tmpCounter != 0) {
					char sign = board.getSignFromField(row, (colShifter + 1)); // get sign before empty field
					board.setSignFromField(row, (colShifter + 1), '_'); // deletes sign because we are moving from left
																		// to right
					board.setSignFromField(row, colShifter, sign); // sets new sign
					tmpCounter--;
					colShifter++;
				}
			} else {
				tokenCounter++;
			}
		}
	}

	public void lastFreeFieldFromLeft(int row, int column) {

		for (int i = 0; i <= 6; i++) {
			if (board.isEmpty(row, i) && i == 6) {
				board.setSignFromField(row, i, getPlayerSign());
			} else if (board.nextFieldIsAToken(row, i)) {
				board.setSignFromField(row, --i, getPlayerSign());
				column = i--;
				break;
			} else if (board.isBlocked(row, i)) {
				if (i == 0) { // abort because entrence is blocked
					break;
				} else {
					board.setSignFromField(row, --i, getPlayerSign());
					column = i--;
					break;
				}
			}
		}
		slideNextTokenFromLeft(row, column);
	}

	public void slideNextTokenFromLeft(int row, int column) {
		int tokenCounter = 0;
		for (int i = column; i <= 6; i++) {
			if (board.isBlocked(row, i))
				break;
			else if (board.isEmpty(row, i) && !board.isBlocked(row, i)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while (tmpCounter != 0) {
					char sign = board.getSignFromField(row, (colShifter - 1)); // get sign before empty field
					board.setSignFromField(row, (colShifter - 1), '_'); // deletes sign because we are moving from left
																		// to right
					board.setSignFromField(row, colShifter, sign); // sets new sign
					tmpCounter--;
					colShifter--;
				}
			} else {
				tokenCounter++;
			}
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

	public boolean isValidBlockMove(int row, int column) {
		row = row - 1;
		column = column - 1;
		// char[][] checkField = board.getBoard();

		if (checkField[row][column] == '#') {
			return false;
		} else
			return true;

	}

	public boolean isValidBombMove(String inputString) {
		int[] cords = inputConversion.inputToCords(inputString);
		int row = cords[0];
		int column = cords[1];

		if (checkField[row][column] == '#' || getPlayer().getPlayerBombStatus() == false) {
			return false;
		} else {
			return true;
		}
	}

	public void setBomb(int row, int column) {
		blast(row, column);
		blast(row, column);
		blast(row + 1, column);
		blast(row - 1, column);
		blast(row, column + 1);
		blast(row, column - 1);
	}

	public void blast(int row, int column) {
		if (board.getSignFromField(row, column) != '#')
		board.setSignFromField(row, column, '_');
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
					boardIsFull = false;
				} else
					boardIsFull = true;
			}
		}
		return boardIsFull;
	}

}
