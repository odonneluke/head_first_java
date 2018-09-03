package chapter10;

public class TestBox {
    Integer i;
    int j;

    public static void main() {
        TestBox t = new TestBox();
        t.go();
    }

    private void go() {
        //j = i; //causes a NullPointerException i is a null reference;
        System.out.println(j);
        System.out.println(i);
    }
}
