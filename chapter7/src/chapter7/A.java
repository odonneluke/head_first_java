package chapter7;

public class A {
    // Instance Variables
    int ivar = 7;
    void m1() {
        System.out.print("A's m1, ");
    }
    void m2() {
        System.out.print("A's m2, ");
    }
    void m3() {
        System.out.print("A's m3, ");
    }
}

class B extends A {
    void m1() {
        System.out.print("B's m1, ");
    }
}

class C extends B {
    void m3() {
        System.out.print("C's m3, " + (ivar + 6));
    }
}

class Mixed {
    public void main() {
        A a = new A();
        B b = new B();
        C c = new C();
        A a2 = new C();
        System.out.println();
        // 1
        b.m1();
        c.m2();
        a.m3();
        System.out.println();
        // 2
        c.m1();
        c.m2();
        c.m3();
        System.out.println();
        // 3
        a.m1();
        b.m2();
        c.m3();
        System.out.println();
        // 4
        a2.m1();
        a2.m2();
        a2.m3();
        System.out.println();
    }
}