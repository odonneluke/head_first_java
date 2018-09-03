package chapter3;

public class Books
{
    String title;
    String author;
    // test the class
    public void testBooks()
    {
        Books[] myBooks = new Books[3];

        for (int i = 0; i < myBooks.length; i++){
            myBooks[i] = new Books();
        }
        myBooks[0].title = "The  Grapes of Java";
        myBooks[1].title = "The Java Gatsby";
        myBooks[2].title = "The Java Cookbook";
        myBooks[0].author = "bob";
        myBooks[1].author = "sue";
        myBooks[2].author = "ian";

        for (Books book: myBooks) {
            System.out.print(book.title);
            System.out.print(" by ");
            System.out.println(book.author);
        }
        System.out.println();
    }
}
