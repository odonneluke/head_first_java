package chapter11;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // Mini music app
        String ans;
        System.out.println("Run Music App (y/n)");
        ans = s.nextLine();
        if (ans.equals("y")) {
            MiniMiniMusicApp.main();
        }
        System.out.println("Run Music App with user input? (y/n");
        ans = s.nextLine();
        if (ans.equals("y")) {
            MiniMusicUserInput.main();
        }
        ExTestDrive.main();
    }
}
