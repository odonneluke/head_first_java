package chapter11;

import java.util.Scanner;

class MyEx extends Exception {

}

public class ExTestDrive {
    public static void main() {
        String test = getInput();
        System.out.print("t");
        try {
            doRisky(test);
            System.out.print("r");
            System.out.print("o");
        } catch (MyEx e) {
            System.out.print("a");
        } finally {
            System.out.print("w");
        }
        System.out.println("s");

    }

    static void doRisky(String t) throws MyEx {
        System.out.print("h");
        if ("yes".equals(t)) {
            throw new MyEx();
        }
    }

    public static String getInput() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter \"yes\" or \"no\"");
        return s.nextLine();
    }
}