package chapter5;
import java.util.Scanner;

public class GameHelper {

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        try {
            Scanner s = new Scanner(System.in);
            inputLine = s.nextLine();
            if (inputLine.length() == 0) return null;
        } catch (java.util.InputMismatchException e) {
            System.out.println("InputMismatchException: " + e);
        }
        return inputLine;
    }
}
