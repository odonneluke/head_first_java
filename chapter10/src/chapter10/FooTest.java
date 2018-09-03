package chapter10;

public class FooTest {
    public static void main() {
        // test 1
        Foo1 foo1 = new Foo1();
        foo1.go();
        // test 4
        Foo4 foo4 = new Foo4();
        foo4.go();
        // test 5
        Foo5 foo5 = new Foo5();
        foo5.go(10);
        // test 6
        Foo6.go(10);
    }


}

class Foo1 {
    static int x;

    public void go() {
        System.out.println(x);
    }
}

//class Foo2 {
//    int x;
//    public static void go() {
//        System.out.println(x);
//    }
//}

//class  Foo3 {
//   final int x;
//    public void go() {
//        System.out.println(x);
//    }
//}

class Foo4 {
    static final int x = 12;

    public void go() {
        System.out.println(x);
    }
}

class Foo5 {
    static final int x = 12;

    public void go(final int x) {
        System.out.println(x);
    }
}

class Foo6 {
    int x = 12;

    public static void go(final int x) {
        System.out.println(x);
    }
}
