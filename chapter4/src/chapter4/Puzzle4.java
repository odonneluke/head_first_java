package chapter4;

public class Puzzle4
{
   int ivar;

    public void setIvar(int ivar) { this.ivar = ivar; }
    public int getIvar() { return ivar; }
    public int doStuff(int factor) {
       if (ivar > 100) {
           return ivar * factor;
       } else {
           return ivar * (5 - factor);
       }

   }

}
