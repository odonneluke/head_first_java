package chapter3;

public class Hobbits
{
    String name;
    // method to test the class
    public void testHobbits()
    {
        Hobbits[] h = new Hobbits[3];
        for (int i = 0; i < h.length; i++) {
            h[i] = new Hobbits();
            h[i].name = "Bilbo";
            if (i == 1) h[i].name = "Frodo";
            else if (i == 2) h[i].name = "Sam";
            System.out.println(h[i].name + " is a good Hobbit name!");
        }
        System.out.println();
    }

}
