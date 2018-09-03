package chapter5;

public class SimpleDotComTest
{
    public static void main() {
        SimpleDotCom game = new SimpleDotCom();
        int[] cellLocations = {1, 2, 3};
        game.setLocationCell(cellLocations);

        String expected1 = "miss";
        String expected2 = "hit";
        String expected3 = "hit";
        String expected4 = "kill";

        String result1 = game.checkGuess("5");
        String result2 = game.checkGuess("1");
        String result3 = game.checkGuess("2");
        String result4 = game.checkGuess("3");

        // Check test results against expected
        System.out.println("Test One: " + ((expected1.equals(result1)) ? "Pass" : "Fail"));
        System.out.println("Test Two: " + ((expected2.equals(result2)) ? "Pass" : "Fail"));
        System.out.println("Test Three: " + ((expected3.equals(result3)) ? "Pass" : "Fail"));
        System.out.println("Test Four: " + ((expected4.equals(result4)) ? "Pass" : "Fail"));
        System.out.println();

    }

}
