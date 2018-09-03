package chapter4;

public class Mix4
{
    private int counter;

    public int getCounter() { return counter; }
    public void addOne() { counter++; }
    public int MaybeNew(int index)
    {
        if (index < 1){
            Mix4 m4 = new Mix4();
            m4.addOne();
            return 1;
        }
        return 0;
    }
}
