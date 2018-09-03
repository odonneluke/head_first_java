package chapter7;

public class Boat {
    private int length;

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void move() {
        System.out.print("drift ");
    }
}

class RowBoat extends Boat {
    public void rowTheBoat() {
        System.out.print("stroke natasha");
    }
}
class SailBoat extends Boat {
    public void move() {
        System.out.print("hoist sail ");
    }
}

class TestBoats {
    public static void main() {
        Boat b1 = new Boat();
        SailBoat b2 = new SailBoat();
        RowBoat b3 = new RowBoat();
        b2.setLength(32);
        b1.move();
        b3.move();
        b2.move();
    }
}