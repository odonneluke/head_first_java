package chapter7;

public class Monster {

    public boolean frighten(int d) {
        System.out.println("arrgh");
        return true;
    }
}

class Vampire extends Monster {
    public boolean frighten(int x) {
    System.out.println("a bite?");
    return true;
    }
}

class Dragon extends Monster {
    public boolean frighten (int degree) {
        System.out.println("Breath Fire");
        return true;
    }
}