package chapter3;

public class Main {

    public static void main(String[] args) {
	    // Dog class example
        // Make a Dog object and access it
        // dog1 is a reference variable that holds the address of a Dog object
        Dog dog1 = new Dog();
        dog1.bark();
        dog1.name = "Bart";
        //dog1.bark();
        // Make a Dog array with 3 elements
        Dog[] myDogs = new Dog[3];
        myDogs[0] = new Dog();
        myDogs[1] = new Dog();
        myDogs[2] = dog1;
        // Now access the 3 Dog objects using the array references
        myDogs[0].name = "Fred";
        myDogs[1].name = "Marge";
        // Hmmmm.... what is myDogs[2] name??
        System.out.println("last dog's name is ");
        System.out.println(myDogs[2].name);
        // Now loop through the array
        // and tell all of the dogs to bark
        for (Dog dog: myDogs) {
            dog.bark();
        }
        // Test Book object
        Books book1 = new Books();
        book1.testBooks();
        // Test Hobbits object
        Hobbits h1 = new Hobbits();
        h1.testHobbits();
        // TestArrays
        TestArray t1 = new TestArray();
        t1.testArray();
        // Triangle
        Triangle ta = new Triangle();
        ta.testTriangle();
    }
}
