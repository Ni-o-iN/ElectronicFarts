package slide;

public class InputConversion {

    /*
     * Method removes number from direction
     */
    public String inputToDirection(String inputString) {
        String direction = "";
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) >= 'A' && inputString.charAt(i) <= 'z') {
                direction += inputString.charAt(i);
            }
        }
        if (direction.equals("Links") || direction.equals("Rechts") || direction.equals("Oben")
                || direction.equals("Unten")) {
            return direction;
        } else {
            return null;
        }
    }

    public int inputToPosition(String inputString) {
        int intCounter = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9') {
                intCounter++;
            }
        }
        if (intCounter == 1) {
            return Character.getNumericValue(inputString.charAt(inputString.length() - 1));
        } else
            return -1;
    }

    public int[] inputToCords(String inputString) {
        int[] cords = new int[2];
        cords[0] = Character.getNumericValue(inputString.charAt(1));
        cords[1] = Character.getNumericValue(inputString.charAt(3));
        return cords;
    }

    public boolean checkInputBomb(String bombCordString) {
        return (bombCordString.charAt(0) == '(' && bombCordString.charAt(1) > '0' && bombCordString.charAt(1) < '7'
                && bombCordString.charAt(2) == ',' && bombCordString.charAt(3) > '0' && bombCordString.charAt(1) < '8'
                && bombCordString.charAt(4) == ')');
    }
}
