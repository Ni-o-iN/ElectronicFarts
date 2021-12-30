package slide;

public class InputConversion {

    public String inputToDirection(String inputString) {
        String direction = "";
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) >= 'A' && inputString.charAt(i) <= 'z') {
                direction = direction + inputString.charAt(i);
            }
        }
        return direction;
    }

    public int inputToPosition(String inputString) {
        return Character.getNumericValue(inputString.charAt((inputString.length() - 1))-1);
    }

    public int[] inputToCords(String inputString) {
        int[] cords = new int[2];
        cords[0] = Character.getNumericValue(inputString.charAt(1));
        cords[1] = Character.getNumericValue(inputString.charAt(3));
        return cords;
    }

    public boolean checkInputBomb(String bombCordString) {
        if(bombCordString.charAt(0) == '(' && bombCordString.charAt(1) > '0' && bombCordString.charAt(1) < '7' &&  bombCordString.charAt(2) == ',' && bombCordString.charAt(3) > '0' && bombCordString.charAt(1) < '8' && bombCordString.charAt(4) == ')') {
        return true;
        }
        else return false;
    }
}
