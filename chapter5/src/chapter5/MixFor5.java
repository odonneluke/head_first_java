package chapter5;

public class MixFor5 {
    public static void main() {
        int x = 0;
        int y = 30;
        System.out.println();
        for (int outer = 0; outer < 3; outer++) {
            for (int inner = 4; inner > 1; inner--) {
                //x += 3;
                //x += 6;
                //x += 2;
                //x++;
                //x--;
                x += 0;
                y -= 2;
                if (x == 6) {
                    break;
                }
                x += 3;
            }
            y -= 2;
        }
        System.out.println(x + " " + y);
    }
}
