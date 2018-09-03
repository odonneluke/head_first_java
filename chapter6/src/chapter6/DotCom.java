package chapter6;
import java.util.ArrayList;

public class DotCom {
    // Instance Variables
    private String name;
    private ArrayList<String> cellLocations = new ArrayList<String>();

    // Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setCellLocations(ArrayList<String> cellLocations) {
        this.cellLocations = cellLocations;
    }
    public String getName() {
        return name;
    }

    public String checkGuess(String guess) {
        String result = "miss";
        int index = cellLocations.indexOf(guess);
        if (index >= 0) {
            cellLocations.remove(index);
            if (cellLocations.isEmpty()) {
                result = "kill";
                System.out.println("You killed " + name);
            }
            else {
                result = "hit";
            }
        }
        return result;
    }


}
