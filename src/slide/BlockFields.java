package slide;

/** using the Scanner Library for input from Terminal */
import java.util.Scanner;

/**
 * This class implements the blocking of fields
 * 
 * @author Ch
 * @version 1.0
 * 
 */
public class BlockFields {

	private int row;
	private int col;
	private int availableBlockFields1 = 3;
	private int availableBlockFields2 = 3;
	private int currentplayer = 0;
	private int playercounter = 0;
	private int countBlock;

	/**
	 * This method initializes the blocking of fields
	 * 
	 * It initializes the current player und counts their availbale moves they have
	 * for block fields. Further this method let the player decides if they want to
	 * block fields. If the player says yes the placeBlock method is called
	 * ortherwise the player is changed and this method starts again.
	 * 
	 * @param logic includes the game logic
	 * @param input
	 * 
	 */
	public void initBlockFields(GameLogic logic, Scanner input) {

		for (int i = 1; i < 7; i++) {
			currentplayer = playercounter % 2;
			countBlock = currentplayer == 0 ? availableBlockFields1 : availableBlockFields2;
			if (countBlock == 0) {
				playercounter++;
				continue;
			}
			System.out.println(
					"Spieler" + (currentplayer + 1) + " ist dran, du darfst [" + countBlock + "] Feld(er) blockieren\n"
							+
							"Soll ein Feld geblockt werden? (j/n)");
			String block = input.next();
			while (!block.equals("j") && !block.equals("n")) {
				System.out.println("Soll ein Feld geblockt werden? (j/n)");
				block = input.next();
			}
			if (block.equals("j")) {
				placeBlock(input, logic);
			} else if (block.equals("n")) {
				if (currentplayer == 0) {
					availableBlockFields1 = 0;
					playercounter++;
				} else {
					availableBlockFields2 = 0;
					playercounter++;
				}
			}
		}
	}

	/**
	 * This method places the Block from given Directions from Player (input).
	 * 
	 * 
	 * @param input
	 * @param logic
	 */
	public void placeBlock(Scanner input, GameLogic logic) {

		if (countBlock > 0) {
			System.out.println("Bitte wählen Sie " + countBlock + " Feld(er), die/welches Sie blockieren möchten:\n");
			logic.printBoard();
			System.out.print("REIHE: ");
			row = input.nextInt();
			System.out.print("SPALTE: ");
			col = input.nextInt();
			while ((row < 1 || row > 6 || col < 1 || col > 7) || !logic.isValidBlockMove(row, col)) {
				System.out.println("\nFehlerhafte Eingabe bzw. Feld besetzt bitte korrigieren...\n");
				System.out.print("REIHE: ");
				row = input.nextInt();
				System.out.print("SPALTE: ");
				col = input.nextInt();
			}
			logic.setBlockField(row, col);
			if (currentplayer == 0) {
				availableBlockFields1--;
			} else
				availableBlockFields2--;
			logic.printBoard();
			playercounter++;
		}
	}
}