package chapter1;

public class PhraseOMatic
{
    public static void PhraseOMatic(int n)
    {
        String[] word_list_1 = {"24/7", "multi-Tier", "30,000 foot", "B-to-B", "win-win", "front-end", "web-based",
                                "pervasive", "smart", "six-sigma", "critical-path", "path"};
        String[] word_list_2 = {"empowered", "sticky", "value-added", "oriented", "centric", "distributed", "clustered",
                                "branded", "outside-the-box", "positioned", "networked", "focused", "leveraged",
                                "aligned", "targeted", "shared", "cooperative", "accelearated"};
        String[] word_list_3 = {"process", "tipping-point", "solution", "architecture", "core competency", "strategy",
                                "mindshare", "portal", "space", "vision", "paradigm", "mission"};
        // Get the length of each list
        int length_1 = word_list_1.length;
        int length_2 = word_list_2.length;
        int length_3 = word_list_3.length;
        String word = "phrases";
        if (n == 1) word = "phrase";
        System.out.println("You have selected " + n + " " + word);
        for (int i = 0; i < n; i++) {
            // generate random numbers between 0  and list length minus 1
            int r1 = (int) (Math.random() * length_1);
            int r2 = (int) (Math.random() * length_2);
            int r3 = (int) (Math.random() * length_3);
            // Generate phrase
            System.out.println("Phrase " + (i + 1) + " is:");
            String phrase = word_list_1[r1] + " " + word_list_2[r2] + " " + word_list_3[r3];
            System.out.println("What we need is a " + phrase);
        }
    }
}
