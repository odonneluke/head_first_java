package chapter1;

public class BeerSong
{
    public static void BeerSong()
    {
        int beer_num = 99;
        String word = "bottles";
        while (beer_num > 0) {
            if (beer_num == 1) word = "bottle";
            System.out.print(beer_num + " " + word + " of beer on the wall, ");
            System.out.println(beer_num + " " + word + " of beer.");
            System.out.print("Take one down, ");
            System.out.print("Pass it around, ");
            beer_num--;
            if (beer_num > 0) {
                if (beer_num == 1) word = "bottle";
                System.out.println(beer_num + " " + word + " of beer on the wall.");
            }
            else System.out.print("no more bottles of beer on the wall.");
            System.out.println();
        }
        System.out.println();
        System.out.println("No more bottles of beer on the wall, no more bottles of beer.");
        System.out.println("Go to the store and buy some more, 99 bottles of beer on the wall.");
        System.out.println();
    }
}
