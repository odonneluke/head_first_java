package chapter12;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InnerButton {
    private JFrame frame;
    private JButton button;

    public static void main() {
        InnerButton gui = new InnerButton();
        gui.go();

    }

    private void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button = new JButton("Button A");
        button.addActionListener(new BListener());

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    class BListener implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        public void actionPerformed(ActionEvent e) {
            if (button.getText().equals("Button A")) {
                button.setText("Button B");
            } else {
                button.setText("Button A");
            }
        }
    }
}
