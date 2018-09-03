package chapter6;

import java.util.ArrayList;

public class DotComTest {
    public static void  main() {
        System.out.println("DotCom Unit Test");
        // Create DotCom Object
        DotCom dotCom = new DotCom();
        // Test check & set Location Method
        String[] vals = {"a1", "a2", "a3"};
        ArrayList<String> cellLocations = new ArrayList<String>(3);
        for (String val : vals) {
            cellLocations.add(val);
        }
        dotCom.setCellLocations(cellLocations);
        String guess1 = "a7";
        String guess2 = "a1";
        String guess3 = "a1";
        String guess4 = "a2";
        String guess5 = "a3";

        String result1 = dotCom.checkGuess(guess1);
        String result2 = dotCom.checkGuess(guess2);
        String result3 = dotCom.checkGuess(guess3);
        String result4 = dotCom.checkGuess(guess4);
        String result5 = dotCom.checkGuess(guess5);

        System.out.println("Test 1 " + ((result1.equals("miss")) ? "Pass" : "Fail"));
        System.out.println("Test 2 " + ((result2.equals("hit")) ? "Pass" : "Fail"));
        System.out.println("Test 3 " + ((result3.equals("miss")) ? "Pass" : "Fail"));
        System.out.println("Test 4 " + ((result4.equals("hit")) ? "Pass" : "Fail"));
        System.out.println("Test 5 " + ((result5.equals("kill")) ? "Pass" : "Fail"));
        // Test name setter and getter method
        String name = "testName";
        dotCom.setName(name);
        System.out.println("Test 6 " + ((name.equals(dotCom.getName())) ? "Pass" : "Fail"));
    }

}
