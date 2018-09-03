package chapter12;

import javax.swing.*;

public class SimpleGui1 {

    public static void main() {
        JFrame frame = new JFrame(); // Create a frame
        JButton button = new JButton("Click me"); // Create a button

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // forces program to quit, when you close the window
        frame.getContentPane().add(button); // add the JButton to the JFrame's content pane

        frame.setSize(300, 300);
        frame.setVisible(true); // Makes the window visible
    }
}
