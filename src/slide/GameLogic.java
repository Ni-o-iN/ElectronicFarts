package slide;

/**
 * 
 * @author Team
 * @version 0.1
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
		if(moveCounter % 2 == 1) {
			currentPlayer = players[0];
		}
		else
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

	public void setBomb(int x, int y) { // x=Spalte und y=Reihe, Koordinatensystem

	}

	public boolean isRunning() {
		return false;
	}

}
