package chapter12;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// Open simple gui
        Scanner scanner = new Scanner(System.in);
        System.out.println("Run SimpleGui1? (y/n)");
        String run = scanner.nextLine();

       if (run.equals("y")) {
           SimpleGui1.main();
       }

        System.out.println("Run SimpleGui1B? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            SimpleGui1B.main();
        }

        System.out.println("Run SimpleGui1C? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            SimpleGui1C.main();
        }

        System.out.println("Run SimpleGui2? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            SimpleGui2.main();
        }

        System.out.println("Run TwoButtons? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            TwoButtons.main();
        }

        System.out.println("Run SimpleAnimation? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            SimpleAnimation.main();
        }

        System.out.println("Run MiniMusicPlayer? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            MiniMusicPlayer.main();
        }

        System.out.println("Run InnerButton? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            InnerButton.main();
        }

        System.out.println("Run Animate? (y/n)");
        run = scanner.nextLine();

        if (run.equals("y")) {
            Animate.main();
        }
    }
}
