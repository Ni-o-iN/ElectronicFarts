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
        cords[0] = inputString.charAt(1);
        cords[1] = inputString.charAt(3);
        return cords;
    }
}
