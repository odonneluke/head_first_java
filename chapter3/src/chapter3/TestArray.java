package chapter3;

public class TestArray {
    public void testArray()
    {
         int y = 0;
         String[] islands = new String[4];
         int[] index = new int[4];
         // initialize  String variables for each element in islands
        islands[0] = "Bermuda";
        islands[1] = "Fiji";
        islands[2] = "Azores";
        islands[3] = "Cozumel";
        // init int variables for each element in index
        index[0] = 1;
        index[1] = 3;
        index[2] = 0;
        index[3] = 2;

        int ref = 0;
        while (y < 4) {
            ref = index[y];
            System.out.println("islands = " + islands[ref]);
            y++;
        }
        System.out.println();
    }
}
