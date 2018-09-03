package chapter1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Beer Song
        BeerSong.BeerSong();
        Scanner s = new Scanner(System.in);
        // Random Phrase Generator
        System.out.println("How many number of randomly generated phrases do you want?");
        int n = Integer.parseInt(s.nextLine());
        PhraseOMatic.PhraseOMatic(n);
    }
}
