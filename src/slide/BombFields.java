package slide;

import java.util.Scanner;

public class BombFields {

    private int row;
    private int column;
    private int bombMoveCounter = 0;
    private boolean bombAvailable = true;
    private InputConversion inputConversion = new InputConversion();

    public void initBomb(GameLogic logic, Scanner input) {
        for (int i = 0; i < 2; i++) {
                bombAvailable = logic.getPlayers(bombMoveCounter % 2).getPlayerBombStatus();
            if (bombAvailable == false) {
                bombMoveCounter++;
                return;
            }
            System.out.println("Spieler" + ((bombMoveCounter % 2) + 1) + " ist dran, du darfst eine Bombe platzieren");
            System.out.println("Soll eine Bombe platziert werden? (j/n)");
            String bomb = input.next();
            while (!bomb.equals("j") && !bomb.equals("n")) {
                System.out.println("Soll eine Bombe platziert werden? (j/n)");
                bomb = input.next();
            }
            if (bomb.equals("j")) {
                placeBomb(input, logic);
                bombMoveCounter++;
            } else if (bomb.equals("n")) {
                bombMoveCounter++;
                return;
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
    public void placeBomb(Scanner input, GameLogic logic) {
        if (bombAvailable) {
            System.out.println("Bitte wählen Sie ein Feld, auf dem Sie die Bombe platzieren möchten mit folgendem Format: \"(REIHE,SPALTE)\"\n");
            logic.printBoard();
            String bombCordString = input.next();
            row = inputConversion.inputToCords(bombCordString)[0];
            column = inputConversion.inputToCords(bombCordString)[1];
            while ((row < 1 || row > 6 || column < 1 || column > 7) || !logic.isValidBombMove(bombCordString)) {
                System.out.println("\nFehlerhafte Eingabe bzw. Feld besetzt bitte korrigieren...\n");
                bombCordString = input.next();
            }
            logic.setBomb(row-1, column-1);
            logic.getPlayers(bombMoveCounter % 2).setPlayerBombStatus(false);
            logic.printBoard();
            bombMoveCounter++;
        }
    }
}