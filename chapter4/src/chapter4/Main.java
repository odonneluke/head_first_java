package chapter4;

public class Main {

    public static void main(String[] args) {
        // XCopy test
        int orig = 42;
        XCopy x = new XCopy();
        int y = x.go(orig);
        System.out.println(orig + " " + y);
        // Clock
        Clock c = new Clock();
        c.setTime("1245");
        String tod = c.getTime();
        System.out.println("time: " + tod);

        // Mix4 test
        int count = 0;
        Mix4[] m4a = new Mix4[20];
        int z = 0;
        while (z < 19) {
            m4a[z] = new Mix4();
            m4a[z].addOne();
            count++;
            count += m4a[z].MaybeNew(z);
            z++;
        }
        System.out.println("--------------------------------");
        System.out.println(count + " " + m4a[1].getCounter());
        // Puzzle 4 test
        Puzzle4[] obs = new Puzzle4[6];
        int a = 1;
        int b = 0;
        int result = 0;
        while (b < 6 ) {
            obs[b] = new Puzzle4();
            obs[b].setIvar(a);
            a *= 10;
            b++;
        }
        b = 6;
        while (b > 0) {
            b--;
            result += obs[b].doStuff(b);
        }
        System.out.println("result " + result);
    }
}
