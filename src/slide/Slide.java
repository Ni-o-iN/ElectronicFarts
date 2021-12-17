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
				blockFields(logic, input);
				break;
			case 3:
				// TODO COM VS COM implementieren!
				System.out.println("COM vs COM und der Gewinner ist: COM");
				break;
		}

	}

	public void blockFields(GameLogic logic, Scanner input) {
		int row, col, availableBlockfields1 = 3, availableBlockfields2 = 3, currentplayer = 0, playercounter = 0,
				countBlock;

		for (int i = 1; i < 7; i++) {
			currentplayer = playercounter % 2;
			if (currentplayer == 0)
				countBlock = availableBlockfields1;
			else
				countBlock = availableBlockfields2;

			if (countBlock == 0) {
				playercounter++;
				continue;
			}
			System.out.println(
					"Spieler" + (currentplayer + 1) + " ist dran, du darfst [" + countBlock + "] Feld(er) blockieren");
			System.out.println("Soll ein Feld geblockt werden? (j/n)");
			String block = input.next();
			if (!block.equals("j")) {
				if (currentplayer == 0) {
					availableBlockfields1 = 0;
					playercounter++;
					continue;
				} else {
					availableBlockfields2 = 0;
					playercounter++;
					continue;
				}
			}
			if (countBlock > 0) {
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
					} while (row < 0 && row > 6 && col < 0 && col > 7);
				}

				if (logic.isValidBlockMove(row, col)) {
					logic.setBlockField(row, col);
					if (currentplayer == 0) {
						availableBlockfields1--;
					} else
						availableBlockfields2--;
				} else {
					do {
						System.out.println("\nFeld ist besetzt\n");
						System.out.print("REIHE: ");
						row = input.nextInt();
						System.out.print("SPALTE: ");
						col = input.nextInt();
					} while (row < 0 && row > 6 && col < 0 && col > 7);
					logic.setBlockField(row, col);
				}

				logic.printBoard();
				playercounter++;
			}
		}
	}

	public void playGame(GameLogic logic, Scanner input) {
		while (logic.isRunning()) {
			System.out.println("Es ist " + logic.getPlayerName() + " dran");
			System.out.println("Bitte geben Sie an von welcher Seite aus Sie einwerfen möchten: ");
			input.next();
			String direction = input.nextLine();
			System.out.println("Bitte geben Sie an in welcher Reihe/Spalte Sie einwerfen möchten: ");
			int position = input.nextInt();
			// logic.myMove(direction, position);
			logic.printBoard();
			logic.updateMoveCounter();
		}
		input.close();
	}
}
