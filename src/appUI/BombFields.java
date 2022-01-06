package appUI;

import java.util.Scanner;

import gamelogic.GameLogic;
import gamelogic.InputConversion;

public class BombFields {

    private int row;
    private int column;
    private boolean bombAvailable = true;
    private InputConversion inputConversion = new InputConversion();

    public void initBomb(GameLogic logic, Scanner input) {
        for (int i = 0; i < 2; i++) {
            bombAvailable = logic.getPlayer().getPlayerBombStatus();
            if (bombAvailable == false) {
                return;
            }
            System.out.println(logic.getPlayerName() + " ist dran, du darfst eine Bombe platzieren");
            System.out.println("Soll eine Bombe platziert werden? (j/n)");
            String bomb = input.next();
            while (!bomb.equals("j") && !bomb.equals("n")) {
                System.out.println("Soll eine Bombe platziert werden? (j/n)");
                bomb = input.next();
            }
            if (bomb.equals("j")) {
                placeBomb(input, logic);
            } else if (bomb.equals("n")) {
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
            System.out.println("Bitte waehlen Sie ein Feld, auf dem Sie die Bombe platzieren moechten");
            logic.printBoard();
            System.out.println("mit folgendem Format: (REIHE,SPALTE)\n" +
            "Beispiel: (2,3)\n");
            String bombCordString = input.next();
            if(inputConversion.checkInputBomb(bombCordString)) {
                row = inputConversion.inputToCords(bombCordString)[0];
                column = inputConversion.inputToCords(bombCordString)[1];
                while ((row < 1 || row > 6 || column < 1 || column > 7) || !logic.isValidBombMove(bombCordString)) {
                    System.out.println("\nFehlerhafte Eingabe bzw. Feld besetzt bitte korrigieren...\n");
                    bombCordString = input.next();
                }
                logic.setBomb(row, column);
                logic.getPlayer().setPlayerBombStatus(false);
                logic.printBoard();
            } else placeBomb(input, logic);
        }
    }
}