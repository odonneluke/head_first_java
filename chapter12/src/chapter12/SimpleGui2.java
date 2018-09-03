package chapter12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGui2 implements ActionListener {

    private JFrame frame;
    private JButton actionButton;
    private SimpleGui2DrawPanel panel;

    public static void main() {
        SimpleGui2 gui = new SimpleGui2();
        gui.go();
    }

    private void go() {
        frame = new JFrame();
        actionButton = new JButton("Change color and position");
        panel = new SimpleGui2DrawPanel();
        // Set frame default config
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        // Register action button as a listener
        actionButton.addActionListener(this);
        // Add button and panel to layout
        frame.getContentPane().add(BorderLayout.SOUTH, actionButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }



}

class SimpleGui2DrawPanel extends JPanel {

    public void paintComponent(Graphics g) {
       g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color randColor = new Color(red, green, blue);

        g.setColor(randColor);
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 200);

        g.fillOval(x, y, 100, 100);

    }

}
