package slide;

import java.util.Scanner;

/**
 * 
 * @author Team
 * @version 0.1
 *          This class will handle every input and output of the game
 */
public class Slide {

	public static void main(String[] args) {
		Slide slide = new Slide();
		GameLogic logic = slide.getLogicObject();
		Scanner scanner = slide.getScannerObject();
		slide.initGame(logic, scanner);
		slide.playGame(logic, scanner); // TODO implement difficulty and add it to playGame()
	}

	public String playerInput() {
		Scanner sc = new Scanner(System.in);
		String playerInput = sc.nextLine();
		sc.close();
		return playerInput;
	}

	public GameLogic getLogicObject() {
		GameLogic logic = new GameLogic();
		return logic;
	}

	public Scanner getScannerObject() {
		Scanner input = new Scanner(System.in);
		return input;
	}

	public void initGame(GameLogic logic, Scanner input) {
		System.out.println("Herzlich willkommen bei dem Besten Spiel der Welt");
		System.out.println("Bitte Spielmodus auswählen");
		System.out.println(" [1]: P1 vs COM1 \n [2]: P1 vs P2 \n [3] : COM1 vs COM2");
		int gameMode = input.nextInt();
		switch (gameMode) {
			case 1:
				logic.setCOM();
				logic.addPlayer();
				break;
			case 2:
				logic.addPlayer();
				break;
			case 3:
				// TODO COM VS COM implementieren!
				System.out.println("COM vs COM und der Gewinner ist: COM");
				break;
		}
		// logic.printBoard();
		// #region Only for testing reasons. Please put this in another Method later
		// System.out.println("Bitte wählen Sie drei Felder, die Sie blockieren
		// möchten:\n");
		// input.nextLine(); // So that it doesnt skip the next in.nextLine() (ignore
		// it)
		int row, col, countBlock = 3;
		do {
			System.out.println("Sie dürfen [" + countBlock + "] Feld(er) blocken");
			System.out.println("Soll ein Feld geblockt werden? (j/n)");
			String blockAnother = input.next();
			if (!blockAnother.equals("j"))
				break;
			System.out.println("Bitte wählen Sie drei Felder, die Sie blockieren möchten:\n");
			logic.printBoard();
			System.out.print("REIHE: ");
			row = input.nextInt();
			System.out.print("SPALTE: ");
			col = input.nextInt();
			if (row < 1 || row > 6 || col < 1 || col > 7) {
				System.out.println("Fehlerhafte Eingabe bitte korrigieren...");
				do {
					System.out.print("REIHE: ");
					row = input.nextInt();
					System.out.print("SPALTE: ");
					col = input.nextInt();
				} while (row < 0 && row > 7 && col < 0 && col > 8);
			}
			logic.setBlockField(row, col);
			countBlock--;
			logic.printBoard();
		} while (row > 0 && row < 7 && col > 0 && col < 8 && countBlock > 0);
		// logic.setBlockField(coordinates[0], coordinates[1]);
		// String inputString1 = input.nextLine();
		// int[] coordinates1 = convertInputIntoCords(inputString1);
		// logic.setBlockField(coordinates1[0], coordinates1[1]);
		// String inputString2 = input.nextLine();
		// int[] coordinates2 = convertInputIntoCords(inputString2);
		// logic.setBlockField(coordinates2[0], coordinates2[1]);
		logic.printBoard();
		// #endregion
	}

	private void convertInputIntoDirectionAndPosition(String inputString) { // TODO new class for InputConversion
																			// instead of these methods
	}

	public void playGame(GameLogic logic, Scanner input) {
		while (logic.isRunning()) {
			System.out.println("Es ist " + logic.getPlayerName() + " dran");
			System.out.println("Bitte geben Sie an von welcher Seite aus Sie einwerfen möchten: ");
			input.next();
			String direction = input.nextLine();
			System.out.println("Bitte geben Sie an in welcher Reihe/Spalte Sie einwerfen möchten: ");
			int position = input.nextInt();
			//logic.myMove(direction, position);
			logic.printBoard();
			logic.updateMoveCounter();
		}
		input.close();
	}

	// public int[] convertInputIntoCords(String inputString) {
	// int[] cords = new int[2];
	// switch (inputString) {
	// case "1,1":
	// cords[0] = 0;
	// cords[1] = 0;
	// break;
	// case "1,2":
	// cords[0] = 0;
	// cords[1] = 1;
	// break;
	// case "1,3":
	// cords[0] = 0;
	// cords[1] = 2;
	// break;
	// //...
	// }
	// return cords;
	// }
}
