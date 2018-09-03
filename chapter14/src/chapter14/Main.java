package chapter14;

import javafx.application.Application;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    DungeonTest.main();
        Scanner s = new Scanner(System.in);
        System.out.println("Start QuizCardBuilder (y/n)?");
        if (s.nextLine().equals("y")) {
            QuizCardBuilder.main();
        }
        System.out.println("Start QuizCardPlayer (y/n)?");
        if (s.nextLine().equals("y")) {
            QuizCardPlayer.main();
        }
        System.out.println("Start BeatBox (y/n)?");
      if (s.nextLine().equals("y")) {
          Application.launch(BeatBox.class, args);
      }
    }
}
