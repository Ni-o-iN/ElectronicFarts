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
		slide.getBoardObject();
		slide.initGame(slide.getBoardObject(), slide.getScannerObject());
		slide.playGame(slide.getBoardObject(), slide.getScannerObject()); // TODO implement difficulty and add it to
																			// playGame()
	}

	public String playerInput() {
		Scanner sc = new Scanner(System.in);
		String playerInput = sc.nextLine();
		sc.close();
		return playerInput;
	}

	public GameLogic getBoardObject() {
		GameLogic logic = new GameLogic();
		return logic;
	}


	public Scanner getScannerObject() {
		Scanner input = new Scanner(System.in);
		return input;
	}

	public void initGame(GameLogic board, Scanner input) {
		System.out.println("Herzlich willkommen bei dem Besten Spiel der Welt");
		System.out.println("Bitte Spielmodus auswählen");
		System.out.println(" 1: P1 vs COM1 \n 2: P1 vs P2 \n 3: COM1 vs COM2");
		int gameMode = input.nextInt();
		switch (gameMode) {
			case 1:
				board.setCOM();
				board.addPlayer();
				break;
			case 2:
				board.addPlayer();
				break;
			case 3:
				// TODO COM VS COM implementieren!
				System.out.println("COM vs COM und der Gewinner ist: COM");
				break;
		}
		board.printBoard();
		// #region Only for testing reasons. Please put this in another Method later
		System.out.println("Bitte wählen Sie drei Felder, die Sie blockieren möchten:");
		String skipString = input.nextLine(); // So that it doesnt skip the next in.nextLine() (ignore it)
		String inputString = input.nextLine();
		int[] coordinates = convertInputIntoCords(inputString);
		board.setBlockField(coordinates[0], coordinates[1]);
		String inputString1 = input.nextLine();
		int[] coordinates1 = convertInputIntoCords(inputString1);
		board.setBlockField(coordinates1[0], coordinates1[1]);
		String inputString2 = input.nextLine();
		int[] coordinates2 = convertInputIntoCords(inputString2);
		board.setBlockField(coordinates2[0], coordinates2[1]);
		board.printBoard();
		// #endregion
	}

	private void convertInputIntoDirectionAndPosition(String inputString) { // TODO new class for InputConversion
																			// instead of these methods
	}

	public void playGame(GameLogic board, Scanner input) {
		while (board.isRunning()) {

			System.out.println("Bitte geben Sie an von welcher Seite aus Sie einwerfen möchten: ");
			input.next();
			String direction = input.nextLine();
			System.out.println("Bitte geben Sie an in welcher Reihe/Spalte Sie einwerfen möchten: ");
			int position = input.nextInt();
			board.myMove(direction, position);
		}
		input.close();
	}

	public int[] convertInputIntoCords(String inputString) {
		int[] cords = new int[2];
		switch (inputString) {
			case "1,1":
				cords[0] = 0;
				cords[1] = 0;
				break;
			case "1,2":
				cords[0] = 0;
				cords[1] = 1;
				break;
			case "1,3":
				cords[0] = 0;
				cords[1] = 2;
				break;
				//...
		}
		return cords;
	}
}
