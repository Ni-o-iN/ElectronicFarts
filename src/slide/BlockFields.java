package slide;

import java.util.Scanner;

public class BlockFields {

    GameLogic logic;
    int row, col, availableBlockFields1 = 3, availableBlockFields2 = 3, currentplayer = 0, playercounter = 0,
            countBlock;

    public void initBlockFields(GameLogic logic, Scanner input) {
        for (int i = 1; i < 7; i++) {
            currentplayer = playercounter % 2;
            if (currentplayer == 0)
                countBlock = availableBlockFields1;
            else
                countBlock = availableBlockFields2;
            if (countBlock == 0) {
                playercounter++;
                continue;
            }
            System.out.println(
                    "Spieler" + (currentplayer + 1) + " ist dran, du darfst [" + countBlock + "] Feld(er) blockieren");
            System.out.println("Soll ein Feld geblockt werden? (j/n)");
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
                    continue;
                } else {
                    availableBlockFields2 = 0;
                    playercounter++;
                    continue;
                }
            }
        }
    }

    public void placeBlock(Scanner input, GameLogic logic) {
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
                    availableBlockFields1--;
                } else
                    availableBlockFields2--;
            } else {
                do {
                    System.out.println("\nFeld ist besetzt\n");
                    System.out.print("REIHE: ");
                    row = input.nextInt();
                    System.out.print("SPALTE: ");
                    col = input.nextInt();
                } while (row < 0 && row > 6 && col < 0 && col > 7);
                logic.setBlockField(row, col);
                if (currentplayer == 0)
                    availableBlockFields1--;
                else
                    availableBlockFields2--;
            }
            logic.printBoard();
            playercounter++;
        }
    }
}
