package chapter12;
import javax.swing.*;
import java.awt.event.*;

public class SimpleGui1B implements  ActionListener {
    private JButton button;
    private JFrame frame;

    public static void main() {
        SimpleGui1B gui = new SimpleGui1B();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        button = new JButton();

        button.addActionListener(this); // register the button - tell it to listen for events
        button.setText("Click me");

        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        button.setText("I've been clicked");
    }
}
