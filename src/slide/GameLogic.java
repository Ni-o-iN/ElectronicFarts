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
		if(moveCounter % 2 == 0) {
			return 'O';
		}else 
			return 'X';
	}
	

	public void myMove(String direction, int position) {
		if(direction == "left") {
			if(board.getSignFromField(position, 1) == '_') {	//can you even slide the token in this row
				int i = 1;
				while(board.getSignFromField(position, i+1) == '_') {	//can the token slide one field further 
					i++;
				}
				board.setField(position, i, setPlayerSign());
			} else {
				isValidMove();
			}
		} 
		
		if(direction == "right") {
			if(board.getSignFromField(position, 7) == '_') {	//can you even slide the token in this row
				int i = 7;
				while(board.getSignFromField(position, i+1) == '_') {	//can the token slide one field further 
					i++;
				}
				board.setField(position, i, setPlayerSign());
			} else {
				isValidMove();
			}
		} 
		
		if(direction == "top") {
			if(board.getSignFromField(1, position) == '_') {	//can you even slide the token in this column
				int i = 1;
				while(board.getSignFromField(i+1, position) == '_') {	//can the token slide one field further
					i--;
				}
				board.setField(i, position, setPlayerSign());
			}else {
				isValidMove();
			}
		}
		
		if(direction == "bottom") {
			if(board.getSignFromField(6, position) == '_') {	//can you even slide the token in this column
				int i = 6;
				while(board.getSignFromField(i+1, position) == '_') {	//can the token slide one field further
					i--;
				}
				board.setField(i, position, setPlayerSign());
			}else {
				isValidMove();
			}
		}
	}

	private boolean isValidBombMove(int row, int column) {
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

	public boolean searchRaw() {
		
		
		
		return true;
	}
	public boolean whoWon() {
		char[][] tmp = board.getField();
		int count = 0;
//		check every row left to right
		for (int row = 0; row < tmp.length; row++) {
			if(count >= 4)
				return true;
			count = 0;
			for (int col = 0; col < tmp[row].length; col++) {
				if(count >= 4)
					return true;
				else if(tmp[row][col] == setPlayerSign()) {
					count++;
				}
				else
					count = 0;
			}
			
		}
//		check every column from above to below
		for (int col = 0; col < tmp[0].length; col++) {
			if(count >= 4)
				return true;
			count = 0;
			for (int row = 0; row < tmp.length; row++) {
				if(count >= 4)
					return true;
				else if(tmp[row][col] == setPlayerSign()) {
					count++;
				}
				else
					count = 0;
			}
			
		}
//		TODO Diagonalen
 		return true;
	}

}
