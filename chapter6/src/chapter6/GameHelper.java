package chapter6;

import java.util.ArrayList;
import java.util.Scanner;

public class GameHelper {
    // Instance variable
    private static final String ALPHABET = "abcdefg";
    private final int  GRIDLENGTH = 7;
    private final int  GRIDSIZE = 49; // Number of Cells in the game
    private int[] grid = new int[GRIDSIZE];
    private int comCount = 0;
    // Methods
    public String getUserImport(String prompt) {
        String inputLine = null;
        System.out.println(prompt + " ");
        Scanner s = new Scanner(System.in);
        inputLine = s.nextLine();
        if (inputLine.length() == 0) return null;
        return inputLine.toLowerCase();
    }
    public ArrayList<String> placeDotCom(int comSize, boolean test) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        String[] alphacoords = new String[comSize]; // holds string coordinates
        String temp = null;
        int[] coords = new int[comSize]; // current candidate coordinates
        int attempts = 0; // current number of attempt
        boolean success = false; // Is the location good
        int location = 0; // set starting position

        comCount++; // nth dotCom to place
        int incr = 1; // set horizontal increment
        if (comCount % 2 == 1) { // if odd dot com (place vertically)
            incr = GRIDLENGTH; // set vertical increment
        }
        while (!success && attempts++ < 200) {
            location = (int) (Math.random() * GRIDSIZE); // get starting point
            if (test) System.out.print(" try " + location);
            int x = 0;
            success = true;
            while (success && x < comSize) {
                if (grid[location] == 0) {  // not already use
                    coords[x++] = location; // save the location
                    location += incr; // try the adjacent cell
                    if (location >= GRIDSIZE) { // out of bounds (bottom)
                        success = false;
                    }
                    if (x > 0 && (location % GRIDLENGTH == 0)) { // out of bounds (right edge)
                        success = false;
                    }

                } else { // already used
                    if (test) System.out.print(" used " + location);
                    success = false;
                }
            }

        }
        int x = 0;
        int row = 0;
        int column = 0;

        while (x < comSize) {
            grid[coords[x]] = 1; // mark grid pt as used
            row = (int) (coords[x] /  GRIDLENGTH); // get row values
            column = coords[x] % GRIDLENGTH; // get column value
            temp = String.valueOf(ALPHABET.charAt(column)); // Get alpha value
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
            if (test) System.out.print(" coord" + x + " = " + alphaCells.get(x - 1));
        }

        return  alphaCells;
    }
}
