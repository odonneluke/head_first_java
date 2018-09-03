package chapter6;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // Unit test
        char unitTest = 'n';
        System.out.println("Do you want to unit test (y/n)? ");
        unitTest = s.nextLine().charAt(0);
        if (unitTest == 'y') {
            DotComTest dotComTest = new DotComTest();
            dotComTest.main();
            GameHelperTest gameHelperTest = new GameHelperTest();
            gameHelperTest.main();
        }
	    // Play the DotCom Game
        char playGame = 'n';
        System.out.println("Play DotCom Game (y/n)?: ");
        playGame = s.nextLine().charAt(0);
        if (playGame == 'y') {
            DotComBust game = new DotComBust();
            game.main();
        }
        // ArrayListMagnet
        ArrayListMagnet arrayListMagnet = new ArrayListMagnet();
        arrayListMagnet.main();
    }
}
