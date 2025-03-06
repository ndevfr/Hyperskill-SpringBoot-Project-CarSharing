package carsharing;

import java.util.Scanner;

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    public static String getStringInput() {
        return scanner.nextLine();
    }

    public static int getIntInput(){
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
