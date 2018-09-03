package chapter14;

import java.io.*;

public class DungeonGame implements Serializable {
    // Instance Variables
    public int x = 3;
    transient long y = 4;
    private short z = 5;


    int getX() {
        return x;
    }
    long getY() {
        return y;
    }

    public short getZ() {
        return z;
    }
}

class DungeonTest {
    public static void main() {
        DungeonGame d = new DungeonGame();
        System.out.println(d.getX() + d.getY() + d.getZ());

        try { // Serialize d
            FileOutputStream fileOutputStream = new FileOutputStream(new File("dog.ser"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(d);
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try { // Deserializes
            FileInputStream fileInputStream = new FileInputStream(new File("dog.ser"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            d = (DungeonGame) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(d.getX() + d.getY() + d.getZ());
    }
}





