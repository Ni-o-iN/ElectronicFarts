package gamelogic;

import slide.Board;
import slide.Player;

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
	private boolean com = false;
	private char[][] checkField = board.getBoard();

	public void setCom(boolean com) { // rdy
		this.com = com;
	}

	public boolean getCom() { // rdy
		return this.com;

	}

	public void addPlayer() {
		players[0] = new Player(1, "Spieler1");
		if (getCom())
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

	public void printBoard() {
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

	/*
	 * checks if in any direction is a winning stone-combination and outputs the
	 * winner
	 */
	public boolean isRunning() {
		char[][] tmp = board.getBoard();
		if (searchRow(tmp)) {
			System.out.println("Gewinner ist: " + getWinnerName(whoWon()));
			return false;
		} else if (searchCol(tmp)) {
			System.out.println("Gewinner ist: " + getWinnerName(whoWon()));
			return false;
		} else if (searchDiagonalNorthWest(tmp)) {
			System.out.println("Gewinner ist: " + getWinnerName(whoWon()));
			return false;
		} else if (searchDiagonalSouthWest(tmp)) {
			System.out.println("Gewinner ist: " + getWinnerName(whoWon()));
			return false;
		} else if (boardIsFull(tmp)) {
			return false;
		} else
			return true;
	}

	/*
	 * Receive Player input and throws the token into the line wich was chosen by
	 * the Player
	 */
	public void myMove(String input) {
		String direction = inputConversion.inputToDirection(input); //
		int position = inputConversion.inputToPosition(input);
		if (direction.equals("Oben")) {
			lastFreeFieldFromTop(0, position - 1);
		} else if (direction.equals("Rechts")) {
			lastFreeFieldFromRight(position - 1, 6);
		} else if (direction.equals("Unten")) {
			lastFreeFieldFromBottom(5, position - 1);
		} else if (direction.equals("Links")) {
			lastFreeFieldFromLeft(position - 1, 0);
		}
	}

	/*
	 * 
	 */
	public boolean isValidMove(String input) {
		String direction = inputConversion.inputToDirection(input);
		if (null == direction){
			return false;
		}
		int position = inputConversion.inputToPosition(input);
		int row = directionInterpreter(direction, position)[0];
		int column = directionInterpreter(direction, position)[1];
		return (isInputValid(row, column) && inputConversion.inputToDirection(input) != null);
	}

	/*
	 * Assign's the number to the correct array position for further calculation
	 */
	public int[] directionInterpreter(String direction, int position) {
		int[] arr = new int[2];
		if (direction.equals("Oben")) {
			arr[0] = 1;
			arr[1] = position;
		} else if (direction.equals("Rechts")) {
			arr[0] = position;
			arr[1] = 6;
		} else if (direction.equals("Unten")) {
			arr[0] = 5;
			arr[1] = position;
		} else if (direction.equals("Links")) {
			arr[0] = position;
			arr[1] = 1;
		}
		return arr;
	}

	/*
	 * Habe alle Last FreeField Methoden gefixt und getestet ( optimiert) unnötige
	 * Zähler wurden entfernt. ggf. schauen wegen den übergabe parameter tokenSign
	 * wurde ebenfalls entfernt
	 */
	private void lastFreeFieldFromTop(int row, int column) {
		for (int i = 0; i <= 5; i++) {
			if (board.isEmpty(i, column) && i == 5) {
				board.setSignFromField(i, column, getPlayerSign());
			} else if (board.isToken(0, column)) {
				slideNextTokenFromTop(row, column); 
			}else if (board.isToken(i, column)) {
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

	private void slideNextTokenFromTop(int row, int column) {
		int tokenCounter = 0;
		for (int i = row; i <= 5; i++) {
			if (board.isBlocked(i, column))
				break;
			else if (board.isEmpty(i, column)) {
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

	private void lastFreeFieldFromBottom(int row, int column) {
		for (int i = 5; i >= 0; i--) {
			if (board.isEmpty(i, column) && i == 0) {
				board.setSignFromField(i, column, getPlayerSign());
			} else if (board.isToken(5, column)) {
				slideNextTokenFromBottom(row, column); 
			}else if (board.isToken(i, column)) {
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

	private void slideNextTokenFromBottom(int row, int column) {
		int tokenCounter = 0;
		for (int i = row; i >= 0; i--) {
			if (board.isBlocked(i, column))
				break;
			else if (board.isEmpty(i, column)) {
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

	private void lastFreeFieldFromRight(int row, int column) {
		for (int i = 6; i >= 0; i--) {
			if (board.isEmpty(row, i) && i == 0) {
				board.setSignFromField(row, i, getPlayerSign());
			} else if (board.isToken(row, 6)) {
				slideNextTokenFromRight(row, column);
			} else  if (board.isToken(row, i)) {
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

	private void slideNextTokenFromRight(int row, int column) {
		int tokenCounter = 0;
		for (int i = column; i >= 0; i--) {
			if (board.isBlocked(row, i))
				break;
			else if (board.isEmpty(row, i)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while (tmpCounter != 0) {
					char sign = board.getSignFromField(row, (colShifter + 1)); // get sign before empty field
					board.setSignFromField(row, (colShifter + 1), '_'); // deletes sign because we are moving from right to left
					board.setSignFromField(row, colShifter, sign); // sets new sign
					tmpCounter--;
					colShifter++;
				}
			} else {
				tokenCounter++;
			}
		}
	}

	private void lastFreeFieldFromLeft(int row, int column) {
		for (int i = 0; i <= 6; i++) {
			if (board.isEmpty(row, i) && i == 6) {
				board.setSignFromField(row, i, getPlayerSign());
			} else if (board.isToken(row, 0)) {
				slideNextTokenFromLeft(row, column);
			}else if (board.isToken(row, i)) {
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

	private void slideNextTokenFromLeft(int row, int column) {
		int tokenCounter = 0;
		for (int i = column; i <= 6; i++) {
			if (board.isBlocked(row, i))
				break;
			else if (board.isEmpty(row, i)) {
				int colShifter = i, tmpCounter = tokenCounter;
				while (tmpCounter != 0) {
					char sign = board.getSignFromField(row, (colShifter - 1)); 
					board.setSignFromField(row, (colShifter - 1), '_'); 
					board.setSignFromField(row, colShifter, sign); 
					tmpCounter--;
					colShifter--;
				}
			} else {
				tokenCounter++;
			}
		}
	}

	public void incrementMoveCounter() {
		moveCounter++;
	}

	public int getMoveCounter() {
		return moveCounter;
	}

	public char getPlayerSign() { // rdy
		return (moveCounter % 2 == 0) ? 'O' : 'X';
	}

	public boolean isValidBlockMove(int row, int column) {
		return checkField[row - 1][column - 1] != '#';
	}

	public boolean isValidBombMove(String inputString) {
		int[] cords = inputConversion.inputToCords(inputString);
		int row = cords[0];
		int column = cords[1];
		return isInputValid(row, column);
	}

	public void setBomb(int row, int column) {
		if (isInputValid(row, column)) {
			blast(row, column);
			if (row > 1 && isBlockField(row - 1, column)) { // Check if top field is free
				blast(row - 1, column);
			}
			if (row < 6 && isBlockField(row + 1, column)) { // Check if bottom field is free
				blast(row + 1, column);
			}
			if (column > 1 && isBlockField(row, column - 1)) { // Check if left field is free
				blast(row, column - 1);
			}
			if (column < 7 && isBlockField(row, column + 1)) { // Check if right field is free
				blast(row, column + 1);
			}
		}
	}

	private boolean isBlockField(int row, int column) {
		return checkField[row - 1][column - 1] == '#';
	}

	public void blast(int row, int column) {
		board.setSignFromField(row - 1, column - 1, '_');
	}

	public boolean isInputValid(int row, int column) {
		return (row > 0 && row < 7 && column > 0 && column < 8 && !isBlockField(row, column));
	}

	public boolean searchRow(char[][] tmp) {
		// check every row from left to right
		int countHit = 0;
		for (int row = 0; row < tmp.length; row++) {
			if (countHit >= 4)
				return true;
			countHit = 0;
			for (int column = 0; column < tmp[row].length; column++) {
				if (countHit >= 4)
					return true;
				else if (tmp[row][column] == getPlayerSign()) {
					countHit++;
				} else
					countHit = 0;
			}
		}
		return false;
	}

	public boolean searchCol(char[][] tmp) {

		// check every column from top to bottom
		int countHit = 0;
		for (int column = 0; column < tmp[0].length; column++) {
			if (countHit >= 4)
				return true;
			countHit = 0;
			for (int row = 0; row < tmp.length; row++) {
				if (countHit >= 4)
					return true;
				else if (tmp[row][column] == getPlayerSign()) {
					countHit++;
				} else
					countHit = 0;
			}
		}
		return false;
	}

	/*
	 * for detecting if game is won. While loop row <= 2 please mind the array index
	 * 2 = (3)
	 */
	public boolean searchDiagonalNorthWest(char[][] tmp) {
		int countHit = 0;
		int row = 0, column = 0;

		while (row < 2 && column < 3) {
			if (countHit >= 4)
				return true;
			if (column > 3) {
				row++;
				column = 0;
			}
			if (tmp[row][column] == getPlayerSign())
				countHit++;
			else
				countHit = 0;
			column++;
		}
		if (countHit >= 4)
			return true;
		else
			return false;

	}

	/*
	 * for detecting if game is won. While loop row >= please mind the array index 5
	 * = (6)
	 */
	public boolean searchDiagonalSouthWest(char[][] tmp) {
		int countHit = 0;
		int row = 5, column = 0;

		while (row > 3 && column < 3) {
			if (countHit >= 4)
				return true;
			if (column > 3) {
				row--;
				column = 0;
			}
			if (tmp[row][column] == getPlayerSign())
				countHit++;
			else
				countHit = 0;
			column++;
		}
		if (countHit >= 4)
			return true;
		else
			return false;

	}

	public boolean whoWon() {
		return getPlayerName().equals("Spieler1");
	}

	public String getWinnerName(boolean whoWon) {
		return whoWon ? "Spieler2" : "Spieler1";
	}

	public boolean boardIsFull(char[][] tmp) {
		boolean boardIsFull = false;
		for (int z = 0; z < tmp.length; z++) {
			for (int s = 0; s < tmp[z].length; s++) {
				if (tmp[z][s] == '_') {
					return false;
				} else
					boardIsFull = true;
			}
		}
		return boardIsFull;
	}

}
