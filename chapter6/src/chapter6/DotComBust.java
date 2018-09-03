package chapter6;
import java.util.ArrayList;
public class DotComBust {
    // instance variables
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    int numGuesses = 0;
    // Declare Methods
    private void setUpGame() {
        DotCom one = new DotCom();
        DotCom two = new DotCom();
        DotCom three = new DotCom();
        // set Names
        one.setName("Pets.com");
        two.setName("eToys.com");
        three.setName("Go2.com");
        // add to dotComList
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);
        // Print game instructions
        System.out.println("Your goal is to sink three dot coms,");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink all in the fewest number of guesses");
        for (DotCom dotCom : dotComList) {
            ArrayList<String> newLocation = helper.placeDotCom(3, false);
            dotCom.setCellLocations(newLocation);
        }
    }
    private void startPlaying() {
        while (!dotComList.isEmpty()) {
            String userGuess = helper.getUserImport("Enter a guess");
            checkUserGuess(userGuess);

        }
        finishGame();
    }
    private void checkUserGuess(String userGuess) {
        numGuesses++;
        String result = "miss";
        for (DotCom dc: dotComList) {
            result = dc.checkGuess(userGuess);
            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                dotComList.remove(dc); // remove DotCom Object from dotComList
                break;
            }
        }
        System.out.println(result);
    }
    private void finishGame() {
        System.out.println("All Dot Coms are destroyed! Your stock is now worthless!");
        if (numGuesses <= 18) {
            System.out.println("It only took you " + numGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }


    public void main() {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }




}
