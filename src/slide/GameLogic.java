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

	public void myMove(String direction, int row) {
		String sign = "";
		// if( roundCount % 2 == 0)
		// sign = "O";
		// else
		// sign = "X";
	}

	private boolean isValidBombMove(int row, int column) { // 1.
		// if true.... call blast() 
		
		char[][] checkField = board.getField();
		if(checkField[row][column] == '#' || currentPlayer.getPlayerBombStatus() == false) {
			return false;
		}else
			return true;
		
	}
	public void setBomb(int row, int column) { 
		if(isValidBombMove(row, column)) {
			board.setField(row, column, board.getBlock());
			blast( row,  column);
			currentPlayer.setPlayerBombStatusFalse(); 
		
		}
	}

	private void blast(int row, int column) {
		board.setField(row, column, '_'); 	  // deletes field where bomb got placed
		board.setField(row + 1, column, '_'); // deletes field below bomb
		board.setField(row - 1, column, '_'); // deletes field above bomb
		board.setField(row, column + 1, '_'); // deletes field right next to bomb
		board.setField(row, column - 1, '_'); // deletes field left next to bomb
	}
	public boolean isRunning() {
		return false;
	}

	private boolean isValidMove() {
		return false;
	}

	private void printBoard() {
		char[][] field = board.getField();

		for (int s = 0; s < field[0].length; s++) {
			System.out.print((s + 1) + " ");
		}

		System.out.println();

		for (int z = 0; z < field.length; z++) {
			for (int s = 0; s < field[z].length; s++) {
				System.out.print(field[z][s] + " ");
			}
			System.out.println();
		}

		System.out.println();
	}

	private void setBlockField(int row, int column) {
		board.setField(row, column, board.getBlock());
	}


	public boolean whoWon() {
		return true;
	}

}
