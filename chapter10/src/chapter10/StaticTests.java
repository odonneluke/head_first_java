package chapter10;

class StaticSuper {

    static {
        System.out.println("super static block");
    }

    // Constructor
    StaticSuper() {
        System.out.println();
        System.out.println("super constructor");
    }
}

public class StaticTests extends StaticSuper {
    private static int rand;

    static {
        rand = (int) (Math.random() * 6);
        System.out.println("static block " + rand);
    }

    StaticTests() {
        System.out.println("constructor");
    }

    public static void main() {
        System.out.println("in main");
        StaticTests staticTests = new StaticTests();
    }
}
