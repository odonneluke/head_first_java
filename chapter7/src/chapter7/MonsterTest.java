package chapter7;

public class MonsterTest {
    public void main() {
        Monster[] ma = new Monster[3];
        ma[0] = new Vampire();
        ma[1] = new Dragon();
        ma[2] = new Monster();
        int x = 0;
        for (Monster m : ma) {
            m.frighten(x);
            x++;
        }
    }
}
