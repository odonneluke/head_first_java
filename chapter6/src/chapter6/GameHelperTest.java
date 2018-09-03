package chapter6;

import java.util.ArrayList;

public class GameHelperTest {

    public  void main() {
        System.out.print("GameHelper Unit Test");
        // Create A Single ArrayList Object
        GameHelper helper = new GameHelper();
        ArrayList<String> test1 = helper.placeDotCom(3, true);
        System.out.println("Test One " + ((test1.size() == 1) ? "Pass" : "Fail"));
        // Create A List of DotCom Objects
        ArrayList<DotCom> test2 = new ArrayList<DotCom>(2);
        for (int i = 0; i < 2; i++) {
            DotCom dotCom =  new DotCom();
            test2.add(dotCom);
        }

        for (DotCom dotCom : test2) {
            ArrayList<String> newLoc = helper.placeDotCom(1,true);
            dotCom.setCellLocations(newLoc);
        }
        System.out.println("Test Two " + ((test2.size() == 2) ? "Pass" : "Fail"));

        System.out.println();
    }
}
