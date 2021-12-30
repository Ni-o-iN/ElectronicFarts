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
		BlockFields blockFields = new BlockFields();
		BombFields bombFields = new BombFields();
		Slide slide = new Slide();
		GameLogic logic = slide.getLogicObject();
		Scanner scanner = slide.getScannerObject();
		slide.initGame(logic, scanner, blockFields);
		slide.playGame(logic, scanner, bombFields); // TODO implement difficulty and add it to playGame()
	}

	public void initGame(GameLogic logic, Scanner input, BlockFields blockFields) {
		System.out.println("Herzlich willkommen bei dem Besten Spiel der Welt");
		System.out.println("Bitte Spielmodus auswählen");
		System.out.println(" [1]: P1 vs COM1 \n [2]: P1 vs P2 \n [3] : COM1 vs COM2");
		int gameMode = Integer.parseInt(input.nextLine());
		switch (gameMode) {
			case 1:
				logic.setCOM();
				logic.addPlayer();
				break;
			case 2:
				logic.addPlayer();
				blockFields.initBlockFields(logic, input);
				break;
			case 3:
				// TODO COM VS COM implementieren!
				System.out.println("COM vs COM und der Gewinner ist: COM");
				break;
		}

	}

	public void playGame(GameLogic logic, Scanner input, BombFields bombFields) {
		while (logic.isRunning()) {
			if(logic.getMoveCounter() > 0) {
			bombFields.initBomb(logic, input);
			}
			System.out.println("Es ist " + logic.getPlayerName() + " dran");
			String inputString = input.next() + input.nextLine();
			if(logic.isValidMove(inputString)) {
			logic.myMove(inputString);
			logic.printBoard();
			logic.updateMoveCounter();
			} else {
				System.out.println("\nFeld ist besetzt!\n");
			}
		}
		input.close();
	}

	public GameLogic getLogicObject() {
		GameLogic logic = new GameLogic();
		return logic;
	}

	public Scanner getScannerObject() {
		Scanner input = new Scanner(System.in);
		return input;
	}
}
