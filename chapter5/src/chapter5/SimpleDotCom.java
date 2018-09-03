package chapter5;

public class SimpleDotCom
{
    // Instance Variables
    private int numOfguesses;
    private int[] locationCell;
    // Methods
    public void setLocationCell(int[] cellLocation) {
        locationCell = cellLocation;
    }
    public String checkGuess(String guess) {
        String result = "miss";
        int intGuess = Integer.parseInt(guess);
        for (int cell: locationCell) {
            if (intGuess == cell) {
                result = "hit";
                numOfguesses++;
                break;
            }
        }
        if (numOfguesses == 3) {
            result = "kill";
        }
        System.out.println(result);
        return result;
    }

    public static void playGame() {
        int numGuesses = 0;
        SimpleDotCom game = new SimpleDotCom();
        GameHelper helper = new GameHelper();
        int r = (int) (Math.random() * 5); // compute a random number between 0 and 4
        int[] dotcomLoc = {r, r + 1, r + 2};
        game.setLocationCell(dotcomLoc);
        boolean isAlive = true;

        while (isAlive) {
            String guess = helper.getUserInput("Please enter a number: ");
            String result = game.checkGuess(guess);
            numGuesses++;
            if (result == "kill") {
                isAlive = false;
                System.out.println("Number of guesses: " + numGuesses);
            }
        }
    }

}
